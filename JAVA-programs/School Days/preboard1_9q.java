import java.util.*;
class preboard1_9q        //class 'perform' overload function 'claculate'
{  int calculate(int n)
 { int p=1;
   while(n>0)
  { if((n%10)!=0)
    p*=(n%10);
    n/=10;
  }
  return p;
 }
 int calculate(String str)
 {  char ch;
    int x,min=-1,i,j;
    for(i=0;i<str.length();i++)
  { ch=str.charAt(i);
    if(ch>='0'&&ch<='9') //'min' initialised as first digit of 'str'
   { min=ch;
     break;
   }
  }
   for(j=0;j<str.length();j++)
  { ch=str.charAt(j);
    if(ch>='0'&&ch<='9')
    if(ch<min)
    min=ch;
  }
  x=min-48;
  return x ;
}
public static void main(String ar[])
{ Scanner sc=new Scanner(System.in);
  Scanner ac=new Scanner(System.in);
  int n=sc.nextInt();
  String str=ac.nextLine();
  sc.close();
  ac.close();
  preboard1_9q  ob=new preboard1_9q();
  System.out.println(ob.calculate(n));
  System.out.println(ob.calculate(str));
  }}