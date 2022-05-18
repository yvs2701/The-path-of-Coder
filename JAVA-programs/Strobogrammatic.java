import java.util.Scanner;

class Strobogrammatic {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        sc.close();
        char[] arr = str.toCharArray();
        int l = arr.length;
        for (int i = 0; i < l; i++) {
            if (arr[i] == '2' || arr[i] == '3' || arr[i] == '4' || arr[i] == '5' || arr[i] == '7') {
                System.out.println("Number is not strobogrammatic");
                return;
            } else if (arr[i] == '6')
                arr[i] = '9';
            else if (arr[i] == '9')
                arr[i] = '6';
        }
        for (int i = 0; i < l; i++) {
            if (str.charAt(i) != arr[l - 1 - i]) { // matches reverse and converted string (180 deg turned) with original
                System.out.println("Number is not strobogrammatic");
                return;
            }
        }
        System.out.println("Number is strobogrammatic");
    }
}
