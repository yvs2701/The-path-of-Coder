class MaxEqlbmSum {
    public static void main(String[] args) {
        int[] arr = { -2, 5, 3, 1, 2, 6, -4, 2 };
        int pre_sum = arr[0], total_sum = 0, max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++)
            total_sum += arr[i];
        for (int i = 1; i < arr.length; i++) {
            pre_sum += arr[i];
            if ((pre_sum == total_sum - pre_sum + arr[i]) && pre_sum > max)
                max = pre_sum;
        }
        if (max == Integer.MIN_VALUE)
            System.out.println("No equillibrium found !");
        else
        System.out.println("Max equillibrium sum: " + max);
    }
}
