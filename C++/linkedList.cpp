#include <iostream>
#include <string> //just to use the string once in the program
using namespace std;
/*nullptr is always a pointer type. 0 (aka. C's NULL bridged over into C++) could cause ambiguity in overloaded function resolution
so we will use a nullptr*/

class linkedList
{
public:               // since everygting is public we could've used a struct
    int data;         // stores the value of that link/node/element
    linkedList *next; //stores the memory address of the next linkedList in the list

    linkedList()
    {
        data = 0; // earlier I assigned data = NULL; but due to compiler warnings I became a good guy :)
        next = nullptr;
    }
    linkedList(int d)
    {
        data = d;
        next = nullptr;
    }
    ~linkedList()
    {
        cout << "Node/Linked-list deleted\n"; // destructor will be called whenever a node is deleted
        // when program end then too it will be executed as the linked list in the main() will go out of scope (and will be deleted)
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

int len(linkedList *head){
    int length = 0;
    if (head == nullptr)
        return length;
    linkedList *ptr = head;
    while(head->next != nullptr){
        length++;
        head = head->next;
    }
    // at the last node while loop will break thereby not updating the length
    length++; // so we will do it for the last node
    return length;
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
    newNode->next = ptr->next;               // (since we were one index before waht is given to us)
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

    ptr->next = new linkedList(d); //appended (inserted at the end)
    // newNode->next =  NULL; it is already  NULL coz we specified that in our contructor
}
void prepend(linkedList *&head, int d) //insert at head
{
    linkedList *newNode = new linkedList(d);
    newNode->next = head;
    head = newNode; //since we called by reference this change will reflect everywhere
}

void delHead(linkedList *&head)
{
    linkedList *toDelete = head;
    head = head->next;
    delete toDelete; //deleting the waste memory
}
void delTail(linkedList *&head)
{
    linkedList *ptr = head;
    while (ptr->next->next != nullptr) //one node before the tail
    {
        ptr = ptr->next;
    }
    linkedList *toDelete = ptr->next;
    ptr->next = nullptr;
    delete toDelete; //deleting the waste node
}
void del(linkedList *&head, int index)
{
    if (index >= len(head)){
        cout<<"indexOutOfBounds\n";
        // throw "indexOutOfBounds\n"
    }
    if (head == NULL) //list has no elements
    {
        return; //do nothing
    }
    else if (head->next == nullptr) //we are at head with no elements after
    {
        delHead(head);
        return;
    }

    linkedList *ptr = head;
    if (index == 0)
    {
        delHead(head);
        return;
    }
    index -= 1; // first getting the node just before the one to delete
    while (index--)
    {
        ptr = ptr->next;
    }
    if (ptr->next->next == nullptr) //then we got to the tail
    {
        delTail(head);
    }

    linkedList *toDelete = ptr->next; //store the value at the next node (whose address was given)
    ptr->next = ptr->next->next;
    delete toDelete; //deleting the waste node from the memory
}

int indexOf(linkedList *head, int search) //returns index of first occurence of search item
{
    int index = 0;
    while (head->next != nullptr)
    {
        if (head->data == search)
            return index;
        index++;
        head = head->next; // head is called by value not reference so no problem we can manipulate it
    }
    return -1; //not found
}

void reverse(linkedList *&head) // reverses the linkedlist in O(n)
{
    // we recieved head via reference so changes shall reflect everywhere
    linkedList *prev = nullptr; // use NULL for earlier versions of C++
    linkedList *current;

    while(head != nullptr)
    {
        current = head;

        head = head->next;
        current->next = prev;

        prev = current;
    }
    // since head = nullptr right now we shall set it back to current (which is pointing to the last node)
    head = current;
}

int main()
{
    linkedList *ll = new linkedList();
    /* can also be written as class linkedList * ll = (class linkedList*)malloc(sizeof(class linkedList))
    this is simply a type converion this statement meant:- allocate memory(malloc) in heap of the size of a class linkedList to a linkedList pointer*/

    ll->data = 1;     // giving some value to this node
    append(ll, 3);    // 1->3->null
    append(ll, 2);    // 1->3->2->null
    insert(ll, 2, 0); // 1->3->0->2->null, 0 inserted at second index
    insert(ll, 2, 4); // 1->3->4->0->2->null, 4 at inserted 2nd index
    append(ll, -1);   // 1->3->4->0->2->-1->null
    prepend(ll, 5);   // 5->1->3->4->0->2->-1->null
    print(ll, " -> ");
    cout << "6 is in the list? " << indexOf(ll, 6) << "\n"; // earlier I made a search function but doesn't matter indexOf will do the job too
    cout << "2 is at the " << indexOf(ll, 2) << "th index" << "\n"; //index = 5
    del(ll, 0);   //delete at 0th index i.e. 5 (head)
    del(ll, 3);   //delete at 3rd index i.e. 0
    cout<<"Length of this list is: "<<len(ll)<<"\n";
    print(ll, " => ");
    reverse(ll); // reverse a list
    print(ll, " <- ");
    return 0;
}
