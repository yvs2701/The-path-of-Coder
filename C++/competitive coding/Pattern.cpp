/*
The first line of the input contains a single integer T denoting the number of test cases. 
The description of T test cases follows. The next line of each contains T space separated integers N.
Sample Input:
2
3 5
Sample Output:
1 4 10
2 5 11
4 10 22
3 6 12
1 4 10 22 46
2 5 11 23 47
4 10 22 46 94
3 6 12 24 48
*/
#include <iostream>
#include <cmath>
using namespace std;
int main() 
{	int t,n,i=1,j,temp,m;
	cin>>t;

	while(t--)
{   cin>>n;
	
	for(i=1;i<=4;i++)
	{ temp=i;
	  m=3;
		if(i==3)
		{
		 temp=4;
		 m=6;
		}
		
		else if(i==4)
		temp=3;
		for(j=1;j<=n;j++)
	{   if(i==3)
		{
			if(j==n)
			cout<<temp;
			else
			cout<<temp<<" ";
			temp+=m;
			m*=2;
		}
		else if (i==4)
		{   if(j==n)
			cout<<temp;
			else
			cout<<temp<<" ";
			temp+=m;
			m*=2;
		}
		else
		{	if(j==n)
			cout<<temp;
			else
			cout<<temp<<" ";
			temp+=m;
			m*=2;
		}
	}
	if(i!=4)
	cout<<endl;
	if(i==4&&(t-1)!=0)
	cout<<endl;
	}
	 	    cout<<"\n";
}
	return 0;
}