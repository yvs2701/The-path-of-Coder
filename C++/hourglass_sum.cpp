/*
You are given a 2d array, find the maximum sum of an hourglass.
The hour glass may look like:
1 2 3
  4
5 6 7
Sum = 28
*/
#include <iostream>
using namespace std;

int hourglassSum(int arr[6][6]){
    int i, j;
    int sum=-9*7; // as constraints were given for arr[i][j] to be from -9 to +9
    for (int i=0;i<=3;i++){
        for(int j=0;j<=3;j++){
            int s = arr[i][j] + arr[i][j+1] + arr[i][j+2] + arr[i+1][j+1] + arr[i+2][j] + arr[i+2][j+1] + arr[i+2][j+2];
            sum=max(sum,s);
            }
        }
    return sum;
}

int main(){
    int arr[6][6]={-9};
    for(int i=0;i<6;i++)
        for(int j=0;j<6;j++)
            cin>>arr[i][j];
    cout<<"Maximum hourglass sum is: "<<hourglassSum(arr)<<"\n";
    cout<<"Press enter to continue...";
    string c;
    getline(cin,c); //just to get the cursor to the next line
    getline(cin,c); // as soon as user presses enter it takes the input in a line after this the program ends.
}
/*
Sample Input:
1 1 1 0 0 0
0 1 0 0 0 0
1 1 1 0 0 0
0 0 2 4 4 0
0 0 0 2 0 0
0 0 1 2 4 0
Sample Output:
19
*/