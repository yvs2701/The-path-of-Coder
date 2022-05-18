class RemainderTheorem {
    public static void main(String[] args) {
        /* Given many congruent equations:
        X1 = a1 % m1
        X2 = a2 % m2
        X3 = a3 % m3
        ...
        Xn = an % mn
        
        Find a single X = summation(a[i] * M[i] * _M[i]) % product(m) ;
        */
        int[] a = { 2, 3, 2 };
        int[] m = { 3, 5, 7 };
        int X = 0, l = a.length;
        int M[] = new int[l];
        int _M[] = new int[l];

        // finding _m (product _m)
        int _m = 1;
        for (int i : m)
            _m *= i;

        // finding M[]
        for (int i = 0; i < l; i++)
            M[i] = _m / m[i];

        //finding _M[]
        for (int i = 0; i < l; i++) {
            int c = 1;
            c = 1 % m[i]; // OPTIMIZE: is 1 % anything = 1 ?
            for (int j = 1; j < m[i]; j++)
                if ((j * M[i]) % m[i] == c) {
                    _M[i] = j;
                    break;
                }
        }

        // calculating X
        for (int i = 0; i < l; i++)
            X += a[i] * M[i] * _M[i];
        // final value of X
        X = X % _m;
        System.out.println(X);
    }
}
