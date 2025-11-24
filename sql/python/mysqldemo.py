import mysql.connector

conn = mysql.connector.connect(
      host="localhost",
      user="root",
      password="password",
      database="database"
    )
cursor = conn.cursor()

cursor.execute("DROP TABLE IF EXISTS users")
cursor.execute('''
    CREATE TABLE IF NOT EXISTS users (
        id INTEGER PRIMARY KEY AUTO_INCREMENT,
        name TEXT,
        email TEXT
    )
''')

# Insert data - use %s instead of ?
cursor.execute("INSERT INTO users (name, email) VALUES (%s, %s)", ('Alice', 'alice@example.com'))
cursor.execute("INSERT INTO users (name, email) VALUES (%s, %s)", ('Bob', 'bob@example.com'))

# Commit the changes
conn.commit()

# Query data
cursor.execute("SELECT * FROM users")
rows = cursor.fetchall()

print("Users in the database:")
for row in rows:
    print(row)

cursor.execute("DROP TABLE IF EXISTS users01")
cursor.execute('''
    CREATE TABLE IF NOT EXISTS users01 (
        id INTEGER PRIMARY KEY AUTO_INCREMENT,
        name TEXT,
        age INTEGER
    )
''')

# Insert data using parameterized query
user_name = "Alice"
user_age = 30
cursor.execute("INSERT INTO users01 (name, age) VALUES (%s, %s)", (user_name, user_age))

# Select data using parameterized query
min_age = 25
cursor.execute("SELECT name, age FROM users01 WHERE age > %s", (min_age,))
results = cursor.fetchall()
for row in results:
    print(f"Name: {row[0]}, Age: {row[1]}")

conn.commit()
# Close the connection
conn.close()

