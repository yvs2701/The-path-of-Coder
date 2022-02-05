import java.util.Scanner;

class OvalPattern {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter width of the oval (>=12)");
        int w = sc.nextInt(); // enter the width of the oval ( >= 12 to print a nice oval)

        int k = 0;
        while (k <= w / 4) {
            for (int i = 1; i <= w / 4 - k; i++)
                System.out.print(" ");
            // to make the oval symmetric (w/4 - k) space should be left on both the sides,
            // therefore w - 2(w/4 - k) in the following loop
            // (not w/2 - 2k as int doesn't store floating point values)
            for (int i = 1; i < w - 2 * (w / 4 - k); i++) {
                if (i == 1 || k == 0)
                    System.out.print("*");
                else
                    System.out.print(" ");
            }
            System.out.println("*");
            k++;
        }
        k = 0;
        while (k <= w / 2) {
            for (int i = 1; i <= w; i++) {
                if (i == 1 || i == w)
                    System.out.print("*");
                else
                    System.out.print(" ");
            }
            k++;
            System.out.println("");
        }
        k = w / 4;
        while (k >= 0) {
            for (int i = 1; i <= w / 4 - k; i++)
                System.out.print(" ");
            for (int i = 1; i < w - 2 * (w / 4 - k); i++) {
                if (i == 1 || k == 0)
                    System.out.print("*");
                else
                    System.out.print(" ");
            }
            k--;
            System.out.println("*");
        }
        sc.close();
    }
}
