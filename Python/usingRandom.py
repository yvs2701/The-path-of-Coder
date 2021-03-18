import random
#random number generators
import random
list1=[20,14,3,6,98]
print(random.choice(list1))
list1=[20,14,3,6,98]
print(random.choice(list1))
#print any floating number from 0 to 1
print(random.random())
#steps of 2 print random number for 100 to 399
print(random.randrange(100,400,2))
#shuffling a list
random.shuffle(list1)
print(list1)
#generating integers
for i in range(5):
    print(random.randint(10,50))