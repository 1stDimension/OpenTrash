from flask import Flask, render_template, request
import base64

app = Flask(__name__)


@app.route('/material', methods=['POST'])
def material():
    return render_template('material.html', img = base64.b64encode(request.files['img'].read()))


@app.route('/', methods=['GET'])
def post_form():
    return render_template('form.html')


if __name__ == '__main__':
    app.run()
