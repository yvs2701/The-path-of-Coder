import java.util.Scanner;
class findLetter_Infinite
{ //to check number of a's in a string at n, string can be repeated infinite times
     static long repeatedString(String s, long n) {
        /*there is a brute force approach too but we are smart people so we wont use that*/
        long count=0;
        long len=s.length();
        long l=(n/len);
       if(l==0)
        {   len=n;
            for(int i=0;i<len;i++)
            if(s.charAt(i)=='a')
            count++;
            return count;
        }
        else{
            for(int i=0;i<len;i++)
            if(s.charAt(i)=='a')
            count++;
            count=l*count;
            String rem=s.substring(0,(int)(n-l*len));
            for(int i=0;i<rem.length();i++)
            if(s.charAt(i)=='a')
                count++;
            return count;
        }
    }
    public static void main(String ar[]){
        Scanner sc=new Scanner(System.in);
        String str=sc.nextLine();
        long n=sc.nextLong();
        System.out.println("Number of a\'s are "+repeatedString(str,n));
        sc.close();
    }
}
/*this Q qas in Hackerrank in warmup challenges but my answer was wrong coz i was not able
to use long for bigger outputs this program works till int's domain
example in which this program failed - str="a" and n=10000000000*/