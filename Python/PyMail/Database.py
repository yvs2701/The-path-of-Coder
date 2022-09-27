from mysql.connector import Error, connect
from Secrets import Secrets


def initialize():
    try:
        connection = connect(host="localhost", user=Secrets.db_user, password=Secrets.db_password)
        cur = connection.cursor()

        cur.execute('CREATE DATABASE IF NOT EXISTS pymail;')
        cur.execute('USE pymail;')
        cur.execute('''CREATE TABLE IF NOT EXISTS draft(
            sender varchar(255),
            receivers varchar(1000),
            sbuject varchar(500),
            body varchar(1000)
        );''')

        print('DB ready !!')
        cur.close()
        connection.close()
        return True

    except Exception as e:
        print('Error while initializing db:', e)
        return False

def connectDB():
    connection = None
    try:
        connection = connect(host="localhost", database=Secrets.db_name, user=Secrets.db_user, password=Secrets.db_password)

        if connection.is_connected():
            print("Connected to MySQL database !!")
            return connection

    except Error as e:
        print("Error while connecting to MySQL", e)

        if (connection.is_connected()):
            connection.close()


def disconnectDB(conn):
    if conn.is_connected():
        connection.close()
        print('DB disconnected !!')
    else:
        print('DB was not connected !!')

if __name__ == '__main__':
    connection = connectDB()
    disconnectDB(connection)
