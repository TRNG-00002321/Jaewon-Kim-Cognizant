import datetime
import sqlite3

class Expense:
    def __init__(self, id, amount, category, description):
        self.id = id
        self.date = datetime.datetime.now().date().isoformat()
        self.amount = amount
        self.category = category
        self.description = description

def db_init():
    conn = sqlite3.connect("persist.db")
    cursor = conn.cursor()
    
    try:
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
    except:
        print("Table already created!")
    return conn, cursor

def create_expense(conn, cursor, expense):
    try:
        cursor.execute("""
            INSERT INTO expenses (date, amount, category, description)
            VALUES(?,?,?,?)
        """, (expense.date, expense.amount, expense.category, expense.description))
        conn.commit()
        print("Successfully added new expense to the database:")
    except Exception as e:
        print("Failed to add to the database:", e)

def get_all_expense(conn, cursor):
    try:
        cursor.execute("""
            SELECT * FROM expenses
        """)
        return cursor.fetchall()
    except Exception as e:
        print("Failed to fetch from the database:", e)
    return None 

def get_expense(conn, cursor, id):
    try:
        cursor.execute("""
            SELECT * 
            FROM expenses
            WHERE id = ?
        """, (id,))
        return cursor.fetchone()
    except Exception as e:
        print("Failed to fetch from the database:", e)
        return None 

def update_expense(conn, cursor, expense):
    try:
        cursor.execute('''
            UPDATE expenses
            SET date = ?, amount = ?, category = ?, description = ?
            WHERE id = ?
        ''', (expense.date, expense.amount, expense.category, expense.description, expense.id))
    except Exception as e:
        print(f"Failed to update expense({expense.id}) from the database:", e)

def delete_expense(conn, cursor, id):
    try:
        cursor.execute('''
            DELETE FROM expenses
            WHERE id = ?
        ''', (id,))
        conn.commit()
    except Exception as e:
        print(f"Failed to delete expense({id}) from the database:", e)

    
def get_total(conn, cursor):
    try:
        cursor.execute('''
            SELECT SUM(amount) as total
            FROM expenses
        ''')
        result = cursor.fetchone()
        return result[0] if result[0] else 0.0
    except Exception as e:
        print(f"Failed to get total expense record from the database:", e)
        return 0.0
    
def get_total_by_category(conn, cursor):
    try:
        cursor.execute('''
            SELECT category, SUM(amount) as total
            FROM expenses
            GROUP BY category
            ORDER BY total DESC
        ''')
        return cursor.fetchall()
    except Exception as e:
        print(f"Failed to delete expense({id}) from the database:", e)
        return None
    
def get_total_by_date_range(conn, cursor, start_date, end_date):
    try:
        cursor.execute('''
            SELECT SUM(amount) as total
            FROM expenses
            WHERE date BETWEEN ? AND ?
        ''', (start_date, end_date))
        result = cursor.fetchone()
        return result[0] if result[0] else 0.0
    except Exception as e:
        print(f"Failed to get total by date range from the database:", e)
        return 0.0