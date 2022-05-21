class Booth {
    public static void main(String[] args) {
        int A = 0, Q, Q_ = 0, M, count = 1, maxBitValue = 1, max, bits, product;
        Q = 7;
        M = 3;
        max = Q > M ? Q : M;
        // find maximum number of bits which can represent A, Q, M
        while (maxBitValue <= max) {
            maxBitValue *= 2;
            count++;
        }
        maxBitValue *= 2;
        maxBitValue--; // the max value that can be represented in (count) bits
        bits = count; // bits shall store this maximum number bit from now on

        product = 0;

        // Boothe's algorithm
        while (count > 0) {
            // Q0 = Q & 1
            if ((Q & 1) == 0 && Q_ == 1)
                A = (A + M) & maxBitValue;
            else if ((Q & 1) == 1 && Q_ == 0)
                A = A + ((~M + 1) & maxBitValue);

            // Artihmetic right shift
            Q_ = Q & 1;
            product = (A << bits) | Q;
            product = (product >> 1) | ((product >> (2 * bits - 1)) & 1) << (2 * bits - 1); // right shift with sign bit copied as it is
            product = product & ((maxBitValue << bits) | maxBitValue); // trimming extra bits


            A = (product >> bits) & maxBitValue; // get first 'bits' bits
            Q = product & maxBitValue; // get last 'bits' bits
            count--;
        }
        if (((product >> (2 * bits - 1)) & 1) == 1)
            product = ~product + 1; // take 2s complement of negative number
        System.out.println(product);
    }
}
