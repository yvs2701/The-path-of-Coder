l=[1,2,3,48,9,17,5,6,11,21]
k=7
#find kth smallest element in list
def bubsort(l):
    for i in range(len(l)-1):
        for j in range(0,len(l)-i-1):
            if l[j]>l[j+1]:
                l[j],l[j+1]=l[j+1],l[j]
    return l
l=bubsort(l)
#l=[1, 2, 3, 5, 6, 9, 11, 17, 21, 48]
print("Kth smallest element is",l[k-1])

#find kth largest element in list
def bubsort_rev(l):
    for i in range(len(l)-1):
        for j in range(0,len(l)-i-1):
            if l[j]<l[j+1]:
                l[j],l[j+1]=l[j+1],l[j]
    return l
l=bubsort_rev(l)
#l=[48, 21, 17, 11, 9, 6, 5, 3, 2, 1]
print("kth largest element is",l[k-1])

#find largest number
print("largest element was",l[0])