#include <iostream>
#include <stdio.h>
#include <math.h>
using namespace std;

int main(){
    double a, b;
    char c;
    cout<<"Enter a number"<<endl;
    cin>>a;
    cout<<"Enter an operator +(add), -(subtract), *(multiply), /(divide), %(remainder) & ^(exponent)"<<endl;
    cin>>c;
    cout<<"Enter second number"<<endl;
    cin>>b;
    switch(c){
     case '+':cout<<a+b ;
               break;
     case '-':cout<<a-b ;
              break;
     case '*':cout<<a*b ;
              break;
     case '/':  b!=0?cout<<a/b:cout<<"Can't divide by zero :-(";
                break;
     case '%':  cout<<a-(a/b)*b ;   // a/b gives quotient which if multiplied by b gives a-remainder
                break;
     case '^':cout<<pow(a,b) ;
                break;          
     default : cout<<"Recheck the inputs :-/";
    }    
return 0;
}