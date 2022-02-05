class patterns {
  public static void main(String ar[]) {
    // character triangle
    char ch, ch1;
    for (ch = 'e'; ch >= 'a'; ch--) {
      for (ch1 = 'e'; ch1 >= ch; ch1--)
        System.out.print(" ");
      for (ch1 = 'a'; ch1 <= ch; ch1++)
        System.out.print(ch1);
      System.out.println();
    }
    int n = 5;
    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= n - i; j++) {
        System.out.print(" ");
      }
      for (int j = 1; j <= i; j++) {
        System.out.print(j);
      }
      System.out.println();
    }
    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= n - i; j++) {
        System.out.print(" ");
      }
      for (int j = 1; j <= i; j++) {
        System.out.print(j + " "); // printing a pyramid patter without using extra loop
      }
      System.out.println();
    }
    // prints an arrow pattern (triangle + stick)
    n = 13; // n SHOULD BE > 8 to print a nice looking arrow
    // print the triangle for the arrow
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

    // print the stick of the arrow
    // by leaving (base of triangle)/2 spaces from the left, of
    // width = (base of triangle)/2
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
  }
}