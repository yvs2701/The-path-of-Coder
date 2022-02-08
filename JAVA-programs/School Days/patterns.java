class patterns {
    public static void main(String args[]) {
        // character triangles
        for (char ch = 'e'; ch >= 'a'; ch--) {
            for (char c = ch; c >= 'a'; c--)
                System.out.print(c);
            for (char c = 'e'; c > ch; c--)
                System.out.print("  "); // printing double space !
            for (char c = 'a'; c <= ch; c++)
                System.out.print(c);
            System.out.println();
        }
        for (char ch = 'b'; ch <= 'e'; ch++) {
            for (char c = ch; c >= 'a'; c--)
                System.out.print(c);
            for (char c = 'e'; c > ch; c--)
                System.out.print("  "); // printing double space !
            for (char c = 'a'; c <= ch; c++)
                System.out.print(c);
            System.out.println();
        }
        // number pyramid
        int n = 5;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n - i; j++)
                System.out.print(" ");
            for (int j = 1; j <= i; j++)
                System.out.print(j + " "); // printing a pyramid pattern without using extra loop
            System.out.println();
        }
        // prints an upward pointing arrow pattern (triangle + stick)
        n = 13; // n SHOULD BE > 8 to print a nice looking arrow
        // Triangle part
        int triangle_height = n / 3;
        for (int i = 1; i <= triangle_height; i++) {
            for (int j = 1; j <= triangle_height - i; j++)
                System.out.print("  ");
            for (int j = 1; j <= i; j++)
                System.out.print("* ");
            for (int j = i - 1; j > 0; j--)
                System.out.print("* ");

            System.out.println();
        }
        // base will always be odd numbered: (2 * triangle_height - 1) stars at the base

        // Stick part
        // by leaving (base of triangle)/2 spaces from the left (width = (base of triangle)/2)
        int stick_height = n - triangle_height;
        for (int i = 1; i <= stick_height; i++) {
            // (base of triangle)/2 - 1
            // -1 because java rounds up int division, hence used < (not <=)
            for (int j = 1; j < (2 * triangle_height - 1) / 2; j++)
                System.out.print("  ");
            // stick width should also be odd numbered hence ... + 1
            for (int j = 1; j <= triangle_height / 2 + 1; j++)
                System.out.print("* ");

            System.out.println();
        }

        /* print:
         *         1
         *       6 1 2
         *     5 6 1 2 3
         *   4 5 6 1 2 3 4
         * 3 4 5 6 1 2 3 4 5
         *   7 8 9 4 3 2 1
         *     7 8 3 2 1
         *       7 2 1
         *         1
         */
        for (int i = 1; i <= 5; i++) {
            // spaces
            for (int j = 5; j > i; j--)
                System.out.print("  ");
            // top left triangle quadrant
            for (int j = 7 - i; j < 6; j++)
                System.out.print(j + 1 + " ");
            // top right triangle quadrant
            for (int j = 1; j <= i; j++)
                System.out.print(j + " ");
            System.out.println();
        }
        for (int i = 4; i > 0; i--) {
            // spaces
            for (int j = 5; j > i; j--)
                System.out.print("  ");
            // bottom left triangle quadrant
            for (int j = 7; j <= (i + 5); j++)
                System.out.print(j + " ");
            // bottom right triangle quadrant
            for (int j = i; j > 0; j--)
                System.out.print(j + " ");
            System.out.println();
        }
    }
}