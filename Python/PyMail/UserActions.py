from tkinter import W, Button, Frame, IntVar, Label, Radiobutton, Tk, messagebox
import Authentication, Email, ChooseDraft, Email_GUI

class UserActions:
    def __init__(self, master, email:Email.Email):
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

        self.question = Label(master, text = 'What to do ?', font = ("Century Gothic", 18), bg = 'cyan')
        self.question.pack()

        # radio buttons
        selectedRadio = IntVar(name='radio_value')
        self.R1 = Radiobutton(master, text="Compose Email", variable=selectedRadio, value=1)
        self.R1.pack(anchor = W)
        self.R2 = Radiobutton(master, text="Load a draft", variable=selectedRadio, value=2)
        self.R2.pack(anchor = W )

        def submit():
            '''Submit button click handler'''
            if selectedRadio.get() == 1:
                self.master.destroy()
                root = Tk()
                Email_GUI.Email_GUI(root, self.email)
            elif selectedRadio.get() == 2:
                self.master.destroy()
                root = Tk()
                ChooseDraft.ChooseDraft(root, self.email)
            else:
                messagebox.showerror('No option selected' ,'Please select an option to proceed.')

        self.send = Button(master, text = 'Submit', command=submit)
        self.send.pack()

        self.logout = Button(master, text = 'Logout', command=self.logout)
        self.logout.pack()

        self.master = master


    def logout(self):
        x = messagebox.askyesno('Sign Out', 'Are you sure you want to log out?')
        if x:
            # go back to login screen
            self.master.destroy()
            root = Tk()
            auth = Authentication.Authentication(root)
