import java.util.Arrays;
import java.util.Scanner;

public class CountPairs {
    /* 
    Given an array of integers A, and an integer K find number of happy elements.
    Element X is happy if there exists at least 1 element whose difference is less than K i.e. an
    element X is happy if there is another element in the range [X-K, X+K] other than X itself.
    Input format:
        First line contains two integers N and K where N is size of the array and K is
        a number as described above. Second line contains N integers separated by space.
    Output format:
        Print a single integer denotingthe total number of happy elements.
    Input
        6 3
        5 5 7 9 15 2
    Output
        5
    Explanation
        Other than number 15, everyone has at least 1 element in the range [X-3, X+3].
        Hence they are all happy elements. Since these five are in number, the output is 5.
    */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter N (size) and K: ");
        int n = sc.nextInt(); // size of array
        int k = sc.nextInt(); // range in which the number should be present
        int arr[] = new int[n];
        System.out.print("Enter array elements: ");
        for (int i = 0; i < n; i++)
            arr[i] = sc.nextInt();
        sc.close();

        Arrays.sort(arr); // O(n.logn) time
        int count = 0, temp = arr[1];

        // travel the array and check if the number is happy or not
        for (int i = 0; i < n; i++) { // O(n) time
            if (temp >= arr[i] - k && temp <= arr[i] + k) {
                count++;
                continue;
            }

            if (i != n - 1)
                temp = arr[i + 1];
            else
                temp = arr[0];
        }
        System.out.println(count);

    }

}
