#from think python book, same code can be applied for factorial
#later on I discovered there's nothing so smart about this, it is just a part of DP :(
def classicalFib(n=0): #this has a default parameter just in case the user is stupid :/
    if n==0:
        return 0
    elif n==1:
        return 1
    return (classicalFib(n-1) + classicalFib(n-2)) #after elif its obviously else as if and elif didnt execute and returned from then and there
def smartFib(n=0):
    try:
        with open("smartDictUses_cache.txt","r") as cacheFile:
            cache=eval(cacheFile.readline()+"}") #adding the curly brace as we reomved it while appending :)
            # string from this file converted to object that looks like dict in python 
            # in this dict 'keys' are the values of n and 'values' are the nth Fibonacci term
            #if you use any different method than eval which you may have to if your file doesnt have a string exactly represnting dict's syntax in python 
            # then integers will not be converted to in they all will be string (datatype is lost) in that case you may try pickling :)
    except Exception:
        # if our file is corrupted(deleted/data modified etc...) in any way then lets fix it :)
        with open("smartDictUses_cache.txt","w") as cacheFile:
            cacheFile.write("{1: 0, 2: 1, 3: 1, 4: 2, 5: 3, 6: 5, 7: 8, 8: 13, 9: 21, 10: 34, 11: 55, 12: 89, 13: 144, 14: 233, 15: 377, 16: 610, 17: 987, 18: 1597, 19: 2584, 20: 4181, 21: 6765") #no curly brace in the end
        cache={1: 0, 2: 1, 3: 1, 4: 2, 5: 3, 6: 5, 7: 8, 8: 13, 9: 21, 10: 34, 11: 55, 12: 89, 13: 144, 14: 233, 15: 377, 16: 610, 17: 987, 18: 1597, 19: 2584, 20: 4181, 21: 6765}
    if cache.get(n):
        return cache.get(n) #or simply cache[n]
    # if n==0: our cache already knows value for n = 0 
    #     return 0
    elif n==1:
        return 1
    else :
        cache[n]=smartFib(n-1)+smartFib(n-2)#after elif its obviously else as if and elif didnt execute and returned from then and there
        with open("smartDictUses_cache.txt","a") as cacheFile: 
            #we dont want to overwrite the recursion step's cache with the cache of the first call thats why we didn't use write mode(w) 
            #we used append mode(a) but while reading we have to one little trick (we need to remove the curly brace to append inside the the dict braces)
            cacheFile.write(", "+str(n)+": "+str(cache[n]))
            #by using with we dont have to write again and again file.close()
        return cache[n]

if __name__=='__main__':
    number=int(input("Enter the number to calculate Fibonacci (0, 1, 1, 2...) upto those many terms : "))
    print()
    choice=input("Which method to use ?\n1. Smart(using dictionaries)\n2.Classical(recursion)\n?(1/2) ")
    if (choice=='1'):
        print(smartFib(number))
    elif (choice=='2'):
        print(classicalFib(number))
    else:
        print("Print wrong input ￣へ￣")
    choice=input("Do you want to check out a matrix printed using dict ? (y/n):")
    if choice.lower().strip()=="y":
        matrix={(1,2):1, (2,3):1,(3,0):1}
        for i in range(1,4):
            for j in range(1,4):
                print(matrix.get((i,j),0),end=" ")
            print()
    input("Press any key to continue...")
#get will return the value specifed in secind parameter if it doesnt find the key but if it does the value of key will be returned
