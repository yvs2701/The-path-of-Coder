/*
You are given an integer C. Let d be the smallest integer such that 2^d is strictly greater than C.
Consider all pairs of non-negative integers (A,B) such that A,B<2^d and A⊕B=C (⊕ denotes the bitwise XOR operation). 
Find the maximum value of A⋅B (product) over all these pairs
*/
#include <iostream>
#include <cmath>
using namespace std;

int main(){
    int t=0;
    cin>>t;
    while(t--){
         int c=0;
        cin>>c;
         int a,b,p=-1;
         int d=0;
        while(pow(2,d)<=c)
        d++; //breaks when finally 2^d > c
        
         int power = pow(2,d);
        /*for(int i=0;i<power;i++){
            a=i;
            for(int j=0;j<power;j++){
                b=j;
                if((a^b)==c){ //^ : bitwise xor operator
                    p=max(p,a*b);
                }
            }
        }*/

        //optimised approrach : (needs O(n) time)
        a=0;
        while(true){ //infinite loop, breaks when a>=ower
            if(b>=power){
                b=0;
                a++;
            }
            if(a>=power)
                break;
            b++;
            if((a^b)==c)
                p=max(p,(a*b));
        }
        cout<<p<<endl;
    }
	return 0;
}