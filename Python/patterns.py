# 5 4 3 2 1
# 4 3 2 1
# 3 2 1
# 2 1
# 1
n=int(input("Enter pattern limit "))
for row in range(n,0,-1):
    for columnElem in range(row,0,-1):
        print(columnElem,end=" ")
    print()
# 1
# 2 1
# 3 2 1
# 4 3 2 1
# Floyd's triangle
print('-------------')
for row in range(1,n+1):
    for columnElem in range(row,0,-1):
        print(columnElem,end=" ")
    print()
#- - - - 1  - means spaces
#      1 2
#    1 2 3
#  1 2 3 4
#1 2 3 4 5
print('-------------')
for row in range(1,n+1):
    print(' '*(n-row),end=' ')# if row is 1 then 4 spaces and same for others
    for col in range(1,row+1):
         print(col,end = '')
    print()
#     1
#    1 2
#   1 2 3
#  1 2 3 4
# 1 2 3 4 5 
print('-------------')
for row in range(1,n+1):
    print(' '*(n-row),end=' ')
    for col in range(1,row+1):
         print(col,end = ' ')
    print()
# 1 1 1 1 1
# 2 2 2 2
# 3 3 3
# 4 4
# 5
print('-------------')
for i in range(1,n+1):
    for j in range(n+1-i):
        print(i,end=' ')
    print()
#another way to do the same
# for i in range(1,n+1):
#     print(str(i)*(n-(i-1)))

# 1
# 2 3 4
# 5 6 7 8 9
print('-------------')
t=1
for i in range(1,6,2):
    for j in range(1,i+1):
        print(t,end=' ')
        t=t+1
    print()
# * * * * *
# *       * 
# *       *
# *       *
# * * * * *
print('-------------')
for i in range(5):
    for j in range(5):
        if i==0 or i==4 or j==0 or j==4:
            print('*',end=" ")
        else:
            print(" ",end=" ")
    print()
#     *****
#    *****
#   *****
#  *****
# *****
print('-------------')
for i in range(5):
    for j in range(5-i):
        print(" ",end='')
    for j in range(5):
        print('*',end='')
    print()
# or a smaller code only works in python
# for j in range(4,1,-1):
#     print(j*' '+5*'*')
#make first and last 
#char of each word to upper case
print('-------------')
# pascal's triangle
#      1
#     1 1
#    1 2 1
#   1 3 3 1
#  1 4 6 4 1
# 1 5 10 10 5 1
for line in range(1, n + 1):  
    C = 1; # used to represent C(line, i)  C stands for combination
    for i in range(1, line + 1):  
            
        # The first value in a  
        # line is always 1  
        print(C, end = " ")
        C = int(C * (line - i) / i)
    print("")
# C(line, i)   = line! / ( (line-i)! * i! )
# C(line, i-1) = line! / ( (line - i + 1)! * (i-1)! )
# We can derive following expression from above two expressions.
# C(line, i) = C(line, i-1) * (line - i + 1) / i
#this is an optimised approach to calculate next Factorials.