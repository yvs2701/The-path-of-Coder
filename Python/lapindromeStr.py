'''Lapindrome strings are those which have equal frequency of each character in both halves, e.g-'rotor' & 'gaga'.
All palindromes are lapindromes but vice versa is not true'''
def lapindrome(s):
    '''fn to check if the given string is lapindrome works for numbers too'''
    if not isinstance(s,str):
        s=str(s) #see basic skills program to see this use
    l=len(s)//2

    #this was my attempt but it fails for cartain cases
    # if len(s)%2==0:
    #     s1=s[:l]
    #     s2=s[l:]
    #     s3=s[-1:-(l+1):-1] or use s3=s[:-l-1:-1] starting will default be -1 when steps are -1 i.e reversed
    # else:
    #     s1=s[:l]
    #     s2=s[l+1:]
    #     s3=s[-1:-l-1:-1] or use s[:-l-1:-1]
    # if s1==s2 or s1==s3: this method won't work for exapamles like abcxcab frequency of each character is still the same on both the sides

    if sorted(s[:l])==sorted(s[-l:]):#sorted sorts the string in ascending order
        return True
    return False
    #this was in codechef competetion
'''
check=lapindrome(input("Enter a word : "))
if check:
    print("Lapindrome")
else:
    print("Not Lapindrome")
uncomment this if you want to use this program'''