class LexicoFirstStr {
    /* Rearrange the characters of the given string to form a lexicographically first palindromic string. If no such string exists display message “no palindromic string”.
    Input : malayalam
    Output : aalmymlaa
    
    Input : apple
    Output : no palindromic string
    
    Input : baba
    Output : abba
    
    Input : accc
    Output : no palindromic string */

    /* to check if a lexicographically palindrome string is possible or not can be done in O(n)
    building such a string will take O(n^2) time (and the string will contain only a single character repeated n times) */
    public static void main(String[] args) {
        String str = "malayalam"; // example string (case insensitive, considering only alphabets)
        // str = str.toLowerCase();
        int l = str.length();
        int[] freq = new int[26]; // initially all elements = 0 and we have 26 alphabets

        // counting frequency
        for (int i = 0; i < l; i++) {
            freq[str.charAt(i) - 97]++; // ascii('a') = i
        }

        // check if palindrome is possible or not
        boolean possible = true;
        int oddFreqIndex = -1;
        if ((l & 1) == 0) { // even length
            for (int i : freq)
                if ((i & 1) == 1) { // odd frequency
                    possible = false;
                    break;
                }
        } else { // odd length
            int oddFreqCount = 0; // counts frequency of characters with odd frequency
            for (int i = 0; i < 26; i++) {
                if ((freq[i] & 1) == 1) { // odd frequency
                    oddFreqCount++;
                    oddFreqIndex = i;
                }
                if (oddFreqCount > 1) {
                    possible = false;
                    break;
                }
            }
        }

        StringBuilder output = new StringBuilder(l);
        if (!possible)
            System.out.println("No such string possible");
        else {
            // creating first half
            for (int i = 0; i < 26; i++)
                if (freq[i] != 0 && (freq[i] & 1) == 0)
                    for (int j = 0; j < freq[i] / 2; j++)
                        output.append((char) (i + 97));

            // adding middle element
            int offset = 0;
            if (oddFreqIndex != -1) {
                offset = freq[oddFreqIndex];
                for (int i = 0; i < freq[oddFreqIndex]; i++)
                    output.append((char) (oddFreqIndex + 97));
            }

            // creating last half
            for (int i = output.length() - 1 - offset; i >= 0; i--) {
                output.append(output.charAt(i));
            }
            System.out.println("Lexicographically first palindromic string: " + output);
        }
    }
}
