#include <iostream>
using namespace std;

/*
*(ptr + i) = ptr[i]
*(*(ptr + i) + j) = ptr[i][j]
therefore we will use the same for accepting our array as an argument
*/
bool isSafe(int **board, int row, int col, int n)
{
    /* Check this row on left side since we can't place any queens on the right (which we haven't visited) */
    for (int i = 0; i < col; i++)
        if (board[row][i])
            return false;
    /* Check the column */
    for (int i = 0, j = col; i < row; i++)
        if (board[i][j])
            return false;
    /* Check upper diagonal on left side */
    for (int i = row, j = col; i >= 0 && j >= 0; i--, j--)
        if (board[i][j])
            return false;

    return true; // if the site is safe
}

void printBoard(int **board, int n)
{
    cout << "0 -> blank grids, 1 -> queens\n";
    for (int i = 0; i < n; i++)
    {
        for (int j = 0; j < n; j++)
            cout << board[i][j] << " ";
        cout << endl;
    }
}

bool nQueen(int **board, int row, int n) // uses backtracking
{
    if (row == n) //i.e we reached the last row + 1 (= n) of the array (since array rows range from 0 to n-1)
    {
        printBoard(board, n);
        delete[] board;
        return true;
    }

    for (int col = 0; col < n; col++)
    {
        if (isSafe(board, row, col, n)) // checking if a queen can be placed or not
        {
            board[row][col] = 1; // placed a queen
            if (nQueen(board, row + 1, n))
                return true;
            // we will backtrack if the above position will make it impossible for the next row to have any safe place
            board[row][col] = 0; // removed the queen we just placed and trying to find another place for the queen
        }
    }
    return false; // if we cant find a safe position for this row then return false
}

int main()
{
    cout << "Solve the n-queen problem for how many queens ? ";
    int n;
    cin >> n;
    int** board = new int*[n]; // allot memory = (int * n) = for n integers or rows

    for (int i = 0; i < n; i++)
        board[i] = new int[n]; // allot memory = (int * n) = for n integers in a row

    for (int i = 0; i < n; i++)
        for (int j = 0; j < n; j++)
            board[i][j] = 0;
    bool possible = nQueen(board, 0, n);

    if(possible)
        cout << "Solution possible :)";
    else
        cout << "Solution not possible :(";
    return 0;
}
/* Q)  The N Queen is the problem of placing N chess queens on an N×N chessboard so that no two queens attack each other.*/
