#include <iostream>
using namespace std;

template <typename type>
class qu
{
    type *arr;
    int index; // index of last slot
    int length; // stores the length of queue... sizeof(arr) / sizeof(arr[0]) won't work here (I tried it out many times)
public:
    qu()
    {
        length = 1;
        arr = new type[1];
        arr[0] = 0;
        index = 0;
    }

    void push(type value) // push to queue end
    {
        if (index == length) // if index == length of array
        {
            type *oldArr = arr;
            arr = new type[index + 1]; // create a new array of length = arr.length + 1
            length = index + 1;

            for (int i = 0; i < index; i++)
                arr[i] = oldArr[i];
            // for the one extra space that's created
            arr[index++] = value;
            // delete oldArr; it will throw "double free or corruption" error which means we are deleting something that wasn't allocated a memory
        }
        else
        {
            arr[index++] = value;
        }
    }
    void pop() // pop from beginning
    {
        if (index == 0)
        {
            std::cout << "Underflow !!!\n";
        }
        else
        {
            /* this wasn't working for some reason... I did a similar thing push() too
            maybe the reason was arr wasn't becoming the newArr by just assigning it to that pointer
            type *newArr;
            newArr = new type[index - 1];
            // except first element copy all others
            for (int i = 1; i < index; i++)
                newArr[i - 1] = arr[i];
            // change the pointers
            index--;
            arr = newArr;
            delete newArr; */
            type *oldArr = arr;
            arr = new type[index - 1];
            length = index - 1;

            // except first element copy all others
            for (int i = 1; i < index; i++)
                arr[i - 1] = oldArr[i];
            
            index--;
            // delete oldArr; it will throw "double free or corruption" error which means we are deleting something that wasn't allocated a memory
        }
    }
    type peek() // return frontmost element
    {
        return arr[0];
    }
    bool isEmpty() // returns true if queue is empty
    {
        return (index <= 0);
    }
};

int main() // driver function for testing
{
    qu<char> q; // declare the queue

    // pushing
    q.push('a');
    q.push('b');
    q.push('c');
    q.push('d');

    // popping
    q.pop();
    q.pop();

    // now the queue should be {c, d}
    std::cout << "Empty ? " << q.isEmpty() << " ";
    std::cout << "Front element = " << q.peek() << "\n"; // prints 'c'

    return 0;
}