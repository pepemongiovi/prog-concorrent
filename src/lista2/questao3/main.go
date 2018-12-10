package main

import (
	"fmt"
	"math/rand"
	"strconv"
	"time"
)

func request(
	concluido chan interface{},
	resposta chan<- string,
	id int,
) {
	tempoEspera := 1 + rand.Intn(4)
	tempoEsperaEmSegundos := time.Duration(tempoEspera) * time.Second

	select {
	case <-concluido:
	case <-time.After(tempoEsperaEmSegundos):
	}

	select {
	case <-concluido:
	case resposta <- ("Resposta pelo mirror " + strconv.Itoa(id)):
		close(concluido)
	}
}

func reliableRequest() string {
	concluido := make(chan interface{})
	resposta := make(chan string)

	for i := 0; i < 3; i++ {
		go request(concluido, resposta, i)
	}

	respostaMirror := <-resposta

	return respostaMirror
}

func executaAteSinalDeParada(
	parada <-chan interface{},
) {
loop:
	for {
		select {
		case <-parada:
			{
				fmt.Println("Parando as requisições...")
				break loop
			}
		default:
			{
				respostaMirror := reliableRequest()
				fmt.Println(respostaMirror)
			}
		}
	}
}

func notificarParada(
	parada chan<- interface{},
) {
	tempoEspera := 10 + rand.Intn(10)
	tempoEsperaEmSegundos := time.Duration(tempoEspera) * time.Second

	fmt.Println("Sinal de parada será enviando em torno de " + strconv.Itoa(tempoEspera) + " segundos")

	<-time.After(tempoEsperaEmSegundos)

	fmt.Println("Enviando sinal de parada...")
	close(parada)
}

func iniciarRequests() {
	parada := make(chan interface{})

	go notificarParada(parada)

	fmt.Println("Iniciando requisições...")
	executaAteSinalDeParada(parada)
}

func main() {
	iniciarRequests()
	fmt.Println("Requisições concluídas.")
}
