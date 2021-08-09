#include <iostream>
#include <thread>
#include <chrono>

using namespace std;

bool run_forever = true;

void infinity()
{
    while (run_forever)
    {
        cout << "Robux !\n";
        this_thread::sleep_for(chrono::seconds(1));
    }
}
int main()
{
    thread t(infinity); // create a thread with the pointer of function to be executed in the new thread
    cin.get(); // waits for user to press enter

    run_forever = false; // as soon as user presses enter stop the loop inside infinity()

    t.join(); // wait untill the thread finishes its work

    // continue doing something

    return 0;
}

/*To COMPILE this program on windows you need to add -lpthread option while compiling:
    `g++ -o output threadDemo.cpp -lpthread` */