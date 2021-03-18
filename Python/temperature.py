def ctof(t):
    f = t*9/5 + 32
    return f
def ftoc(t):
    c=(t-32)*5/9
    return c
choice = input("Enter your choice :\n 1. ℃ to ℉\n 2. ℉ to ℃ \n")
if (choice == '1'):
    print(ctof(float(input("Enter the temperature \n"))),'℉')
elif(choice == '2'):
    print(ftoc(float(input("Enter the temperature \n"))),'℃ ')
else:
    print("Error 404 :(")
# i used windows + . to open emoji and special char keyboard to get ℃ & ℉ 
# this will run fine everywhere but in codec output (that is one in the extension of vscode) 
# it throws error coz it cannot print special char, but it runs fine in terminal