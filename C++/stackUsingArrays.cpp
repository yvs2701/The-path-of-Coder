#include <iostream>
using namespace std;

/* Using array to create a stack */

class Stack {
    int top;
    int MAX;
    int *a;
    
    public:
    
    Stack(int n) 
    {   /*constructor*/
        top = -1;
        MAX = n; // Maximum size of Stack is n
        a=new int[MAX]; //dynamic array allows us to define the limit as a variable (which can be changed)
    }

    void push(int x){
        if (top >= (MAX - 1)) {
            cout << "Stack Overflow";
        }
        else {
            a[++top] = x;
            cout << x << " pushed into stack\n";
        }
    }

    int pop(){
        if (top < 0){
            cout << "Stack Underflow";
            return 0;
        }
        else {
            int x = a[top--];
            return x;
        }
    }

    int peek(){
        if (top < 0) {
            cout << "Stack is Empty";
            return 0;
        }
        else {
            int x = a[top];
            return x;
        }
    }

    bool isEmpty(){
        return (top<0); //return true or false based on if top is < 0
    }
    ~Stack(){
        cout<<"\nSpace freed!\n---------";
    }
};

int main(){
    Stack st(6);
    
    st.push(2);
    st.push(3);
    st.push(5);

    cout<<st.pop()<<"\n";
    cout<<st.peek()<<"\n";
    cout<<st.isEmpty();

    return 0;
}