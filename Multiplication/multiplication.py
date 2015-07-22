from flask import Flask, request, jsonify
app = Flask(__name__)

@app.route('/', methods=['POST'])
def multiplication():
	json = request.json
	result = json["operand1"] * json["operand2"]

	print json
	print "Calculate Result : %s" % result
	
	return "%s" % result

if __name__ == '__main__':
    app.run(host="127.0.0.1", port=9003)
