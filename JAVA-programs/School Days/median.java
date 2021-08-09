import java.util.*;
class median
{ public static void main(String ar[])
{ Scanner sc=new Scanner(System.in);
  int n=sc.nextInt();
  int c=n,count=0,F=0,L=0,div;
  double avg;
  while(c>0)
{ count++;
  c/=10;
}
  div=(int)Math.pow(10,count/2);
  if(count%2==0)
{ F=n/div%10;
  L=n%div/(div/10);
  avg=(F+L)/2.0;
  System.out.print(" "+avg);
}
else
System.out.print(n/div%10);
sc.close();
}}
