import base64

from flask import Flask, render_template, request
from flask import jsonify

from classifier import classify

app = Flask(__name__)


@app.route('/material',methods=['POST'])
def material():
    with open('labels.txt') as file:
        labels = [line.strip() for line in file.readlines()]
    image = request.files['img']
    label = classify(image.read(), labels)
    responce = {
        "material": label
    }
    return jsonify(responce)


@app.route('/', methods=['GET'])
def post_form():
    return render_template('form.html')


if __name__ == '__main__':
    app.run()
