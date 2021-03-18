''' loop to print helloworld 10 times '''
#this is also a comment like the one above
print("Hello world! "*10)
#range and loops
r= range(2,51,2)
print('Even numbers till 50: ')
for i in r :
    print(i,end=' ')
#to give a line break as we mentioned in previous print to end with a ' '
print()
#forcing user to enter particular dataype value
print ("Enter only an integer value")
while True:
    try:
        i=int(input())
    except ValueError:
        print("only integers accepted :/")
        continue
    else :
        break
print("Integer value parsed :) ",i)

#float and int dataypes
a=4.0 #float
b=8  #int
print(min(a,b))
c='String'
print(type(c))
flag = True if int(a)<<1==b>>1 else False
print(flag)

#IMPORTING A MODULE'S Fn AND USING IT
from lapindromeStr import lapindrome as lap
if lap(input("Enter a word to check lapindrome : ")): print("Lapindrome")
else: print("Not lapindrome")
if lap(121):print("121 is also lapindrome")

list1=[*range(0,11,1)]# * is the argument unpacking operator
tup=tuple((list1*2)[1:6]*2)#tuplle() function used to convert a list to tuple in this example
print(tup)

kywrd={'a':'float type','b':'int type','c':'str type','flag':'bool dataype','list1':'list type','tup':'tuple datatype',
'kywrd':'dict type','min':'used to calculate minimum of given arguments'}
#program glossary
for i in kywrd:
    print (i,":",kywrd[i])
print("----------")
# function to remove a character
def removeChar(str,n):
    if n in range(len(str)):
        return str[:n]+str[n+1:]
    else: 
        return "OutOfBounds" 
print(removeChar("VIT -Bhopal",3))
print("----------")
#count no of duplicates freq of each number and print a list conatining no duplicates.
a=[1,2,3,4,5,5,6,6,7,8,7]
res=[]
count=0
for i in a:
    if i not in res:
        res.append(i)
    else:
        count+=1
print("duplicates :",count)
print("duplicates removed :",res)
#count frequency of each element
freq=[0 for i in range(len(res))]
for i in range(len(res)):
    for j in a:
        if res[i]==j:
            freq[i]+=1
print(res)
print(freq)
#instead of this u can also make a visited list and use only a and check if the duplicate was visited.
print("----------")

#counting with a dict
sent="Mississipi"
count={}
for letter in sent:
    # if count.get(letter):
    #     count[letter]+=1
    # else:
    #     count[letter]=1 or do this
    count[letter] = count.get(letter,0) + 1 
    #get will return 0 (as specified in 2nd partameter) if it doesnt find letter in keys if it find then it will give the value
print("----------")
# convert first and last char of every word to upper case
s=input("Enter a string\n")
for wrd in s.split():
    print(wrd[0].upper(),wrd[1:-1],wrd[-1].upper(),end=' ',sep='')
# or store this in newstr = newstr +--- and then print it
print()
a=[]
n=int(input("Enter n for N x N matrix "))
for i in range(n):
    row=[]
    for j in range(n):
        row.append(int(input()))
    a.append(row)
print(a)
print("Here is your matrix")
for i in range(n):
    for j in range(n):
        print(a[i][j],end=" ")
    print()
print("Square of matrix")
result=[[0,0,0],
        [0,0,0],
        [0,0,0]] #works only for 3x3 for others make a larger result or find anotherr way to do it
for i in range(len(a)):
   # iterate through columns of a
   for j in range(len(a[0])):
       # iterate through rows of a
       for k in range(len(a)):
           result[i][j] += a[i][k] * a[k][j]
# if the two matrices are different !
# for i in range(len(X)):
#     iterate through columns of Y
#    for j in range(len(Y[0])):
#         iterate through rows of Y
#        for k in range(len(Y)):
#            result[i][j] += X[i][k] * Y[k][j]
for r in result:
   print(r)