from flask import Flask, render_template, redirect, request, url_for
from listmethods import view_tasks, add_tasks, delete_task, mark_task

app = Flask(__name__)

@app.route("/list")
def view_list():
    dictionary = view_tasks()
    return render_template("list_manager.html", dictionary=dictionary)

@app.route("/add",methods=['POST'])
def add_task_to_page():
    task = request.form['task']
    add_tasks(task)
    return redirect(url_for("view_list"))

@app.route("/mark",methods=["POST"])
def mark_task_page():
    task = request.form['mark']
    mark_task(task)
    return redirect(url_for("view_list"))

@app.route("/delete",methods=["POST"])
def delete_task_page():
    task = request.form['delete']
    delete_task(task)
    return redirect(url_for("view_list"))

if __name__ == '__main__':
    app.run(debug=True)