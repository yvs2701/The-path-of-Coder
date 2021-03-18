def isArmstrong(n):
    t=n
    exp=0
    while(t>0):
        exp=exp+1
        t=t//10
    t=n
    sum=0
    while(t>0):
        sum=sum+((t%10)**exp)
        t=t//10
    if n==sum:
        print (n,"is Armstrong!!")

n=int(input("Enter a number "))
for i in range(n):
    isArmstrong(i)