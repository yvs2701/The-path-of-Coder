import java.util.LinkedList;
import java.util.Scanner;

class CollectingCandies {
    /* Krishna loves candies a lot, so whenever he gets them, he stores them
    so that he can eat them later whenever he wants to.
    He has recently received N boxes of candies each containing Ci candies where Ci
    represents the total number of candies in the ith box. Krishna wants to store them
    in a single box. The only constraint is that he can choose any two boxes and store
    their joint contents in AN EMPTY BOX only. Assume that there are an infinite number
    of empty boxes available.
    At a time he can pick up any two boxes for transferring and if both the boxes
    contain X and Y number of candies respectively, then it takes him exactly X + Y
    seconds of time. As he is too eager to collect all of them he has approached you to
    tell him the minimum time in which all the candies can be collected.
    
    The first input of is the number of boxes N. The second input is N integers delimited
    by whitespace denoting the number of candies in each box.
    Input:
        4 (= N)
        1 2 3 4
    Output:
        19
    4 boxes, each containing 1, 2, 3 and 4 candies respectively.Adding 1 + 2 in a new box takes 3 seconds.Adding 3 + 3 in a new box takes 6 seconds.Adding 4 + 6 in a new box takes 10 seconds.Hence total time taken is 19 seconds. There could be other combinations also, but overall time does not go below 19 seconds.
    init = 0s
    0s + 1 + 2 = 3s
    3s + 3 + 3 = 9s, 3 + 3 + 4= 10s
    pick 9s seconds since it is the minimum (1 2 and 3 are in a single box)
    9s + 6 + 4 = 19s
    TOTAL = 19s
    Input:
        5
        1 2 3 4 5
    Output:
        33
    init = 0s
    0 + 1 + 2 = 3s, ... (all will have greater values)
    3s + 3 + 3 = 9s, 3s + 3 + 4= 10s, 3s + 3 + 5 = 11s, 3s + 4 + 5 = 12s
    9s is minimum (0, 1, 2 are in the same box)
    9s + 9 + 4 = 22s, 9s + 9 + 5 = 23s, 9s + 4 + 5 = 18s
    18s is minimum (1, 2, 3 and 4, 5 are in the same box)
    18s + 6 + 9 = 33s
    TOTAL = 33s */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        LinkedList<Integer> c = new LinkedList<Integer>();
        for (int i = 0; i < n; i++)
            c.add(sc.nextInt()); // enter only +ve values
        sc.close();

        c.sort((a, b) -> Integer.compare(a, b)); // O(nlogn) time to sort

        /* Now since the array has been sorted the values are in increasing order.
        We know that the sum of two numbers which are smaller than two other numbers will be smaller too.
        Here, we are adding the time of collecting candies from boxes with least candies first. */
        int time = 0;
        int total_candies;
        while (c.size() != 1) {
            System.out.println("Candies in each box: " + c);
            total_candies = c.get(0) + c.get(1);
            c.remove(1);
            c.remove(0);
            time += total_candies;
            for (int i = 0; i <= c.size(); i++) {
                if (i == c.size()) {
                    c.add(total_candies);
                    break;
                    // used break since adding an element will increase the length of list by 1
                    // and this if block will again evaluate to true, which will start an infinite loop
                } else if (c.get(i) > total_candies) {
                    c.add(i, total_candies);
                    break;
                }
            }
        }
        System.out.println("Total candies: " + c);
        System.out.println(time);
    }
}
