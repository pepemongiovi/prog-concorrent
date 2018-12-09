package main

import (
	"fmt"
	"math/rand"
	"strconv"
	"sync"
	"time"
	"runtime"
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

func getCurrentMemoryUsage() uint64 {
        var m runtime.MemStats
        runtime.ReadMemStats(&m)
        return m.Alloc
}

func main() {
	initialMemoryUsage := getCurrentMemoryUsage()
	
	respostaMirror := reliableRequest()
	
	posteriorMemoryUsage := getCurrentMemoryUsage()
	
	fmt.Println(respostaMirror)

	fmt.Printf("\nMemory usage = %v bytes\n", (posteriorMemoryUsage-initialMemoryUsage))
}
