#include <iostream>
#include <stack>
using namespace std;

int toDig(char c) // converts a character into a digit
{
    switch (c)
    {
    case '0':
        return 0;
    case '1':
        return 1;
    case '2':
        return 2;
    case '3':
        return 3;
    case '4':
        return 4;
    case '5':
        return 5;
    case '6':
        return 6;
    case '7':
        return 7;
    case '8':
        return 8;
    case '9':
        return 9;
        // defualt : return -1; we will write it at the end of function to avoid any compiler warnings
        // no need of break statement due to return statements
    }
    return -1; // returns -1 if the character is not a digit
}

int main()
{
    stack<int> eval;
    int i; //we'll use it later
    char oprtr; // operator with higher precedence
    cin >> oprtr;
    char c = ' '; // temporary variable
    cin >> c;

    while (c != ';')
    {
        if (c == oprtr && !eval.empty())
        {
            cin >> c;
            if (toDig(c) != -1)
            {
                if (oprtr == '*')
                {
                    i = eval.top() * toDig(c);
                    eval.pop();
                    eval.push(i); // after performing this higher precedence operation push the result back
                }
                else
                {
                    i = eval.top() + toDig(c);
                    eval.pop();
                    eval.push(i); // after performing this higher precedence operation push the result back
                }
            }
            else
            {
                cout << "Invalid expression !";
                return 0;
            }
        }
        // if eval is empty amd we encounter a non-number
        else if (eval.empty() && (toDig(c) == -1))
        {
            cout << "Invalid expression !";
            return 0; // end of the program
        }
        else if (toDig(c) != -1)
            eval.push(toDig(c)); // push only numbers in the stack
        cin >> c;
    }

    // when loop encounters the end (;) we will do the remaining operations on expression
    if (oprtr == '+')
    {
        i = 1;
        while (!eval.empty())
        {
            i *= eval.top();
            eval.pop();
        }
    }
    else
    {
        i = 0;
        while (!eval.empty())
        {
            i += eval.top();
            eval.pop();
        }
    }
    cout << "RESULT: " << i;
    return 0;
}

/*Expression Evaluator 
Write a program that can evaluate arithmetic expressions consisting of single-digit integers as operands and addition/multiplication as operators.
You will receive as input two lines: 
The first line consists of only a single character - this will be either the addition operator '+' or the multiplication operator '*'.
This character will be the operator with higher precedence.
The second line contains an arithmetic expression.
The expression should obey the following rules: 1) 
All the operands are single-digit whole numbers [0,9] 2) 
Operators are either '+' or '*' 3)
The expression is terminated by ';' 
If the expression violates any of the above rules, the output should be -1.
If the expression does not violate any rules, evaluate the expression, with the condition that
the character given in the first line of input is to be treated as the operator of higher precedence. 

Sample Testcases:
Testcase 1 
Input:
*
1 + 3 * 2 ; 
Output: 7 Explanation: * is of higher precedence than +. 1+(3*2) = 7
Testcase 2 
Input:
+
1 + 3 * 2 ;
Output: 8 Explanation: + is of higher precedence than *. (1+3)*2 = 8
Testcase 3
Input:
+
2 + 3 + 4 * 2 + 1 ;
Output: 27*/