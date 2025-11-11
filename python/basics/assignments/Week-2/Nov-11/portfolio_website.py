from flask import Flask, render_template, redirect, request, url_for
import json

app = Flask(__name__)

def load_data():
    dictionary = {}
    my_file = "/home/mattycrabs/cognizant/python/basics/assignments/Week-2/Nov-11/portfolio.json"
    with open(my_file, "r") as file:
        try:
            dictionary=json.load(file)
        except:
            print("json empty")
    return dictionary 
 
@app.route("/")
def index():
    return redirect(url_for('view_home'))
 
@app.route("/home")
def view_home():
    website_json = load_data()
    return render_template("home.html", home=website_json["Home"])

@app.route("/projects")
def view_projects():
    website_json = load_data()
    return render_template("projects.html", projects=website_json["Projects"])

@app.route("/contacts")
def view_contact():
    website_json = load_data()
    return render_template("contacts.html", contacts=website_json["Contacts"])


if __name__ == '__main__':
    app.run(debug=True)