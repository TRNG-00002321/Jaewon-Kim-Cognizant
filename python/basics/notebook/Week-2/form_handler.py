from flask import Flask, request, redirect, url_for
app = Flask(__name__)

@app.route('/login',methods=['GET','POST'])
def login():
    if(request.method == "POST"):
        username = request.form['nm']
        return "Welcome, %s!"%username
    return "Form Processing"

if __name__=='__main__':
    app.run()