def sq_root(n,g=1):#this g is given a default guess parameter if user simply entered a square to calculate and not a guess to start with
    #we added this absolute coz (value less than n)-n<0 so we need to make it positive
    if abs(g*g-n)<0.000000001: #(this is called precision)approximate value otherwise recursion will go on till infinity or to complete stack memory
        return g
    else:
        return sq_root(n,(g+n/g)/2.0)
print(sq_root(int(input("Enter a number "))))

print("----------")

def expo(x,n):
    if(n>=0):
        if(n%2==0):#even power
            return expo(x,n/2)*expo(x,n/2)
        elif(n==1):
            return x
        else:#odd power
            return x*expo(x,n-1)
    else:#negative power
        n=-1*n
        if(n%2==0):
            return 1/(expo(x,n/2)*expo(x,n/2))
        elif(n==1):
            return 1/x
        else:
            return 1/(x*expo(x,n-1))
print(expo(int(input("Enter an integer : ")),int(input("Enter the power : "))))