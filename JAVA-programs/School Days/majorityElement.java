class majorityElement {
    public static void main(String[] args) {
        int noOfMajority = 2; // if frequency of element > n/2 then no of majority element = 1 only otherwise if frequency == n/2 then no of majority = 2
        int[] arr = { 1, 2, 3, 2, 1, 3, 1, 1, 3, 3, 1 };
        // int[] arr = { 1, 0, 1, 0, 1, 0, 1, 0 };
        int n = arr.length, count, threshold = (int) Math.ceil(n / 2);
        for (int i = 0; noOfMajority != 0 && i < n; i++) {
            count = 0;
            for (int j = i; j < n; j++)
                if (arr[j] == arr[i])
                    count++;
            if (count == threshold) {
                System.out.println(arr[i]);
                noOfMajority--;
            } else if (count > threshold) {
                System.out.println(arr[i]);
                noOfMajority = 0;
            }
        }
    }
}
