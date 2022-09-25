from tkinter import Tk
import Authentication

if __name__ == '__main__':
    root = Tk()
    auth = Authentication.Authentication(root) # module.class
    root.mainloop()