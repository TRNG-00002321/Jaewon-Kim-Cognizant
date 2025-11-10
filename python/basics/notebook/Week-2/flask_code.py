from flask import Flask, redirect, url_for
import math

app = Flask(__name__)

#view functon since the output is viewed!
@app.route('/')
def hello_world():
    return "Hello, World!"

@app.route('/hello')
def hello_again():
    return "Hello, World Again!"

#another way to define
def hi():
    return "hi there"
app.add_url_rule('/hi', view_func=hi)

@app.route("/hello/<name>")
def hi_name(name):
    return 'Hi, %s!'%name.upper()

@app.route("/square/<int:num>")
def square(num):
    return str(int(num)**2)

@app.route("/circle/<float:radius>")
def circle_area(radius):
    return str(math.pi*radius**2)

@app.route("/sum/<int:num1>/<int:num2>")
def sum(num1, num2):
    return str(num1+num2)

#url_for is used for dynamically building a url for a specific function
@app.route("/role/<role>")
def role(role):
    if(role.lower() == "admin"):
        return redirect(url_for('hello_admin',admin=role))
    return redirect(url_for('guest',name=role))

@app.route('/admin/<admin>')
def hello_admin(admin):
    return "Hello Admin, %s!"%admin.upper()

@app.route("/user/<name>")
def guest(name):
    return "Hello Guest, %s!"%name.upper()

if __name__=='__main__':
    #default port is 5000
    #use this for automatic server restart on file save: app.run(debug=True)
    app.run()
