/*Emma is playing a new mobile game that starts with consecutively numbered clouds. 
Some of the clouds are thunderheads and others are cumulus. 
She can jump on any cumulus cloud having a number that is equal to the number of the current cloud plus 1 or 2. 
She must avoid the thunderheads. Determine the minimum number of jumps it will take Emma to jump from her starting postion to the last cloud. 

It is always possible to win the game.

For each game, Emma will get an array of clouds numbered 0 if they are safe or 1 if they must be avoided. 
For example, c=[0,1,0,0,0,1,0] indexed from 0...6. The number on each cloud is its index in the list so she must avoid the clouds at 
indexes 1 and 5. 
She could follow the following two paths:0>2>4>6 or 0>2>3>4>6 . The first path takes  jumps while the second takes . */
import java.util.Scanner;
public class cloudJump {
   static int jump(int[] c) {
        int jmp=0;
        int l=c.length;
        for(int i=0;i<l-1;)
        {   if(i+2<l && c[i+2]!=1)
            i+=2;
            else if(c[i+1]!=1)
            i++;
            jmp++;
        }
        return jmp;
    }
    public static void main(String ar[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the cloud path(specify length of path first)");
        int n=sc.nextInt();
        int c[]=new int[n];
        for (int i=0;i<n;i++)
        c[i]=sc.nextInt();
        System.out.println("Number of jumps : "+jump(c));
        sc.close();
    }
}