#include <iostream>
using namespace std;

int main() {
    int n,f;
    cin>>n;
    f=n%10;
    //this loop will run till the last digit is remaining coz after that n/10 will be 0
    while(n/10){
        cout<<n<<endl;
        n/=10; // on final iteration only last digit is remaining
    }
    cout<<n<<" "<<f<<" "<<(n+f)<<endl;
	return 0;
}
