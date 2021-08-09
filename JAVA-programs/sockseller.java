/*Alex works at a clothing store. There is a large pile of socks that must be paired by color for sale. 
Given an array of integers representing the color of each sock, determine how many pairs of socks
with matching colors there are.
For example, there are n = 7 socks with colors ar=[1,2,1,2.1,3,2]. 
There is one pair of color 1 and one of color 2. There are three odd socks left, one of each color. The number of pairs is 2.*/
import java.util.Scanner;
class sockseller {
    int sockMerchant(int n, int[] ar) {
        boolean[] check=new boolean[n];
        for(int i=0;i<n;i++)
        check[i]=false;
        int count,pair=0;
        for(int i=0;i<n;i++)
        {   count=0;
            for(int j=i;j<n;j++)
            if(ar[j]==ar[i] && !check[j])
            {count++;
             check[j]=true;
            }
            pair+=(int)count/2;
        }
        return pair;
    }
    public static void main(String args[])
    {   sockseller ob=new sockseller(); //if u dont wanna make an object directly call new className().methodName() or
    //simply call mehtodName() and make that method static so that you dont need an object to call it
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int arr[]=new int[n];
        for(int i=0;i<n;i++)
        arr[i]=sc.nextInt();
        System.out.println("Number of pairs that he could sell is : "+ob.sockMerchant(n,arr));
        sc.close();
    }
}