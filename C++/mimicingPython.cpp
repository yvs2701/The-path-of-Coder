#include <iostream>
#include <string>
using namespace std;
/* This program mimics python string concatenation using '*' and to some extent print function as well (needs to be refined)*/
//defining our class for 'string'
class String{
    string str;

    public:

    String(){
        str=""; //constructor
    }

    void readline(){
        getline(cin,str); 
        // we dont require to use string for getline as it is also included in the istream header (which is again included in the iostream)
    }
    void print()
    {
        cout<<str;
    }
    void print(string s) //function overload to mimic python's print
    {
        cout<<s<<str<<"\n";
    }

    String operator*(int i){
        String s;
        while(i--){
            s.str += str ;
        }
        return s;
    }
    String concat(int i) // equivalent funtion to our overload
    {
        String s;
        while(i--){
            s.str += str ;
        }
        return s;
    }
};

string operator*(string str, int n) // for concatenating string without using our String class object
{
    string s;
    while(n--){
        s+=str;
    }
    return s;
}

int main(){
    String s,copy;
    s.readline();

    copy = s * 3 ;
    copy.print("The concatenated string is: ");

    cout<<"Now the equivalent function: ";
    s = s.concat(3);
    s.print();

    string str = "Another string";
    cout<<"\n"<<str*5;

    return 0;
}
