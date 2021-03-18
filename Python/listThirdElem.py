#remove evry 3rd element and print them till list is empty
#sir's method
l=[1,2,3,4,5,6,7,8,9]
pos=2
id=0
lenlist=len(l)
while lenlist>0:
    id=(pos+id)%lenlist
    print(l.pop(id),end=" ")
    lenlist-=1
""" def rem(l):
    if len(l)!=0:
        
        while i<len(l):
            if (i+1)%3==0:
                print(l.pop(i))
                i=i-1
                r=range(0,len(l))
    rem(l)
l =[1,2,3,4,5,6,7,8,9]
rem(l) """