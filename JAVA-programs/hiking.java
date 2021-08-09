/*An avid hiker keeps meticulous records of their hikes. During the last hike that took exactly 'steps' steps, 
for every step it was noted if it was an uphill,U, or a downhill, D step. 
Hikes always start and end at sea level, and each step up or down represents a 1 unit change in altitude. We define the following terms:
 A mountain is a sequence of consecutive steps above sea level, starting with a step up from sea level and ending with a step down to sea level.
 A valley is a sequence of consecutive steps below sea level, starting with a step down from sea level and ending with a step up to sea level.
Given the sequence of up and down steps during a hike, find and print the number of valleys walked through.
Example
Steps =8 path=DDUUUUDD
The hiker first enters a valley  units deep. Then they climb out and up onto a mountain  units high. 
Finally, the hiker returns to sea level and ends the hike.*/
import java.util.Scanner;
class hiking
{   static int countValley(int steps, String path) {
    boolean check=true;
    char ch;
    int ucnt=0,dcnt=0,v=0;
    for(int i=0;i<steps;i++)
    {ch=path.charAt(i);
     if(ucnt==dcnt)
     check=true;
     if(check && ch=='D')
     v++;
     if (ch=='D'){
         dcnt++;
         check=false;
     }
     else if(ch=='U') {
         ucnt++;
         check=false;
     }            
    }
    return v;
    }
    public static void main(String ar[])
    {
        Scanner sc=new Scanner(System.in);
        int steps=sc.nextInt();
        sc.nextLine();
        String path=sc.nextLine();
        if (path.length()!=steps)
        System.out.println("You counted wrong check your records !");
        else
        System.out.println("Number of valleys traversed : "+countValley(steps,path));
        sc.close();
    }
}