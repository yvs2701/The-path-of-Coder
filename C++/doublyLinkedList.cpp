#include <iostream>
#include <string> // used in print() fn
using namespace std;

class linkedList
{
public:
    linkedList *prev;
    int data;         // stores the value of that link/node/element
    linkedList *next; //stores the memory address of the next linkedList in the list

    linkedList()
    {
        prev = nullptr;
        data = 0;
        next = nullptr;
    }
    linkedList(int d)
    {
        prev = nullptr;
        data = d;
        next = nullptr;
    }
};

void print(linkedList *head, string sep = " ") //here we took head by value as we don't need to modify list in this fn
{
    cout << head->data;
    head = head->next;
    while (head->next != nullptr)
    {
        cout << sep << head->data; // separator between two couts
        head = head->next;         // ptr is updated to pointer of the next node (which points to next to next node)
    }
    cout << sep << head->data << endl;
}
void printRev(linkedList *head, string sep = " ") //here we took head by value as we don't need to modify list in this fn
{
    while (head->next != nullptr)
    {
        head = head->next;
    }
    while (head->prev != nullptr)
    {
        cout << head->data << sep;
        head = head->prev;
    }
    cout << head->data << endl;
}

void insert(linkedList *&head, int index, int d)
{
    linkedList *ptr = head;
    index -= 1; //decreasing one index as we have to insert at this index not after this index
    while (index--)
    {
        if (ptr->next != nullptr)
            ptr = ptr->next; //navigating to the link/node/element at that index
        else
        {
            cout << "indexOutOfBounds\n";
            // throw "indexOutOfBounds";
            return; // instead of this you can use a check variable which will decide shouldthe code after this loop execute or not
        }
    }

    linkedList *newNode = new linkedList(d); // creating a new link/node/element
    newNode->prev = ptr;
    newNode->next = ptr->next;               // (since we were one index before waht is given to us)
    ptr->next->prev = newNode;
    ptr->next = newNode;
}
void append(linkedList *&head, int d) //insert at the end
{
    if (head == NULL) //when we have an empty list
    {
        head = new linkedList(d); //then we directly assign head to this new link/element/node
        return;                   //return after doing the work
    }

    linkedList *ptr = head; //initialising another pointer variable (we dont want to mess up with original memory address of head)
    while (ptr->next != nullptr)
    {
        ptr = ptr->next; //finding the tail of the linked list
    }

    linkedList *newNode = new linkedList(d); //appended (inserted at the end)
    ptr->next = newNode;
    newNode->prev = ptr;
}
void prepend(linkedList *&head, int d) //insert at head
{
    linkedList *newNode = new linkedList(d);
    newNode->next = head;
    head->prev = newNode;

    head = newNode; //since we called by reference this change will reflect everywhere
}

int main()
{
    linkedList *ll = new linkedList();
    ll->data = 1;  // giving some value to this node
    
    append(ll, 3); // 1 -> 3 ->null
    print(ll, " -> ");
    printRev(ll, " => ");

    append(ll, 2); // 1 -> 3 -> 2 ->null
    print(ll, " -> ");
    printRev(ll, " => ");

    insert(ll, 2, 0); // 1 -> 3 -> 0 -> 2 ->null, 0 inserted at second index
    print(ll, " -> ");
    printRev(ll, " => ");

    insert(ll, 2, 4); // 1 -> 3 -> 4 -> 0 -> 2 ->null, 4 at inserted 2nd index
    print(ll, " -> ");
    printRev(ll, " => ");

    append(ll, -1); // 1 -> 3 -> 4 -> 0 -> 2 -> -1 ->null
    print(ll, " -> ");
    printRev(ll, " => ");

    prepend(ll, 5); // 5 -> 1 -> 3 -> 4 -> 0 -> 2 -> -1 ->null
    print(ll, " -> ");
    printRev(ll, " => ");
    return 0;
}
