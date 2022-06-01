import java.util.Scanner;

class ForestFire {
    /* Roco is an island near Africa which is very prone to forest fire. Forest fire is such that it destroys the complete forest.
    Not a single tree is left.This island has been cursed by God , and the curse is that whenever a tree
    catches fire, it passes the fire to all its adjacent tree in all 8 directions,North, South, East, West, North-East,
    North-West, South-East, and South-West.And it is given that the fire is spreading every minute in the given manner,
    i.e every tree is passing fire to its adjacent tree.Suppose that the forest layout is as follows where T denotes tree and W
    denotes water. Your task is that given the location of the first tree that catches fire, determine how long would it take
    for the entire forest to be on fire. You may assume that the lay out of the forest is such that the whole forest will
    catch fire for sure and that there will be at least one tree in the forest.
    
    Input Format:
        First line contains two integers giving the size of the forest in terms of the number of rows and columns.
        The next line contains two integers giving the coordinates of the first tree that catches the fire.
        The following integers give the position of the Tree and Water in the ith row of the forest.
    Output Format:
        Single integer indicating the number of minutes taken for the entire forest to catch fire.
    Input:
        3 3
        1 3
        W T T
        T W W
        W T T
    Output:
        5
    Explanation:
        In the second minute,tree at (1,2) catches fire,in the third minute,the tree at (2,1)
        catches fire,fourth minute tree at (3,2) catches fire and in the fifth minute the last
        tree at (3,3) catches fire. */
    public static void main(String[] args) {
        // Given that the input is such that the entire forest will burn! Find how many minutes will it take ?
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt(), n = sc.nextInt(); // row and columns in the forest

        int x = sc.nextInt() - 1, y = sc.nextInt() - 1; // coordinates (input not 0 indexing) of first tree that catches fire
        char[][] forest = new char[m][n];
        // input the forest 'W' -> Water 'T' -> Tree 'B' -> Burnt tree
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                forest[i][j] = sc.next().toUpperCase().charAt(0); // character input
        sc.close();
        forest[x][y] = 'B';
        System.out.println(burnForest(forest, x, y, 1));
    }

    private static int burnForest(char[][] forest, int x, int y, int minute) {
        // to count the number of trees burnt
        // we can burn all the surrounding trees in the same time (1 minute) concurrently
        // so if we burn a tree and we incremented the time for it we need to now stop incrementing time
        int count = 0;
        if (x + 1 < forest.length && y + 1 < forest[0].length && forest[x + 1][y + 1] == 'T') {
            forest[x + 1][y + 1] = 'B';
            count++;
            if (count == 1)
                minute++;
            minute = burnForest(forest, x + 1, y + 1, minute);
        }
        if (x - 1 < 0 && y - 1 < 0 && forest[x - 1][y - 1] == 'T') {
            forest[x - 1][y - 1] = 'B';
            count++;
            if (count == 1)
                minute++;
            minute = burnForest(forest, x - 1, y - 1, minute);
        }
        if (x + 1 < forest.length && y - 1 >= 0 && forest[x + 1][y - 1] == 'T') {
            forest[x + 1][y - 1] = 'B';
            count++;
            if (count == 1)
                minute++;
            minute = burnForest(forest, x + 1, y - 1, minute);
        }
        if (x - 1 >= 0 && y + 1 < forest[0].length && forest[x - 1][y + 1] == 'T') {
            forest[x - 1][y + 1] = 'B';
            minute = burnForest(forest, x - 1, y + 1, minute + 1);
        }
        if (y + 1 < forest[0].length && forest[x][y + 1] == 'T') {
            forest[x][y + 1] = 'B';
            count++;
            if (count == 1)
                minute++;
            minute = burnForest(forest, x, y + 1, minute);
        }
        if (y - 1 >= 0 && forest[x][y - 1] == 'T') {
            forest[x][y - 1] = 'B';
            count++;
            if (count == 1)
                minute++;
            minute = burnForest(forest, x, y - 1, minute);
        }
        if (x + 1 < forest.length && forest[x + 1][y] == 'T') {
            forest[x + 1][y] = 'B';
            count++;
            if (count == 1)
                minute++;
            minute = burnForest(forest, x + 1, y, minute);
        }
        if (x - 1 >= 0 && forest[x - 1][y] == 'T') {
            forest[x - 1][y] = 'B';
            count++;
            if (count == 1)
                minute++;
            minute = burnForest(forest, x - 1, y, minute);
        }
        return minute;
    }
}
