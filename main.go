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

	// the corresponding fasthttp code
	fasthttpRoutes := func(ctx *fasthttp.RequestCtx) {
		switch string(ctx.Path()) {
		case "/testJson":
			testJSON(ctx)
		default:
			ctx.Error("not found", fasthttp.StatusNotFound)
		}
	}

	fasthttp.FSHandler("/public", 0)
	fasthttp.ListenAndServe(":"+os.Getenv("PORT"), fasthttpRoutes)
}
