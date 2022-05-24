class MoveHyphens {
    public static void main(String[] args) {
        String str = "- this -- is -- some - - - string.";
        char[] s = str.toCharArray();
        char temp;
        int i = str.length() - 1;
        for(int j = i; j >=0; j--) {
            if (s[j] != '-') {
                temp = s[j];
                s[j] = s[i];
                s[i--] = temp;
            }
        }
        for (char c : s) {
            System.out.print(c);
        }
        System.out.println();
    }
}
