/* You have 2 jugs with capacities x and y ( > x). Goal is to measure z amount of water using these 2 jugs.

Algorithm / Code structure:
- Transfer water from one jug to another.
- Empty a jug.
- Fill a jug.

Two methods:
- always tranfer from jug x to y.
- always tranfer from jug y to x.
Return the solution with least steps.
*/
#include <iostream>
using namespace std;
/*
breaking the code into components (planning):
int waterJug(int, int, int);   arg1 and arg2 are capacitites of two jugs (arg1 < arg2) arg3 will be the voulme to be measured
int xTOy(int, int, int);       arg1 and arg2 are capacitites of two jugs (arg1 < arg2) arg3 will be the voulme to be measured
int yTOx(int, int, int);       arg1 and arg2 are capacitites of two jugs (arg1 < arg2) arg3 will be the voulme to be measured

void fill(int, int);           completely fills a jug (arg1, arg2) arg1 is the total capacity and arg2 is the volume already filled up
void transfer(int, int, int);  (arg1, arg2, amt) transfer water from 1st argument to 2nd (having 3rd agrument of water already)
void empty(int);               assigns zero to variable passed
*/
void fill(int cap, int &vol)
{
    vol += cap - vol;
}

void empty(int &vol)
{
    vol = 0;
}

void transfer(int &vol1, int cap, int &vol2)
{
    if (vol1 <= (cap - vol2))
    {
        vol2 += vol1;
        vol1 = 0; // 1 is now empty
    }
    else // 1 has more water than what 2 can hold, then:
    {
        vol1 -= (cap - vol2); // b - vol2 volume of water transfered
        vol2 = cap;           // 2 is completely filled
    }
}

int xTOy(int j1, int j2, int t)
{
    int vol1, vol2; // volumes stores in the two jugs
    vol1 = vol2 = 0;
    int stepCount = 0;

    while (vol1 != t || vol2 != t)
    {
        if (vol1 == 0)
        {
            fill(j1, vol1); // fill when totally empty
            stepCount++;
        }

        if (vol2 == t || vol1 == t)
            break;

        transfer(vol1, j2, vol2);
        stepCount++; // increment steps whenever performing an operation on jugs

        if (vol2 == t || vol1 == t)
            break;

        if (vol2 == j2)
        {
            empty(vol2); // if 2 is full empty it
            stepCount++;
        }

        if (stepCount > 100) // too many steps
            return -1;
    }
    return stepCount;
}

int yTOx(int j1, int j2, int t)
{
    int vol1, vol2; // volumes stores in the two jugs
    vol1 = vol2 = 0;
    int stepCount = 0;

    while (vol1 != t || vol2 != t)
    {
        if (vol2 == 0)
        {
            fill(j2, vol2); // fill when totally empty
            stepCount++;
        }

        if (vol1 == t || vol2 == t)
            break;

        transfer(vol2, j1, vol1);
        stepCount++; // increment steps whenever performing an operation on jugs

        if (vol2 == t || vol1 == t)
            break;

        if (vol1 == j1)
        {
            empty(vol2); // if 2 is full empty it
            stepCount++;
        }

        if (stepCount > 100) // too many steps
            return -1;
    }
    return stepCount;
}

int waterJug(int j1, int j2, int target = 1)
{

    int ans1 = xTOy(j1, j2, target);
    int ans2 = yTOx(j1, j2, target);

    if (ans1 == -1)
        return ans2; // if this is also -1 then we have to handle it in main()
    else if (ans2 == -1)
        return ans1; // if this is also -1 then we have to handle it in main()
    else if (ans1 < ans2)
        return ans1;
    return ans2;
}

int main()
{
    int x, y, z;
    try
    {
        cout << "Enter the the capacities of two jugs x and y (x < y) ";
        cin >> x >> y;
        cout << "What quantity of water to measure ? ";
        cin >> z;

        if (x < 0 || y < 0 || z < 0)
            throw -1;
    }
    catch (int)
    {
        cout << "Impractical values :/";
        return 0;
    }
    int steps = waterJug(x, y, z);
    if (steps != -1)
        cout << steps;
    else
        cout << "Solution too lng, or not possible...";
    return 0;
}
