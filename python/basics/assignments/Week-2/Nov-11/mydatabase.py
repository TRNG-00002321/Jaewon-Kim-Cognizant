import sqlite3

class Expense:
    def __init__(self, id, date, amount, category, description):
        self.id = id
        self.date = date
        self.amount = amount
        self.category = category
        self.description = description

def db_init():
    conn = sqlite3.connect(":memory:")
    cursor = conn.cursor()
    
    cursor.execute("""
        CREATE TABLE expenses (
            id INTEGER PRIMARY KEY,
            date DATE NOT NULL,
            amount REAL NOT NULL,
            category TEXT NOT NULL,
            description TEXT 
        )
    """)
    conn.commit()
    return conn, cursor

def create_expense(conn, cursor, expense):
    try:
        cursor.execute("""
            INSERT INTO expenses (date, amount, category, description)
            VALUES(?,?,?,?)
        """, (expense.date, expense.amount, expense.category, expense.description))
        conn.commit()
    except:
        print("Failed to add to the database")
        
def get_expense(conn, cursor, id):
    try:
        cursor.execute("""
            SELECT * 
            FROM expenses
            WHERE id = ?
        """, (int(id)))
        conn.commit()
    except:
        print("Failed to fetch from the database") 

def update_expense(conn, cursor, expense):
    

def delete_expense(conn, cursor):
    
def get_all_expense_total(conn, cursor):
    
def get_total_by_category(conn, cursor):
    
def get_total_by_date_range(conn, cursor):