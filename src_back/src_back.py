import base64

from flask import Flask, render_template, request
from flask import jsonify

from classifier import classify

app = Flask(__name__)


@app.route('/material', methods=['POST'])
def material():
    with open('labels.txt') as file:
        labels = [line.strip() for line in file.readlines()]
    print(request.files)
    image = request.files['img']
    read = image.read()
    label = classify(read, labels)
    return render_template('material.html', img='data:image/png;base64,' + str(base64.b64encode(read), 'utf-8'), material = label)


@app.route('/', methods=['GET'])
def post_form():
    return render_template('form.html')


if __name__ == '__main__':
    app.run(host='0.0.0.0')
