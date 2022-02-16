import java.util.Collection;
import java.util.function.Predicate;
import java.util.ArrayList;

class streamsExample {
    private static <T> int countElem(Collection<T> collection) {
        Predicate<T> predicate = element -> (element instanceof Integer) && isOdd(element) && isPrime(element);
        return (int) collection.stream().filter(predicate).count();
    }

    private static <T> boolean isPrime(T element) {
        int n = (int) element;
        for (int i = 2; i < n; i++)
            if (n % 2 == 0)
                return false;
        return true;
    }

    private static <T> boolean isOdd(T element) {
        return ((int)element & 1) == 1;
    }

    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<Integer>();
        arr.add(1);
        arr.add(2);
        arr.add(3);
        arr.add(4);
        arr.add(5);

        System.out.println((countElem(arr)));
    }
}
