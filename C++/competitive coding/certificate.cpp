/*There were N students (numbered 1 through N) participating in the Indian Programming Camp (IPC) and they watched 
a total of K lectures (numbered 1 through K). For each student i and each lecture j, 
the i-th student watched the j-th lecture for Ti,j minutes.
Additionally, for each student i, we know that this student asked the question, 
"What is the criteria for getting a certificate?" Qi times.
The criteria for getting a certificate is that a student must have watched at least M minutes 
of lectures in total and they must have asked the question no more than 10 times.
Find out how many participants are eligible for a certificate.
Input
The first line of the input contains three space-separated integers N, M and K.
N lines follow. For each valid i, the i-th of these lines contains K+1 space-separated integers Ti,1,Ti,2,…,Ti,K,Qi.
Output
Print a single line containing one integer — the number of participants eligible for a certificate.*/
#include <iostream>
using namespace std;
int main() {
	int n,m,k;
	cout<<"Read the Q and enter the values 8)\n";
	cin>>n>>m>>k;
	int t[n][k+1]={-1};
	for(int i=0;i<n;i++)
	for(int j=0;j<=k;j++)
	{cin>>t[i][j];
	if (t[i][j]==-1)
	{   cout<<"Wrong value }/\n";
		j--;
	}
	}
	int count=0,qcount=0,tcount=0;
	for(int i=0;i<n;i++)
	{
	for(int j=0;j<k;j++)
		tcount+=t[i][j];
	qcount=t[i][k];
	if(tcount>=m && qcount<=10)
	count++;
	qcount=-1;
	tcount=0;
	}
	cout<<count<<endl;
	char c;
	cout<<"Enter any value to exit_";
	cin>>c; //just a delay waiting for user to press any key to exit
 	return 0;
}
/*  Example Input
    4 8 4
    1 2 1 2 5
    3 5 1 3 4
    1 2 4 5 11
    1 1 1 3 12
    Example Output
    1 
*/