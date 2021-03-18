#include <iostream>
#include <stack>
#include <queue>
#include <algorithm> //to use the sort function which sorts the entire vector/array.
#include <vector>
using namespace std;

int main(){
    stack<int> st; //LIFO
    queue<int> q; //FIFO
    for(int i : {1,2,3,4,5}){
        st.push(i);
        q.push(i);
    }
    cout<<"Stack - Queue\n";
    for(int j : {1,2,3,4,5}){
        cout<<st.top()<<" - "<<q.front()<<"\n";
        st.pop();
        q.pop();
    }
    cout<<"now lets see vectors :)\n";

    vector<int> v(5); // declared dynamic array of 5 size, specifying size is not necessary just exclude barckets part

    cout<<v.empty();//returns true if the vector size is 0, otherwise it returns false.

    v.push_back(2);// push_back adds(pushes) new elements to this vector to the end.
    v.push_back(3);
    v.push_back(1);
    
    v[0]=-1;
    v[1]=-2;
    v[2]=-3;
    v[3]=-4;
    v[4]=-5; //this will write elements to existing 5 memory blocks 
    //write each statement in a new line i wrote them in a single line using semicolons
    vector<int>::iterator it; 
    //creating iterator for vector usually we can traverse vector like arrays but many datatypes use iterators so we use it here too
    for (it = v.begin(); it < v.begin(); it++){
        cout<<*it<<" ";
    }
    cout<<"\n";

    v.insert(v.end(),{4,8,7,9,6,10,5});

    sort(v.begin(),v.end());
    for(it=v.begin();it<v.end();it++){
        cout<<*it<<" ";
    }

    return 0;
}