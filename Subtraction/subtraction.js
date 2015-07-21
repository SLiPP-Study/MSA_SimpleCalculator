console.log('\u001b[1m');
console.log('\u001b[32m', '=============Server Start=============');
console.log('\u001b[1m');

var http = require('http');
var express = require('express');
var bodyParser = require('body-parser');

//webserver
var app = express();
app.use(bodyParser.json()); // for parsing application/json
app.use(bodyParser.urlencoded({ extended: true })); // for parsing application/x-www-form-urlencoded

var PORT = "9002";

//server
http.createServer(app).listen(PORT, function() {
	console.log('Server running');
});


app.post('/', function(request, response) {
	console.log(request.body);
	response.send((request.body.operand1 - request.body.operand2) + "") ;
});