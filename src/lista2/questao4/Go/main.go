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

	select {
	case <-concluido:
	case <-time.After(tempoEsperaEmSegundos):
	}

	select {
	case <-concluido:
	case resposta <- ("Resposta pelo mirror " + strconv.Itoa(id)):
	}
}

func reliableRequest(initialMemoryUsage uint64, ch *sync.WaitGroup) string {
	defer ch.Done()
	
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
  
  	var NUM_OF_THREADS int = 2000
  
	initialMemoryUsage := getCurrentMemoryUsage()
	
	var ch sync.WaitGroup
	ch.Add(NUM_OF_THREADS)
	
	for i := 0; i < NUM_OF_THREADS; i++ {
		go reliableRequest(initialMemoryUsage, &ch)
	}
	
	ch.Wait()
	
	posteriorMemoryUsage := getCurrentMemoryUsage()

	fmt.Printf("\nMemory usage = %v bytes\n", (posteriorMemoryUsage-initialMemoryUsage))
}
