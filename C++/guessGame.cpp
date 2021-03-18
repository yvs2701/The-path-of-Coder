#include <iostream>
#include<stdlib.h>
using namespace std;

int main(){
    int number=rand()%100;
    int guess;
    cout<<"Guess a number between 1 - 100\n";
    while(true){
        cin>>guess;
        if(guess==number){
        cout<<"Woah ! You guessed it right :)";
        break;
        }
        else if (guess/number>=2)
        cout<<"You went too high\nGuess again ";
        else if(number/guess>=2)
        cout<<"That's too low\nGuess again ";
        else if(number/guess>=1||guess/number>=1)
        cout<<"Almost there :)\nGuess again ";
        else if(number/guess>=0.5||guess/number>=0.5)
        cout<<"Shoot ! That is close\nGuess Again ";
        else
        cout<<"Coming close\nGuess again ";
    }
    return 0;
}