package main

import (
	"fmt"
	"math/rand"
	"strconv"
	"sync"
	"time"
)

func request(
	concluido <-chan interface{},
	resposta chan<- string,
	id int,
	wg *sync.WaitGroup,
) {
	defer wg.Done()

	tempoEspera := 1 + rand.Intn(10)
	tempoEsperaEmSegundos := time.Duration(tempoEspera) * time.Second

	select {
	case <-concluido:
	case <-time.After(tempoEsperaEmSegundos):
	}

	select {
	case <-concluido:
	case resposta <- ("Resposta pelo mirror " + strconv.Itoa(id)):
	}
}

func reliableRequest() string {
	concluido := make(chan interface{})
	resposta := make(chan string)

	var wg sync.WaitGroup
	wg.Add(3)

	for i := 0; i < 3; i++ {
		go request(concluido, resposta, i, &wg)
	}

	respostaMirror := <-resposta
	close(concluido)

	wg.Wait()

	return respostaMirror
}

func executaAteSinalDeParada(
	parada <-chan interface{},
) {
	select {
	case <-parada:
		{
			fmt.Println("Acabou")
		}
	default:
		{
			respostaMirror := reliableRequest()
			fmt.Println(respostaMirror)
			go executaAteSinalDeParada(parada)
		}
	}
}

func notificarParada(
	parada chan<- interface{},
) {
	tempoEsperaEmSegundos := time.Duration(10) * time.Second
	<-time.After(tempoEsperaEmSegundos)

	fmt.Println("Enviando sinal de parada...")

	close(parada)
}

func iniciarRequests() {
	parada := make(chan interface{})

	go executaAteSinalDeParada(parada)
	notificarParada(parada)
}

func main() {
	iniciarRequests()
}
