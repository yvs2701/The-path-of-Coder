class MajorityVoting {
    // A majority elements is the one with maximum freuency in a given array
    // i.e. in a given array of length n majority element should have a frequency > n/2
    // and not = n/2, since that would mean the array has equal number of different elements as the element with max frequency
    public static void main(String[] args) {
        int[] arr = { 1, 3, 2, 2, 1, 2 }; // input
        int n = arr.length;

        // boyer moore's majority voting algorithm
        int candidate = 0, count = 1;
        for (int i = 1; i < n; i++) {
            if (count == 0) {
                candidate = i;
                count = 1;
            } else if (arr[i] == arr[candidate])
                count++;
            else
                count--;
        }
        // to make sure the majority candidate is present in the array
        count = 0;
        for (int i = 0; i < n; i++)
            if (arr[i] == arr[candidate])
                count++;
        if (count > n / 2)
            System.out.println("Majority element: " + arr[candidate]); // print this directly if you're sure the array has a majority element
        else
            System.out.println("Majority element is not present in this array");

    }
}
