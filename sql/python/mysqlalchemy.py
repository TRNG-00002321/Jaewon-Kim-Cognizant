from sqlalchemy import create_engine, MetaData, Table, Column, Integer, String, insert, update, delete, select

engine = create_engine(
    "mysql+mysqlconnector://root:password@localhost/database",
    echo=False
)
metadata = MetaData()

users_table = Table('users', metadata,
                    Column('id', Integer, primary_key=True, autoincrement=True),
                    Column('name', String(100)),
                    Column('email', String(100)))

metadata.create_all(engine)

with engine.connect() as conn:
    insert_stmt = insert(users_table).values(name='Charlie', email='charlie@example.com')
    result = conn.execute(insert_stmt)
    conn.commit()
    print(f"Inserted row with ID: {result.inserted_primary_key}")
    
    update_stmt = update(users_table).where(users_table.c.name == 'Alice').values(email='alice_new@example.com')
    result = conn.execute(update_stmt)
    conn.commit()
    print(f"Updated {result.rowcount} rows")
    
    delete_stmt = delete(users_table).where(users_table.c.name == 'Bob')
    result = conn.execute(delete_stmt)
    conn.commit()
    print(f"Deleted {result.rowcount} rows")
    
    select_stmt = select(users_table).where(users_table.c.name == "Alice")
    result = conn.execute(select_stmt)
    conn.commit()
    print(f"Row {result.first()}")
