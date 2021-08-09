import java.util.*;
class kaprekar_num
{ public static void main(String ar[])
{ Scanner sc=new Scanner(System.in);
  System.out.println(" Enter a number\n* A kaprekar number is always even number");
  int n=sc.nextInt();
  sc.close();
  int c=n;
  int s=0;
  while(c>0)
 { s+=(c%100);
   c/=100;
 }
 if((s*s)==n)
 System.out.println("\n KAPREKAR NUMBER !");
 else
 System.out.println("\n NOT KAPREKAR NUMBER !");
 }
 }