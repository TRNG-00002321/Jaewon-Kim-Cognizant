#Do the same thing as the todo list manager but using flask
import json
my_file = "/home/mattycrabs/cognizant/python/basics/assignments/Week-2/Nov-10/tasks.json"
 
def view_tasks():
    dictionary = {}
    with open(my_file, "r") as file:
        try:
            dictionary=json.load(file)
        except:
            print("json empty")
    return dictionary   

def add_tasks(task):
    dictionary = view_tasks()
    dictionary[task] = "Incomplete"
    with open(my_file,"w") as file:
        json.dump(dictionary, file)


def mark_task(mark):
    dictionary = view_tasks()
    for task, status in dictionary.items():
        if(task == mark):
            if(status == "Complete"):
                dictionary[mark] = "Incomplete"
            else:
                dictionary[mark] = "Complete"
                    
        
    with open(my_file, "w") as file:                 
        json.dump(dictionary, file)


def delete_task(delete):
    temp = view_tasks()   
    with open(my_file, "w") as file:                 
        newlist = newlist = dict(filter(lambda item: item[0] != delete, temp.items()))       
        json.dump(newlist, file)