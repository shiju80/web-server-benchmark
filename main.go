package main

import (
	"encoding/json"
	"fmt"
	"os"
	"strconv"
	"time"

	"github.com/valyala/fasthttp"
)

var DELAY = 1000

type HelloStruct struct {
	Framework string `json:"framework"`
	Value     string `json:"value"`
}

// request handler in net/http style, i.e. method bound to MyHandler struct.
func testJSON(ctx *fasthttp.RequestCtx) {

	var hello HelloStruct

	hello.Framework = "Go"
	hello.Value = "Hello"

	time.Sleep(time.Duration(DELAY) * time.Millisecond)

	ctx.SetStatusCode(fasthttp.StatusOK)

	json.NewEncoder(ctx).Encode(hello)
}

func main() {

	DELAY, _ = strconv.Atoi(os.Getenv("DELAY"))

	fmt.Println("Delay = ", DELAY)

	filehandler := fasthttp.FSHandler("./public", 0)

	// the corresponding fasthttp code
	fasthttpRoutes := func(ctx *fasthttp.RequestCtx) {
		switch string(ctx.Path()) {
		case "/testJson":
			testJSON(ctx)
		default:
			filehandler(ctx)
		}
	}

	fasthttp.ListenAndServe(":8080", fasthttpRoutes)
}
