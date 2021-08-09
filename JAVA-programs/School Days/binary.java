import java.util.Scanner;

class binary{
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter a number in decimal base ");
        int n=sc.nextInt();
        sc.close();
        int power,j,bin=0;
    // we run a loop only 10 times as we dont want a very long loop if we are unable to find the binary of this number but u can remove it too
        for(int i = 10; n>0 && i>0; i--) {
            power=0;
            j=0;
            for(;power<n;j++)
                power=(int)Math.pow(2,j);
            if (j==1){
            bin+=1; break;}
            else if(j==2)
                bin+=10; //we know Math.pow(10,2-1) is 10
            else
            bin+=Math.pow(10,j-2);
            if(power>n)
                power=(int)Math.pow(2, j-2);
            n-=power;
        }  
        System.out.println("\n"+bin);     
    }
}
