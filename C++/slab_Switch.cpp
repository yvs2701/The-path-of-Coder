#include <iostream>

using namespace std;
//this program performs a slab calculation using switch case. question given at the bottom :)
int main() {
    int cl, failed,grace=0;
    cout<<"Enter the class and number of failed subjects"<<endl;
    cin>>cl>>failed;
    switch(cl){
        case 1:switch(failed){
                case 1:;
                case 2:;
                case 3: grace=5;
            }
            break;
        case 2:switch(failed){
                case 1:;
                case 2:grace=4;
            }
            break;
        case 3:switch(failed){
                case 1:grace=5;
                }
            break; // no need of writing this break it's anyway the end
        }
    cout<<"Grace marks per subject is "<<grace<<endl;
    return 0;
}

/*
Write a program to find the grace marks for a student using switch. The user should
enter the class obtained by the student and the number of subjects he has failed in.
Use the following rules:
i. If the student gets first class and the number of subjects failed is >3, then no grace
marks are awarded. If the number of subjects failed is less than or equal to '3' then the
grace is 5 marks per subject.
ii. If the student gets second class and the number of subjects failed in is >2, then no
grace marks are awarded. If the number of subjects failed in less than or equal to '3'
then the grace is 4 marks per subject.
iii. If the student gets third class and the number of subjects failed in is >1, then no
grace marks are awarded. If the number of subjects failed in is equal to '1' then the
grace is 5 marks per subject.
*/