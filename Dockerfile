FROM golang:1.10.1 as builder
WORKDIR $GOPATH/src/github.com/iAmPlus/web-server-benchmark
COPY . .
RUN go get -u github.com/kardianos/govendor
RUN govendor sync
RUN CGO_ENABLED=0 GOOS=linux go build -a -installsuffix cgo -o web-server-benchmark .

FROM alpine:latest
# Add certs if any
# ADD ca-certificates.crt /etc/ssl/certs/
WORKDIR /root/
COPY --from=builder go/src/github.com/iAmPlus/web-server-benchmark .
ENV PORT 8888
EXPOSE 8888
CMD ["./web-server-benchmark"]  
# To build this image 
# Run "docker build --rm -f Dockerfile -t web-server-benchmark:latest ."