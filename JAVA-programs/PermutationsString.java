class PermutationsString {
    /* FIND all permutations in a string */
    public static void main(String[] args) {
        StringBuffer str = new StringBuffer("abcd");
        permute(str, 0);
    }

    private static void permute(StringBuffer str, int start) {
        if (start == str.length())
            return;
        System.out.println(str);
        

    }
}
