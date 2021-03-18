#include <iostream>
using namespace std;
int main(){
/*  
    1
   2 2
  3   3
 4     4
5       5
 4     4
  3   3
   2 2
    1
*/
    int range[]={1,2,3,4,5}; //no need to specify the limit in [] as we specified the elements
    for (int i : range)//a for each loop
    {
        for(int j=5;j>i;j--)
        cout<<" ";
        cout<<i;
        for(int j=1;j<=2*(i-1)-1;j++)
        cout<<" ";
        if(i>1)
        cout<<i;
        cout<<"\n";
    }
    for(int i=4;i>=1;i--){
        for(int j=5;j>i;j--)
        cout<<" ";
        cout<<i;
        for(int j=1;j<=2*(i-1)-1;j++)
        cout<<" ";
        if(i>1)
        cout<<i;
        cout<<"\n";
    }
    cout<<"-------\n";
/*
        1
      2 3 2
    3 4 5 4 3
  4 5 6 7 6 5 4
5 6 7 8 9 8 7 6 5    
*/
    for(int i:range){
        for(int j=5;j>i;j--)
        cout<<"  "; //double space to match the pattern since in the question we see after every character there's a space
        int j=i;
        for (; j <= 2*i-1; j++)
        cout<<j<<" ";
        j-=2; //in the above loop when j++ made j=2*i then loop broke so we did j-=2 if i=2 then next loop starts from 2*i-2 ie 3
        //another way could be- to not intialise j=i before this previous loop and start this next loop with j=2*i-1 to draw the rest of the triangle
        for(;j>=i;j--)
        cout<<j<<" ";
        cout<<"\n";        
    }
    cout<<"-------\n";
/*
12345
21234
32123
43212
54321
*/
for(int i:range){
    for(int j=i;j>1;j--)
    cout<<j;
    for(int j=0;j<=5-i;j++)
    cout<<j+1;
    cout<<"\n";
}
/*Pascal's triangle
     1
    1 1
   1 2 1
  1 3 3 1
 1 4 6 4 1
1 5 10 10 5 1

C(line, i)   = line! / ( (line-i)! * i! ) !->factorial C->combination
C(line, i-1) = line! / ( (line - i + 1)! * (i-1)! )
We can derive following expression from above two expressions.
C(line, i) = C(line, i-1) * (line - i + 1) / i -> i.e. C(n+1) = C(n) * (row-column+1)/column
This is an optimised approach to get next Factorials*/
int c=1;
for(int i=0;i<=6;i++){
    for(int j=1;j<=6-i;j++) //printing spaces
    cout<<" ";

    for(int j=0;j<=i;j++){
        if(j==0||i==0)
            c=1;
        else
            c = c*(i-j+1)/j;
        cout<<c<<" ";
    }
    cout<<"\n";
}
    return 0;
}