from tkinter import Tk
from Authentication import Authentication
import Database

if __name__ == '__main__':
    if Database.initialize() == True:
        root = Tk()
        auth = Authentication(root) # module.class
        root.mainloop()