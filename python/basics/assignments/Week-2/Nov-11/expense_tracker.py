import mydatabase
from mydatabase import Expense

def main():
    conn,cursor = mydatabase.db_init()
    while(True):
        print("Expense Manger:"+
            "\n1.)Create expense"+
            "\n2.)Update expense"+
            "\n3.)Delete expense"+
            "\n4.)View expense"+
            "\n5.)View all expenses"+
            "\n6.)View total expenses by category"+
            "\n7.)View total expenses by date range"+
            "\n8.)View total expense"+
            "\n9.)Exit")
        selection = input("Select option:")
        
        match selection:
            case "1":
                print("Creating expense...")
                amount = float(input("Enter Amount: "))
                category = input("Enter Category: ")
                description = input("Enter Description: ")
                expense = Expense(None, amount, category, description)
                mydatabase.create_expense(conn,cursor,expense)
                
            case "2":
                print("Updating expense...")
                id = input("Enter id:")
                amount = float(input("Enter Amount: "))
                category = input("Enter Category: ")
                description = input("Enter Description: ")
                expense = Expense(id, amount, category, description)
                mydatabase.update_expense(conn,cursor,expense)
            case "3":
                print("Deleting expense...")
                id = input("Enter id:")
                mydatabase.delete_expense(conn,cursor,id)
                
            case "4":
                print("View expense...")
                id = input("Enter id:")
                print(mydatabase.get_expense(conn,cursor, id))
            case "5":
                print("Viewing all expenses...")
                
            case "6":
                print("Viewing total expenses by category...")
                print(mydatabase.get_total_by_category())
                
            case "7":
                print("Viewing total expenses by date range...")
                
            case "8":
                print("Viewing total expense...")
                print(mydatabase.get_total())
            case "9":
                print("Exiting program.")
                break
            case _:
                print("Invalid selection. Please try again.")
        

if __name__ == "__main__":
    main()