/*A VITBhopalLibrary has a book shop which maintains the inventory of books that are being sold at the VIT shop. The list includes details such as book author,
title, price,  publisher  and  stock  position.  Whenever  a studentwants  a  book,  the  sales person  inputs  the  title  and  author  and  the  system
searches  the  list  and  displays whether it is available or not. If it is not, an appropriate message is displayed. If it is, then the system displays the 
book details and requests for the number of copies required. If the requested copies are available, the total cost of the required copies is displayed, 
otherwise the message “sorry willserve later”is to be displayed.Develop a codea using a class called stock with suitable member functions and constructors*/
#include <iostream>
#include <string>
using namespace std;

class library{
    string author[5]={"HC Verma","Resnik Halliday","Sumita Arora","RS Agarwal","Sarojini Naidu"};
    string title[5]={"Concepts of Physics","Quantum Physics","Chemistry: Class 10th","Mathematics: class 10th","In the Bazaars of Hyderabad"}; 
    string publisher[5]={"Bharti Bhawan", "Willey", "Concise", "S Chand", "Sarojini Naidu"};
    int stock_pos[5]={108,84,112,114,48};
    float price[5]={1999.9, 2587.6, 1899.0,2200.0,56.78};
    int available[5]={3}; //3 copies of all the books are available
    int n;
    int i;

    void buy(int num){
        if(num!=-1 &&available[i]-num>=0){
            available[i]-=num;
            cout<<"You have to pay = "<<price[i]*num<<"R.s./\nWill you like to proceed ? y/n";
            char c;
            cin>>c;
            if(c=='Y'||c=='y')
            cout<<"You bought "<<num<<" "<<title[i]<<":)\n";
        }
        else if(num==-1)
        cout<<"Not Found !";
        else
        cout<<"";
    }

    public:

    library(){
        n=-1;
    }

    void search(string auth,string pub){
        for(i=0;i<5;i++){
            if(pub.compare(publisher[i])==0 && auth.compare(author[i])==0){
                cout<<"Book : "<<title[i]<<" written by :"<<auth<<"\n";
                cout<<"Published by :"<<pub<<"\n";
                cout<<"Rack number : "<<stock_pos[i]<<" PRICE :"<<price[i]<<"\n";
                cout<<available[i]<<" copies available.\n";
                if(available[i]!=0){
                    cout<<"How many copies you want to issue ? ";
                    cin>>n;
                    buy(n);
                }
                else
                    cout<<"Sorry the stock seems empty right now :(\n";
                break;
            }
        }
    }
    ~library(){
        cout<<"----------\n";
    }
};
 int main (){
    string aname, pname;
    cout<<"Enter the author's name - book name to search\n";
    getline(cin,aname,'-');
    getline(cin,pname);
    library lib;
    lib.search(aname,pname);
    return 0;
 }