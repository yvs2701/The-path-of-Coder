import java.util.*;
class pronic
{ public static void main(String ar[])
{ Scanner sc=new Scanner(System.in);
  int n=sc.nextInt();
  int p=-1;
  for(int i=1;i<=n;i++)
  if(i*(i+1)==n)
  { p++;
    break;
 }
 if(p==-1)
 System.out.println("not pronic number ");
 else
 System.out.print("pronic number");
 sc.close();
}
}
