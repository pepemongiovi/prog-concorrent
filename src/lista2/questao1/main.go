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
	tempoEspera := 1 + rand.Intn(10)
	tempoEsperaEmSegundos := time.Duration(tempoEspera) * time.Second

	fmt.Println("mirror " + strconv.Itoa(id) + " esperando por: " + strconv.Itoa(tempoEspera) + " segundos")

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

func main() {
	fmt.Println("Fazendo a requisição...")

	respostaMirror := reliableRequest()

	fmt.Println(respostaMirror)
}
