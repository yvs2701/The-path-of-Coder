#include <iostream>
#include <string> // just to use the string once in the program
using namespace std;
/* nullptr is always a pointer type. 0 (aka. C's NULL bridged over into C++) could cause ambiguity in overloaded function resolution
so we will use a nullptr */

class linkedList
{
public:               // since everygting is public we could've used a struct
    int data;         // stores the value of that link/node
    linkedList *next; // stores the memory address of the next linkedList in the list

    linkedList()
    {
        data = 0;
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

void print(linkedList *head, string sep = " ") // here we took head by value as we don't need to modify list in this fn
{
    if (head == nullptr)
        return;
    cout << head->data;
    if (head->next == nullptr)
        return;
    head = head->next;
    while (head != nullptr)
    {
        cout << sep << head->data; // separator between two couts
        head = head->next;         // ptr is updated to pointer of the next node (which points to next to next node)
    }
    cout << endl;
}

int len(linkedList *head)
{
    int length = 0;
    if (head == nullptr)
        return length;
    linkedList *ptr = head;
    while (head->next != nullptr)
    {
        length++;
        head = head->next;
    }
    // at the last node while loop will break thereby not updating the length
    length++; // so we will do it for the last node
    return length;
}

void insert(linkedList *&head, int index, int d)
{
    if (head == nullptr) // when we have an empty list
    {
        if (index == 0)
            head = new linkedList(d);
        else
            cout << "indexOutOfBounds\n";
        return;
    }
    linkedList *ptr = head;
    index -= 1; // decreasing one index as we have to insert at this index not after this index
    while (index--)
    {
        if (ptr->next != nullptr)
            ptr = ptr->next; // navigating to the link/node at that index
        else
        {
            cout << "indexOutOfBounds\n";
            return; // instead of this you can use a check variable which will decide should the code after this loop execute or not
        }
    }

    linkedList *newNode = new linkedList(d); // creating a new link/node
    newNode->next = ptr->next;               // (since we were one index before what is given to us)
    ptr->next = newNode;
}
void append(linkedList *&head, int d) // insert at the end
{
    if (head == nullptr) // when we have an empty list
    {
        head = new linkedList(d); // then we directly assign head to this new link/node
        return;                   // return after doing the work
    }

    linkedList *ptr = head; // initialising another pointer variable (we dont want to mess up with original memory address of head)
    while (ptr->next != nullptr)
    {
        ptr = ptr->next; // finding the tail of the linked list
    }

    ptr->next = new linkedList(d); // appended (inserted at the end)
    // newNode->next =  NULL; it is already  NULL coz we specified that in our contructor
}
void prepend(linkedList *&head, int d) // insert at head
{
    if (head == nullptr) // when we have an empty list
    {
        head = new linkedList(d); // then we directly assign head to this new link/node
        return;                   // return after doing the work
    }
    linkedList *newNode = new linkedList(d);
    newNode->next = head;
    head = newNode; // since we called by reference this change will reflect everywhere
}

void delHead(linkedList *&head)
{
    if (head == nullptr) // list has no elements
    {
        cout << "nothingToDelete\n";
        return;
    }
    if (head->next == nullptr)
    {
        linkedList *toDelete = head;
        head = nullptr;
        delete toDelete;
        return;
    }
    linkedList *toDelete = head;
    head = head->next;
    delete toDelete; // deleting the waste memory
}
void delTail(linkedList *&head)
{
    if (head == nullptr) // list has no elements
    {
        cout << "nothingToDelete\n";
        return;
    }
    linkedList *ptr = head;
    if (ptr == nullptr)
        return;
    if (ptr->next == nullptr)
        cout << "noElementsAhead\n";
    while (ptr->next->next != nullptr) // one node before the tail
    {
        ptr = ptr->next;
    }
    linkedList *toDelete = ptr->next;
    ptr->next = nullptr;
    delete toDelete; // deleting the waste node
}
void del(linkedList *&head, int index)
{
    if (index >= len(head))
        cout << "indexOutOfBounds\n";
    if (head == nullptr) // list has no elements
    {
        cout << "nothingToDelete\n";
        return;
    }
    else if (head->next == nullptr) // we are at head with no elements after
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
        ptr = ptr->next;
    if (ptr->next == nullptr) // i.e. index was 1 and total elements were only 2 (incl head)
    {
        // so we need to delete an element after head
        linkedList *toDelete = head->next;
        head->next = nullptr; // anyways head->next->next = nullptr
        delete toDelete;
        return;
    }

    linkedList *toDelete = ptr->next; // store the value at the next node (whose address was given)
    ptr->next = ptr->next->next;
    delete toDelete; // deleting the waste node from the memory
}

void deleteMafterN(linkedList *head, int n, int m)
{
    /* deleted n nodes after every m nodes */
    int counter = 1;
    // head has to be counted anyways
    while (head != nullptr)
    {
        if (counter == m)
        {
            counter = n;
            while (counter > 0 && head->next != nullptr)
            {
                // we are at the same mth node
                // delete the next node
                del(head, 1);
                counter--;
            }
            counter = 1;
            // since the way we are counting head will shift one step ahead already
            // when we realise that counter = m !!!
        }
        else
            counter++;
        head = head->next;
    }
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

    while (head != nullptr)
    {
        current = head;

        head = head->next;
        current->next = prev;

        prev = current;
    }
    // since head = nullptr right now we shall set it back to current(which is pointing to the last node)
    head = current;
}

bool isPalindrome(linkedList *head) // checks if the list is palindrome
{
    // IF THE LIST IS PALINDROME THEN THE RIGHT HALF OF THE LIST
    // SHALL BE THE OPPOSITE SEQUENCE OF THE LEFT HALF !
    // IF WE REVERSE ANY ONE OF THE HALVES
    // THE THEY SHOULD BE IDENTICAL
    int length = len(head);
    linkedList *rightHalf = head;

    if (length & 1) // C++ considers 0 as false
    {
        // if Number is odd "Number & 1" will give 1 otherwise 0
        // we arrived inside this block, which means the length was odd
        length = length / 2;

        // navigating to the middle
        while (length--)
        {
            rightHalf = rightHalf->next;
        }
        rightHalf = rightHalf->next; // now we are at the beginning of the right half
        // since the length was odd
        // we wont compre the middle element by itself
        // we will compare the left half and right half
    }
    else
    {
        // we didn't make it to previous block, which means the length was even
        length = length / 2;

        // navigating to the middle
        while (length--)
        {
            rightHalf = rightHalf->next;
        }
        // now we are at the beginning of the right half
    }
    reverse(rightHalf);
    // now rightHalf pointer is pointing at the end of the list
    // and the end of the left half is still connected to the head of right
    // but the head of right shall now be pointing to NULL

    // if the length was odd then rightHalf shall reach the NULL
    // and head will be at the middle (before splitting) of the list

    linkedList *ptr = rightHalf;
    // we have to preserve rightHalf to again correct the list as it was before reversing the right half !
    while (ptr != nullptr)
    {
        if (ptr->data != head->data)
        {
            reverse(rightHalf); // all nodes fixed back
            return false;
        }
        head = head->next;
        ptr = ptr->next;
    }
    reverse(rightHalf); // all nodes fixed back
    return true;
}

void sort_list(linkedList *head)
{
    linkedList *ptr;
    int sortTill = len(head);

    /* bubble sort */

    // we can't use head == nullptr and inside the loop head = head->next
    // since here I'm sorting in such a way greatest element goes to the bottom
    // however in approach where lowest element rises upwards there we could've used it
    while (sortTill--)
    {
        ptr = head;

        // in bubble sort we know iner loop runs outer - 1
        // but we have alread did sortTill-- i.e. by now it is already -1
        // so we don't have to write it again here
        int runFor = sortTill; // inner loop shall run for these many times

        // while ptr is not null pointer and sort till doesn't turn to 0
        while (runFor > 0)
        {
            // if ptr->next is null pointer then this condition will throw
            // segmentation fault error
            if (ptr->data > ptr->next->data)
                std::swap(ptr->data, ptr->next->data);
            ptr = ptr->next;
            runFor--;
        }
    }
}

linkedList *splitAlternate(linkedList *head)
{
    if (head == nullptr || head->next == nullptr)
        return nullptr;

    linkedList *second_head = head->next;
    linkedList *ptr = second_head; // for traversing

    while (head != nullptr && head->next != nullptr && ptr != nullptr && ptr->next != nullptr)
    {
        head->next = head->next->next;
        head = head->next;
        // head should be updated before the second head always !
        ptr->next = ptr->next->next;
        ptr = ptr->next;
    }
    while (head != nullptr && head->next != nullptr)
    {
        head->next = head->next->next;
        head = head->next;
    }
    while (ptr != nullptr && ptr->next != nullptr)
    {
        ptr->next = ptr->next->next;
        ptr = ptr->next;
    }
    return second_head;
}

void removeDup(linkedList *node)
{
    while (node->next != nullptr)
    {
        linkedList *ptr = node;
        while (true)
        {
            if (ptr->next == nullptr)
                break;
            if (node->data == ptr->next->data)
                del(ptr, 1); // delete the element after this link/node pointer
            // since this is passed to be the head of the list it would be treated as index 0 in del function
            else
            {
                // very important to use else otheriwse if ptr moves ahead and
                // say there were 3 repeating elements in a row then we will miss one
                ptr = ptr->next;
            }
        }
        node = node->next;
    }
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
    cout << "2 is at the " << indexOf(ll, 2) << "th index"
         << "\n"; //index = 5
    del(ll, 0);   //delete at 0th index i.e. 5 (head)
    del(ll, 3);   //delete at 3rd index i.e. 0
    cout << "Length of this list is: " << len(ll) << "\n";
    print(ll, " => ");
    reverse(ll); // reverse a list
    print(ll, " <- ");
    return 0;
}
