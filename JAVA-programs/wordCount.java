import java.util.Scanner;
class wordCount
{// this is one of the innovative methods i have discovered by myself :)
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter your string.");
        String s=sc.nextLine();
        sc.close();
        Scanner c=new Scanner(s);//a simialr thing can be done in c++ using s<<word in place of cin<<word then wrd++
        int wrd=0;
        try{
            while(true){
                c.next();
                wrd++;
            }
        }
        catch(Exception e){
            System.out.println("\nWord count :"+wrd);
            c.close();
        }
    }
}
/* ANOTHER SHORT METHOD without using libraries :-
import java.util.Scanner;

public class WorldCount {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter your string: ");
        String input = sc.nextLine();
        System.out.printf("Your word count is: %d\n", input.split(" ").length);
    }
}
*/