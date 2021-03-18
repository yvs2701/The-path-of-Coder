def arms(a):
    sum=0
    temp=len(str(a))
    num=a
    while num!=0:
        d=num%10
        sum=sum+(d**temp)
        num=num//10
    if a==sum:
        print(a,"Arm!!")
min=int(input("Enter the lower bound "))
max=int(input("Enter the upper bound "))
for x in range(min,max+1):
    arms(x)