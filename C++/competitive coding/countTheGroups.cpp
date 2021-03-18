/*There are N seats in a row. You are given a string S with length N; for each valid i,
the i-th character of S is '0' if the i-th seat is empty or '1' if there is someone sitting in that seat.
Two people are friends if they are sitting next to each other. Two friends are always part of the same group of friends. 
Can you find the total number of groups?
eg - 100111001111000010 - 4*/
#include <iostream>
#include <string>
using namespace std;

int main() {
	int t;
	cin>>t;
    string dummy;
    getline(cin,dummy); //this will read the same line of t so of no use(therefore a dummy) after this getline cursor moves to the next line
	while(t--){
	    string s;
	    getline(cin,s);
	    char current_state=s[0];
	    int count=0;
		if(current_state=='1')
			count++;

	    for(int i=0;i<s.length();i++){
	        if(s[i]=='1' && s[i]!=current_state)
	            count++;
            current_state=s[i]; //stores state of previous seat, we will use this stored value of THIS iteration in the next iteration
	    }
	    cout<<count<<endl;
	}
	return 0;
}