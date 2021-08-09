import java.util.*;
class nth_prime
{ public static void main(String ar[])
{ Scanner sc=new Scanner(System.in);
  System.out.print("Enter how many numbers do you want ");
  int n=sc.nextInt();
  int prime[]=new int[n];
  int c=0,i,j,k=0;
  for(i=1;n>0;i++)
 { c=0;
   for(j=1;j<=i;j++)
   if(i%j==0)
   c++;
   if(c==2)
  { prime[k++]=i;
    n--;
  }
 }
 for(i=0;i<prime.length;i++)
 System.out.print(prime[i]+" ");
}}