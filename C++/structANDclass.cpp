#include <iostream>
#include <string>
using namespace std;

struct student{
    string name;
    int age;
}s[3]; //this s[3] in the end means three student structs created in thus array

class people{
    string name="",likes="";
    public:
    void input(){
        cout<<"What's your name ?";
        cin>>name;
        cout<<"\nWhat do you like ?";
        cin>>likes;
    }
    void mssg(){
        cout<<name<<" likes "<<likes<<endl;
    }
};
// driver code to use the above written code :
int main(){
    char choice;
    cout<<"You want to store student data ? Y/N"<<endl;
    cin>>choice;
    switch(choice){
        case 'y':;
        case 'Y':   for(int i=2;i<=4;i++) //yes here you can use array indices that start from 1 or from anything you want
                    {cout<<"Enter your name : ";
                    cin>>s[i].name;
                    cout<<"\nEnter your age : ";
                    cin>>s[i].age;}

                    break;
        case 'n':;
        case 'N':   people person; //person is the object of class people
                    person.input();
                    cout<<"This is your data :"<<"\n";
                    person.mssg();
                    break;
    }
    return 0;
}