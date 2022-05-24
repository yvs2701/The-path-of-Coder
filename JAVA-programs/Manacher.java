class Manacher {
    public static void main(String[] args) {
        String str = "acbacbaabcabcbc";
        int start = 0, end = 0;
        int max_start = 0, max_end = 0;
        for (int i = 1; i < str.length() - 1; i++) {
            start = i;
            end = i + 1;
            while (start >= 0 && end < str.length() && str.charAt(start) == str.charAt(end)) {
                start--;
                end++;
            }
            start++;
            end--;
            if (end - start > max_end - max_start) {
                max_start = start;
                max_end = end;
            }
        }
        System.out.println("Max. palindromic substring:" + str.substring(max_start, max_end + 1));
    }
}
