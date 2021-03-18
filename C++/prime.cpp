#include <iostream>
using namespace std;

int main(){
    int n, check=0;
    cout<<"Enter a number : ";
    cin>>n;
    cout<<endl; //endl means end line instead of this u can write '\n' endl is slow coz it causes flush operation
    for(int i=2;i<=n/2;i++)
    if(n%i==0)
    check=-1;

    if (check!=-1)
    cout<<"prime"<<endl;
    else
    cout<<"not prime"<<endl;
/* cout<<n+"prime"<<endl; will not work u need to write cout<<n<<"prime"<<endl;
The + overloaded operator in this case is not concatenating any string since x is an integer. The output is moved by rvalue times in this case. So the first 10 characters are not printed. Check this reference.
    if you will write
    cout << "My favorite number is " + std::to_string(x) << endl;
    it will work 
*/
    return 0;
}