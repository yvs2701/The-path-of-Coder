list1=[1,2,3,4,5,6,7,8,9,0]
print(list1[2:6])
print(list1[-6:])
list1.append("one")
print(list1*2,end = ' ')# * used to repeat list elements n times + is used to add two lists
print()
del(list1[-1])
del(list1[2:3])
list1.remove(4)
print(list1)
list1.insert(3,0)
print(list1)