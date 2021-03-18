// add two numbers without using arithmatic operators
#include <iostream>
using namespace std;

int main(){
    int a,b;
    cout<<"Enter the numbers to find the sum: ";
    cin>>a>>b;
    int sum=b; //so that is a = 0 sum is b

    while(a != 0){
        sum = a^b; // gives the sum without the carry-overs
        a=(a&b)<<1; // a&b gives the carry for this bit sum but we add carry to next digit so left shifted
        b=sum;
    }
    cout<<sum;
    return 0;
}