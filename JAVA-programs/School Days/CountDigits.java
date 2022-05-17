import java.util.Scanner;

class CountDigits {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int count = 0;
        // we use do while to count the number of digits even if the user entered "0"
        // since 0 itself is a number with 1 digit
        do {
            count++;
            n /= 10;
        } while (n > 0);
        System.out.println("Number of digits: " + count);
        sc.close();
    }
}
