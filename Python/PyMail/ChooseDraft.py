from tkinter import CENTER, Button, Frame, Label, Radiobutton, Tk, messagebox, IntVar
import Authentication, Email, Email_GUI

class ChooseDraft:
    def __init__(self, master, email:Email.Email):
        self.email = email

        self.frame = Frame(master)
        master.title('PyMail - email client')
        master.minsize(width = 400, height = 400)
        master.config(bg = 'orange')
        self.title = Label(master, text = 'PyMail', font = ("Century Gothic", 40), fg = 'snow', bg = 'orange')
        self.title.pack()

        self.question = Label(master, text = 'Which draft to edit?', font = ("Century Gothic", 18), bg = 'orange')
        self.question.pack()

        # drafts
        drafts = self.email.loadDrafts()
        if drafts[0] == True and len(drafts[1]) > 0:
            selectedRadio = IntVar()
            index = 0
            for draft in drafts[1]:
                R = Radiobutton(master, text=draft[3], variable=selectedRadio, value=index, bg='orange', borderwidth=0, highlightthickness=0)
                R.pack(anchor = CENTER)
                index += 1

            def submit():
                selected = False
                for i in range(0, len(drafts[1])):
                    if selectedRadio.get() == i:
                            selected = True
                            draft = drafts[1][i]
                            self.master.destroy()
                            root = Tk()
                            Email_GUI.Email_GUI(root, self.email, rec_email=draft[2], text=draft[3], main=draft[1], isDraft=True)
                if selected == False: # this was executing after running the above lines...
                    self.master.destroy()
                    root = Tk()
                    Email_GUI.Email_GUI(root, self.email)

            self.send = Button(master, text = 'Submit', command=submit)
            self.send.pack()

            self.logout = Button(master, text = 'Logout', command=self.logout)
            self.logout.pack()
        else:
            self.master.destroy()
            root = Tk()
            Email_GUI.Email_GUI(root, self.email)

        self.master = master

    def logout(self):
        x = messagebox.askyesno('Sign Out', 'Are you sure you want to log out?')
        if x:
            # go back to login screen
            self.master.destroy()
            root = Tk()
            auth = Authentication.Authentication(root)
