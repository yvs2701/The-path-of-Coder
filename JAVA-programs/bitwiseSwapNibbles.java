import java.util.Scanner;

class bitwiseSwapNibbles {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int MAX8bitINT = 255; //  = 0b11111111 = 0xFF
        n = n & MAX8bitINT; // consider 8 bit input
        sc.close();

        // in 8 bit get left half by shifting right 4 times and vice versa for right half
        int rightHalf = (n << 4) & MAX8bitINT; // consider 8 bit input
        int leftHalf = (n >> 4) & MAX8bitINT; // consider 8 bit input
        int swappedNibbles = rightHalf | leftHalf; // merge both halves back

        System.out.println("Input: " + n + "(" + Integer.toBinaryString(n) + ")");
        System.out.println("Left half: " + leftHalf + "(" + Integer.toBinaryString(leftHalf) + ")");
        System.out.println("Right half: " + rightHalf + "(" + Integer.toBinaryString(rightHalf) + ")");
        System.out.println("Output: " + swappedNibbles + "(" + Integer.toBinaryString(swappedNibbles) + ")");

        /* instead of trimming halves later on by using bitwise AND
        we can also extract the first half and last half using bitwise AND
        n = sc.nextInt();
        left half = n & 0b11110000
        right half = n & 0b00001111 
        and then simply merge them by bitwise OR */
    }
}
