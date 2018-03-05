var sleep = require('sleep-async')();
const express = require('express')
const path = require('path')
const PORT = process.env.PORT || 8080
const DELAY = process.env.DELAY || 1000


express()
  .use(express.static(path.join(__dirname, 'public')))
  .get('/testJson', (req, res) => testJson(res))
  .listen(PORT, () => console.log(`Listening on ${ PORT }`))

function testJson(res){

  sleep.sleep(DELAY, function() {
    var string = "{'framework':'NodeJs', 'value':'Hello'}"
    res.send(JSON.stringify(string))
  });
}
