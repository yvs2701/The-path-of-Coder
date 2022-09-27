from tkinter import END, Button, Entry, Frame, Label, Text, Tk, messagebox
import Authentication
from Email import Email

class Email_GUI:
    def __init__(self, master, email:Email):
        
        self.email = email

        self.frame = Frame(master)
        master.title('PyMail - email client')
        master.minsize(width = 400, height = 400)
        master.config(bg = 'cyan')
        self.title = Label(master, text = 'PyMail', font = ("Century Gothic", 40), fg = 'black', bg = 'cyan')
        self.title.pack()

        text1 = 'This is a simple python application that is used to \nsend emails with ease.'
        self.detail = Label(master, text = text1, font = ("Century Gothic", 15), fg = 'black', bg = 'cyan')
        self.detail.pack()

        self.connected = Label(master, text = f'\nConnected to: {self.email._username}\n({self.email.cl})\n', font = ("Century Gothic", 13), fg = 'red', bg = 'cyan')
        self.connected.pack()
        self.rec_title = Label(master, text = '\nReceiver Email Address', font = ("Century Gothic", 18), bg = 'cyan')
        self.rec_title.pack()

        self.notice = Label(master, text = 'Separate email addresses with a comma.', font = ("Century Gothic", 10), bg = 'cyan')
        self.notice.pack()

        self.rec_email = Entry(master, width = 40)
        self.rec_email.pack()

        self.sub_title = Label(master, text = '\nSubject', font = ("Century Gothic", 18), bg = 'cyan')
        self.sub_title.pack()

        self.text = Entry(master,width = 40)
        self.text.pack()

        self.text_title = Label(master, text = '\nMessage', font = ("Century Gothic", 18), bg = 'cyan')
        self.text_title.pack()

        self.main = Text(master, width = 50, height = 5)
        self.main.config(borderwidth=3)
        self.main.pack(ipady =3)

        self.send = Button(master, text = 'Send Email', command=self.send_email)
        self.send.pack()

        self.send = Button(master, text = 'Save Draft', command=self.save_draft)
        self.send.pack()

        self.logout = Button(master, text = 'Logout', command=self.logout)
        self.logout.pack()

        self.master = master

    def send_email(self):
        '''--- SENDS EMAIL ---'''
        rec = self.rec_email.get()

        lst = rec.split() # list of email strings
        subj = self.text.get()
        mes = self.main.get(0.0, END)

        if self.verify_email(rec):
            send = messagebox.askyesno('Send Email?', f'Are you sure you want to send this email?\n\nSubject: {subj}\n\nRecipient(s): {rec}')
            
            if bool(send) == True:
                
                success = True

                for address in lst:
                    x = self.email.send(to = address, subject = subj, message = mes) # as defined in email class, send() returns a bool
                    if bool(x) == False:
                        messagebox.showerror(f'Failed to Send email to {address}! Please check your inputs.')
                        success = False
                        break
                if success == True:
                    messagebox.showinfo('Email Sent!', f'Congratulations! Successfully Sent Email!\n\nSubject: {subj}\n\nRecipient(s): {rec}')
        else:
            messagebox.showerror('Invalid reciever(s) format!')

    def save_draft(self):
        '''--- Saves current email as draft ---'''
        rec = self.rec_email.get()
        subj = self.text.get()
        mes = self.main.get(0.0, END)

        success = self.email.saveAsDraft(to = rec, subject=subj, message=mes)
        if success == True:
            messagebox.showinfo('Saved', 'Saved as Draft !')
        if success == False:
            messagebox.showinfo('Error', 'Error while saving the draft!')


    def logout(self):
        x = messagebox.askyesno('Sign Out', 'Are you sure you want to log out?')
        if x:
            # go back to login screen
            self.master.destroy()
            root = Tk()
            auth = Authentication.Authentication(root)

    def verify_email(self, email):
        import re
        valid_re = re.compile(r'^.+@.+') # veirfy the list of email is properly separated by commas or not
        if valid_re.match(email):
            return True
        else:
            return False
