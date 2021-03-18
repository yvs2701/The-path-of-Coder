#include <iostream>
#include <string> //instead of <cstring>, which are characters arrays ended by /0(null), we will use c++'s strings
using namespace std;

class batsmen{
    int runs,fours,sixes;
    string name;
    public:
    void assign(){
        cout<<"Enter your name\n";
        getline(cin,name);
        runs=0;
        fours=0;
        sixes=0;
    }
    void run(int r=0,int f=0,int s=0){
        //takes input as runs and number of fours and sixes scored till now
        runs+=r;
        fours+=f;
        sixes+=s;
    }
    void display(); //define outside the class
};
void batsmen::display(){
        cout<<name<<" scored : "<<fours<<" four(s), "<<sixes<<" six(es)\n";
        cout<<"Total runs : "<<runs;
    }

int main(){
    batsmen b;
    b.assign();
    b.run(6,0,1);//6 runs 0 fours 1 six
    b.run(4,1,0);//4 more runs 1 four 0 six
    b.run(2,0,0); //2 more runs with 0 boundaries
    b.display();
    return 0;
}