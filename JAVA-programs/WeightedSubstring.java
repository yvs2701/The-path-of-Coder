class WeightedSubstring {
    public static void main(String[] args) {
        // solves the weighted substring problem in O(n) time
        String P = "acbacbacaa", Q = "12300045600078900012345000";
        int k = 3;
        int start = 0, end = 0, sum = Q.charAt(P.charAt(end) - 'a') - '0', length = P.length();
        while (start < length && end < length) {
            if (sum < k) {
                System.out.print(P.substring(start, end + 1) + " ");
                end++;
                if (end == length)
                    break;
                sum += Q.charAt(P.charAt(end) - 'a') - '0';
            } else {
                sum -= Q.charAt(P.charAt(start) - 'a') - '0';
                start++;
            }
            if (start > end) {
                // we kept incrementing start since sum > k but now it crossed end
                // if end < length then move
                end++;
                sum += Q.charAt(P.charAt(end) - 'a') - '0';
            }
        }
        System.out.println();
    }
}
