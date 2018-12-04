package main

import (
	"errors"
	"fmt"
	"math/rand"
	"strconv"
	"time"
)

func request(
	concluido <-chan interface{},
	resposta chan<- string,
	id int,
) {
	// tempoEspera := 1 + rand.Intn(3) // probabilidade alta de ter uma resposta em menos de 2 segundos.
	tempoEspera := 1 + rand.Intn(4) // probabilidade baixa de ter uma resposta em menos de 2 segundos, consequentemente, lançar uma exceção.
	tempoEsperaEmSegundos := time.Duration(tempoEspera) * time.Second

	fmt.Println("mirror " + strconv.Itoa(id) + " esperando por: " + strconv.Itoa(tempoEspera) + " segundos")

	select {
	case <-concluido:
	case <-time.After(tempoEsperaEmSegundos):
	}

	select {
	case <-concluido:
	case resposta <- ("Resposta pelo mirror " + strconv.Itoa(id)):
	}
}

func reliableRequest() (string, error) {
	concluido := make(chan interface{})
	resposta := make(chan string)

	for i := 0; i < 3; i++ {
		go request(concluido, resposta, i)
	}

	tempoMaximoEspera := time.Duration(2) * time.Second

	select {
	case <-time.After(tempoMaximoEspera):
		return "", errors.New("ultrapassou o tempo máximo de espera")
	case respostaMirror := <-resposta:
		{
			close(concluido)
			return respostaMirror, nil
		}
	}
}

func main() {
	respostaMirror, erro := reliableRequest()

	if erro != nil {
		fmt.Println("Erro: ", erro)
	} else {
		fmt.Println(respostaMirror)
	}
}
