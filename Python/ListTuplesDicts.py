print("LISTS : ",end="\n\n")
# Python Program to Swap the First and Last Value of a List 
l=[1,2,9,8,5,6,7,4,3]
print("Sample list",l)
(l[0],l[-1])=(l[-1],l[0])
print("the new list is :",l,end="\n----------\n")

# Python Program to Create a List of Tuples with the First Element as the Number and Second Element as the Square of the Number 
t=(1,2,3,4,5)
l=[tuple(i for i in t),tuple(i*i for i in t)]
print("Our list is : ",l,end="\n----------\n")

# Python Program to Read a List of Words and Return the Length of the Longest One 
words=["hey","everyone","this","is","a","test","list"]
maxSize=0
wrd=""
for w in words:
    size=len(w)
    if size>maxSize:
        maxSize=size
        wrd=w
print("The longest word is :","\'"+wrd+"\'","and the size is ",maxSize,end="\n----------\n")

print("RECURSION:",end="\n\n") 
# Python Program to Find the LCM of Two Numbers Using Recursion 
def gcd(a,b):
    if a == 0:
        return b
    return gcd(b % a, a)
def lcm(a,b):
    return (a*b)/gcd(a,b)
a = 12
b = 16
print('LCM of', a, 'and', b, 'is', lcm(a, b),end="\n----------\n")
# Python Program to Find if a Number is Prime or Not Prime Using Recursion 
def prime(n,d=2):
    if n==1:
        return False
    if d==int(n/2)+1:
        return True
    elif n%d==0:
        return False
    else:
        return prime(n,d+1)
n=int(input("Enter a number "))
if prime(n):
    print("Prime",end="\n----------\n")
else:
    print("Not prime",end="\n----------\n")

# Python Program to Find the Length of a List Using Recursion 
def length(l,i=0):
    if l[i]==l[-1]:
        return i+1
    else:
        return length(l,i+1)
l=[1,2,3,4,5,6,7,8,9]
print("the length of",l,"is ",length(l),end="\n----------\n")

print("DICTIONARY :",end="\n\n") 
# Program to find top scorer name among 8 students  
grades={"Nikhil":29,"Yash":46,"Srijit":48,"Vatsal":42,"Avantika":29.5,"Tanushree":44,"Luv":38,"Dev":40}
max=-1
n=""
for name in grades:
    if max<grades[name]:
        max=grades[name]
        n=name
print("Top scorer is ",n,"with",max,"marks",end="\n---------\n")
# Program to count the number of letter in a word 
sentence={word:len(word) for word in "This is a test sentence".split()}
size=0
for wrd in sentence:
    size+=sentence[wrd]
print("The number of letters in this sentence are : ",size,end="\n----------\n")

# Make a dictionary with each item being a pair of a number and its square 
data={i:i*i for i in range(1,11)}
print(data,end="\n----------\n")

# Python code to check whether a given key already exists in dictionary
data={i:i*i for i in range(1,11)}
key=int(input("Enter a key to check in data "))
if key in data:
    print("Found !",end="\n----------\n")
else: print("Not found",end="\n----------\n")

# Write a Python program to sum all the items in a dictionary 
sum=0
for i in data:
    sum+=data[i]
print("Sum of values in data is",sum,end="\n----------\n")
# Write a Python program to sort (ascending and descending) a dictionary by value. 
d={"One":1,"eight":8,"nine":9,"two":2,"four":4}
l=sorted(d.values())
print ("sorted items are")
for value in l:
    for e in d:
        if d[e]==value:
            print(e,":",value)
print(end="\n---------\n")

print("TUPLES :",end="\n\n")
# Program to input two (x,y) points and find midpoint between the two 
print("Enter the 4 values x1 y1 then x2 y2")
t1=(int(input()),int(input()))
t2=(int(input()),int(input()))
mid=(t1[0]/2+t2[0]/2,t1[1]/2+t2[1]/2)
print("mid point is",mid,end="\n----------\n")

# Write a python program to reverse a tuple 
tup=(1,2,3,4,5,6,7)
rev=tuple(t[-1::-1])
print("Reversed tuple is",rev,end="\n----------\n")

# Python program to find sum of all elements of each tuple inside a tuple 
t=((1,2,3),(10,0,5),(7,5,8))
sum=0
for i in t:
    for j in i:
        sum+=j
print("sum is",sum,end="\n----------\n")

# Python program to find repeated items in a tuple 
t=(1,2,3,4,5,3,4,5,6,7)
rep=[]
for i in t:
    if i not in rep:
        rep.append(i)
    else:
        print(i)
print("----------\n")

# Convert a list of tuples into a list of elements formed by multiplying elements of tuple 
l=[(1,2),(3,4,5),(6,7)]
res=[]
for i in l:
    mul=1
    for j in i:
        mul*=j
    res.append(mul)
print(res,end="\n----------\n")
# Write a Python program to get the 4th element from the first and 4th element from last of a tuple.
t=(1,2,3,4,5,6,7,8,9)
print(t[3])
print(t[-4])