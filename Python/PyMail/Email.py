import smtplib
import Database
class Email:
    def __init__(self, username, password, client):
        self.DBConnection = Database.connectDB()
        
        self._username = username
        self._password = password
        self._email = username

        self.client = client
        self.clients = {'Gmail':['smtp.gmail.com', 587], 'Outlook':['smtp.live.com', 587], 'Yahoo':['smtp.mail.yahoo.com', 465]}
        self.email_client=self.clients[client][0]
        self.port = self.clients[client][1]

        self.smtp = smtplib.SMTP(self.email_client, self.port)

    def login(self):
        '''Logs in with specified credentials
        - Make sure to turn off security to allow the app to sign in to the account'''
        try:
            self.smtp.connect(self.email_client, self.port)
            self.smtp.ehlo()
            self.smtp.starttls()
            self.smtp.login(self._username, self._password)
            return True
        except:
            return False

    def send(self, to, subject='', message='', eom="--- Sent using PyMail ---"):
        '''Sends the email to specified address
        - Returns true if email is sent
        - Returns false otherwise'''

        new = "Subject: {0}\n{1} \n\n{2}".format(subject, message, eom)
        try:
            self.smtp.sendmail(self._email, to, new)
            return True
        except:
            return False

    def saveAsDraft(self, to='', subject='', message=''):
        '''Saves an email draft written by the user'''
        cursor = self.DBConnection.cursor()
        try:
            cursor.execute(f'insert into draft(sender, receivers, subject, body) values ("{self._username}", "{to}", "{subject}", "{message}")')
            print('Saved draft !!')
            return True
        except Exception as e:
            print('Error while saving as draft', e)
            return False
        finally:
            cursor.close()