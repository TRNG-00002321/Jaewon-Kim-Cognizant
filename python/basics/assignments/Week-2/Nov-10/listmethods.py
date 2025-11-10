#Do the same thing as the todo list manager but using flask
my_file = "/home/mattycrabs/cognizant/python/basics/assignments/Week-2/Nov-10/tasks.txt"

def add_tasks(task):
    with open(my_file, "a") as file:
            file.write(task+":"+"incomplete"+"\n")

def view_tasks():
    dictionary = {}
    with open(my_file, "r") as file:
        for line in file:
            if not line.strip():
                continue
            task, status = line.split(":")
            dictionary[task] = status
    return dictionary

def mark_task(mark):
    temp = {}
    with open(my_file, "r") as file:
        for line in file:
            if not line.strip():
                continue
            task, status = line.split(":")
            if task == mark:
                status = "Complete"
            temp[task] = status
        
    with open(my_file, "w") as file:                 
        for task, status in temp.items():
            file.write(task+":"+status+"\n")


def delete_task(delete):
    temp = {}
    with open(my_file, "r") as file:
        for line in file:
            if not line.strip():
                continue
            task, status = line.split(":")
            if task == delete:
                continue
            temp[task] = status
        
    with open(my_file, "w") as file:                 
        for task, status in temp.items():
            file.write(task+":"+status+"\n")