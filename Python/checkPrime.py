def check(num):
    for i in range(2,num//2+1):
        #+1 coz range excludes last value if we have 4 then n//2 is 2 which will coz this loop to not run at all and function returns true
        if num%i==0:
            return False
    return True
print("Enter a number to get prime factors")
n=60
for i in range(2,n//2): #or use int(n/2)
    if check(i) and n%i==0:
        print(i,end=" ")