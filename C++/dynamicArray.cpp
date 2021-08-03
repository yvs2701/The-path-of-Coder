#include <iostream>
using namespace std;

void print(int *arr, size_t n)
{
    for (int i = 0; i < n; i++)
        cout << arr[i] << " "; // or cout << *(arr + i);
    cout << "\n";
}

void update(int *arr, size_t n)
{
    cout << "Which place to update ? ";
    int index; cin >> index;
    cout << "update by what ? ";
    int value; cin >> value;
    arr[index] = value;
    print (arr, n);
}
void add(int *arr, size_t n)
{
    int index, value;
    cout << "Enter the index and value to add: "; cin >> index >> value;

    if (index >= n || index < 0)
    {
        cout << "Out of bounds \n!";
        return;
    }
    // else
    int *oldArr = arr;
    arr = new int[n + 1];
    // copy elements till 'index'
    for (int i = 0; i < index; i++)
        arr[i] = oldArr[i];
    
    arr[index] = value; // adding the 'value' to given 'index'

    // copying elements after 'index'
    for (int i = index + 1; i <= n; i++)
        arr[i] = oldArr[i-1];
    print (arr, n + 1);
}
void deletion(int *arr, size_t n)
{
    int index;
    cout << "Enter the index to delete "; cin >> index;

    if (index < 0 || index >= n)
    {
        cout << "Out of bounds !\n";
        return;
    }
    // else
    int *oldArr = arr;
    arr = new int[n-1];

    // except index copy all other elements
    for (int i = 0; i < index; i++)
        arr[i] = oldArr[i];
    for (int i = index + 1; i < n; i++)
        arr[i-1] = oldArr[i];
    print (arr, n - 1);
}

int main()
{
    cout << "Enter the length of the array ";
    int n; cin >> n;
    int arr[n];

    // insert for the first time
    for (int i = 0; i < n; i++)
        cin >> arr[i]; // or *(arr + i)
    print (arr, n);

    cout << "What do you want to do ?\n1- update value at index\n2- add an element at the index\n3- delete an element\n";
    int choice; cin >> choice;
    switch(choice)
    {
        case 1: update(arr, n);
                break;
        case 2: add(arr, n++); // n will increment since we are adding an element
                break;
        case 3: deletion(arr, n); // n should decrement since we are removing an element
                break;
        default: cout << "Invalid choice !\n";
    }
    return 0;
}