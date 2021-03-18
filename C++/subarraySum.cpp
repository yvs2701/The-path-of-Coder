#include <iostream>
using namespace std;

int main()
{
    int l,s;
    cout<<"Enter length of array"<<'\n'; // \n is faster than endl so its better to use it
    cin>>l;

    int a[l];
    cout<<"Enter the array\n";
    for (int i=0;i<l;i++)
    cin>>a[i];
    cout<<"Enter the sum of subarray ";
    cin>>s;
    int i=0, j=0;
    int st=-1, en=-1, sum = 0;
    while(j<l && sum+ a[j]<=s){
        sum+=a[j];
        j++;
    }
    if(sum==s){
        cout<<i+1<<" "<<j<<'\n';
        for(int k=i+1;k<=j;k++)
            cout<<a[k];
        return 0;
    }
    while(j<l)
    {
        sum+=a[j];
        while(sum>s)
        {
            sum-=a[i];
            i++;
        }
        if(sum==s){
            st=i+1;
            en=j+1;
            break;
        }
        j++;
    }
    cout<<st<<" "<<en<<'\n';
    for(int k=st;k<=en;k++)
            cout<<a[k]<<" ";
    return 0;
} 