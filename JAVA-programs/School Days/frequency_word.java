import java.util.*;
class frequency_word
{ public static void main(String ar[])
{ Scanner sc=new Scanner(System.in);
  System.out.println("Enter a sentence and then a word to find ");
  String str=sc.nextLine();
  String wrd=sc.nextLine();
  sc.close();
  int i,f=0,l=str.length();
  for(i=0;i<l-(wrd.length());i++)
   if(wrd.equals(str.substring(i,i+wrd.length())))
    f++;
 System.out.println("Frequency "+f);
}}
