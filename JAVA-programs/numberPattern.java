/* Sample Input
    2 (Number of test cases)
    0 2 10 (a,b,n)
    5 3 5
Sample Output
    2 6 14 30 62 126 254 510 1022 2046 [Tn=a+b + (a+b+2b + a+b+2^2 b +...+a+b+2^n b)]
    8 14 26 50 98*/
import java.util.*;
class numberPattern{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        int t=sc.nextInt();
        while(t-->0){
            int a = sc.nextInt();
            int b = sc.nextInt();
            int n = sc.nextInt();
            int prev=a+b;
            System.out.print(prev+" ");
            for(int i=1;i<n;i++){
                int mul=((int)Math.pow(2,i))*b;
                prev+=mul;
                System.out.print(prev+" ");
            }
            System.out.println();
        } 
        sc.close();
    }
}