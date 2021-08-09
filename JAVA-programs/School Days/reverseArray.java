import java.util.Scanner;

class reverseArray {
    static int[] reverse(int[] arr) {
        for (int i = 0; i <= arr.length / 2; i++) {
            int temp = arr[i];
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = temp;
        }
        return arr;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the size of the array then array itself :)");
        int n = sc.nextInt();
        int arr[] = new int[n];
        for (int i = 0; i < arr.length; i++)
            arr[i] = sc.nextInt();
        sc.close();
        System.out.print("Reversed array is - ");
        reverse(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+" ");
        }
    }
}