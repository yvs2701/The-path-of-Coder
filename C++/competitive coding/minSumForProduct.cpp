/*Input:
The first line of the input contains a single integer T denoting the number of test cases. The description of T test cases follows.
Each testcase contains single integer N.

Output:
For each testcase, output minimum x + y such that x*y = N and x,y >0. 
*/
#include <iostream>
using namespace std;

int main() {
	int t,n;
	cin>>t;
	double minsum;
	while(t--)//as soon as t=0 while(0) means while(False) ie loop ends
	{
	    cin>>n;
	    minsum=n+1;
	    for(int i=1;i<=n/2;i++)
	    {
	        if(n%i==0)
			if(i+(n/i)<minsum)
			minsum=i+(n/i);
	    }
	    cout<<minsum;
	    if(t!=0)
	    cout<<endl;
	}
	return 0;
}
