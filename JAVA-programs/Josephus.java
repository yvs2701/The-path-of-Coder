import java.util.ArrayList;
import java.util.Scanner;

class Josephus {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        sc.close();
        ArrayList<Integer> alive = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            alive.add(i + 1);
        }
        josephusProblem(alive, 0, n);
    }

    private static void josephusProblem(ArrayList<Integer> alive, int i, int n) {
        if (n == 1) {
            System.out.println("Survivor: " + alive.get(0));
            return;
        }
        if (i > n - 1)
            i = 0;
        // just to print the survivors in each turn
        System.out.print("Chance: " + alive.get(i) + " | ");
        System.out.print("Survivors: ");
        for (int j = 0; j < n; j++) {
            System.out.print(alive.get(j) + " ");
        }
        if (i + 1 > n - 1)
            i = 0;
        else
            i++;
        System.out.println("| Killed: " + alive.get(i));
        alive.remove(i);
        josephusProblem(alive, i, n - 1);
    }
}
