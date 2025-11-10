from flask import Flask, render_template

"""default template folder is named "template" to change use: 
app = Flask(__name__, template_folder="new_folder_name")"""
app = Flask(__name__)

@app.route('/index')
def index():
    return render_template("hello.html")

@app.route('/hello/<name>')
def hello_name(name):
    return render_template('hello_name.html', name=name)    

@app.route('/score/<int:score>')
def hello_score(score):
    return render_template('hello_score.html', score=score)

#create dictioanry of makr s in the foillowing subjects physics, chem, math. Assign 
#the marks according to your choice and display them in a tabular format in html
@app.route('/dictionary')
def hello_dictionary():
    dictionary = {"Math":90, "Chemistry":"67", "Physics":20}
    return render_template('hello_dictionary.html', dictionary=dictionary)

if __name__ == '__main__':
    app.run(debug=True)