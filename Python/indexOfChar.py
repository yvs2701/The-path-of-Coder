def findInString(ch,str):
    for i in range(len(str)):
        if(str[i]==ch):
            return ch,i
    return ch,-1
#this was done intentionally coz i wanted indexes otherwise i would not use range to create 1 to length range of integers.
#i would simply use for i in str to get each character in i.
print("Enter a string :")
str=input()
print("Which character to find ? ")
ch=input()
result=findInString(ch,str)
if(result[1]!=-1):
    print ("found",result[0],"t",result[1])
else:
    print("Not found :(")