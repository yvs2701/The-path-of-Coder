#include <iostream>
using namespace std;
int main(){
//this logic is working for positive integers, it gives negative of gcd in negative integers
int n1, n2, hcf ;
char c = 'Y';
while (c=='Y'|| c=='y'){
    cout<<"Enter two numbers to find gretest common divisor\n";
    cin>>n1>>n2;
    hcf= n1>n2 ? n2:n1 ;
    if(n1%n2==0)
    cout<<"gcd is : "<<n2<<"\n";
    else if(n2%n1==0)
    cout<<"gcd is : "<<n1<<"\n";
    else
    {while(hcf>=1)
    {
        if(n1%hcf==0 && n2%hcf==0)
        break;
        hcf--;
    }
    cout<<"gcd is : "<<hcf<<"\n";
    }
    cout<<"Do you want to to continue ? y/n\n";
    cin>>c;
    cout<<"\n";
}
return 0;
}/*Euclidian algo for GCD (uses recursion)
int gcd(a,b){
    if(b!=0)
    return gcd(b,a%b);
    #till we get remiander zero we keep doing it
    else
    return a;
}*/
/* Another brilliant method to find gcd of just two numbers at a time
    int n1, n2;

    cout << "Enter two numbers: ";
    cin >> n1 >> n2;
    
    while(n1 != n2)
    {
        if(n1 > n2)
            n1 -= n2;
        else
            n2 -= n1;
    }

    cout << "HCF = " << n1;
*/