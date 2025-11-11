import json
my_file = "/home/mattycrabs/cognizant/python/basics/assignments/Week-2/Nov-11/quiz.json"
 
def load_questions():
    dictionary = {}
    with open(my_file, "r") as file:
        try:
            dictionary=json.load(file)
        except:
            print("json empty")
    return dictionary   

def main():
    dictionary = load_questions()
    score = 0
    for question, answer in dictionary.items():
        my_answer = input(question+" ")
        if(my_answer.lower().strip() == answer.lower().strip()):
            score = score + 1
    
    print(f"Quiz complete! Score: {100*(score/len(dictionary))}%")

if __name__ == '__main__':
    main()