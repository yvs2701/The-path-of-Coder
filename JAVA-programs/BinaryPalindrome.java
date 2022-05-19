import java.util.Scanner;

class BinaryPalindrome {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextInt();
        sc.close();
        System.out.println(bin_plaindrome(n));
    }

    private static boolean bin_plaindrome(long n) {
        long left = 1, right = (Integer.SIZE / 8) * 8;

        while (left < right) {
            if (kthbit(n, left) != kthbit(n, right))
                return false;
            left++; right--;
        }
        return true;
    }

    private static int kthbit(long n, long k) { // returns if kthbit is 1 or 0
        return (n & (1 << (k - 1))) == 1 ? 1 : 0;
    }
}
