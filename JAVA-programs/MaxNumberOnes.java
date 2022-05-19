import java.util.Scanner;

class MaxNumberOnes {
    public static void main(String[] args) {
        /* Given an integer input. You can set only one bit. What is the maximum of number of consecutive ones you can find.
        Input: 1775 (11011101111)
        Output: 8
        Logic: 11011'1'1111 = 8 number of 1s after flipping 0 at index 6 
        Input: 71
        Output: 4
        Input: 12
        Output: 3
        Input: 15
        Output: 4 */
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.close();
        String s = Integer.toBinaryString(n);
        System.out.println(s);
        int l = s.length(), count = 0, maxCount = 0, flippedIndex = -1;
        for (int i = 0; i < l; i++) {
            if (s.charAt(i) == '1')
                count++;
            else if (flippedIndex == -1) {
                count++;
                flippedIndex = i;
            }
            else {
                count = i - flippedIndex;
                flippedIndex = i;
            }
            if (count >= maxCount)
                maxCount = count;
            }
        System.out.println("Max number of 1s after a flipping single bit: " + maxCount);
    }
}
