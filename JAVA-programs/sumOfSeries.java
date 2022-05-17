import java.util.Scanner;

class sumOfSeries {
    // FIND THE SUM of this series: 0, 1, 3, 6, 10, 15, 21, 28...
    // differences in every consecutive term: 1, 2, 3, 4, ...
    // so the difference are in A.P.
    // the difference of nth and (n-1)th term is (n-1)th difference = a + ((n-1)-1)d
    // Tn = Tn-1 + difference => Tn = Tn-1 + n-1
    // Tn = T1 + 1 + 2 + 3 + ... + n-1 = 0 + (n-1)n/2 = (n^2 - n)/2
    // Sn = summation{(Ti); where i = [0, n]} = summation ((n^2 - n)/2) = (summation(n^2) - summation(n))/2
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Upto what term should I find the sum ? ");
        int n = sc.nextInt();
        sc.close();
        int sum = (int)(n*(n+1)*(2*n + 1)/12 - n*(n+1)/4);
        System.out.println("Sum upto nth term: " + sum);
    }
}
