import java.util.Scanner;

class bitwiseSwapNibbles {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int MAX8bitINT = 255;
        n = n & MAX8bitINT; // consider 8 bit input
        sc.close();

        // in 8 bit get left half by shifting right 4 times and vice versa for right half
        int rightHalf = (n << 4) & 255; // consider 8 bit input
        int leftHalf = (n >> 4) & 255; // consider 8 bit input
        int swappedNibbles = rightHalf | leftHalf;

        System.out.println("Input: " + n + "(" + Integer.toBinaryString(n) + ")");
        System.out.println("Left half: " + leftHalf + "(" + Integer.toBinaryString(leftHalf) + ")");
        System.out.println("Right half: " + rightHalf + "(" + Integer.toBinaryString(rightHalf) + ")");
        System.out.println("Output: " + swappedNibbles + "(" + Integer.toBinaryString(swappedNibbles) + ")");
    }
}
