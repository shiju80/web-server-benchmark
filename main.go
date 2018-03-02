package main

import (
	"encoding/json"
	"fmt"
	"net/http"
	"os"
	"strconv"
	"time"

	"github.com/gorilla/mux"
)

var DELAY = 1000

type HelloStruct struct {
	Framework string `json:"framework"`
	Value     string `json:"value"`
}

func testJSON(w http.ResponseWriter, r *http.Request) {

	var hello HelloStruct

	hello.Framework = "Go"
	hello.Value = "Hello"

	time.Sleep(time.Duration(DELAY))
	w.WriteHeader(200)
	json.NewEncoder(w).Encode(hello)

}

func main() {

	DELAY, _ = strconv.Atoi(os.Getenv("DELAY"))

	fmt.Println("Delay = ", DELAY)

	r := mux.NewRouter()
	r.HandleFunc("/testJson", testJSON).Methods("GET")

	staticFileHandler := http.FileServer(http.Dir("./public"))
	r.PathPrefix("/").Handler(staticFileHandler).Methods("GET")

	http.ListenAndServe(":"+os.Getenv("PORT"), r)
}
