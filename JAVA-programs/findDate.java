import java.util.Scanner;

public class findDate {
    public static void main(String[] args) {
    Scanner sc=new Scanner(System.in);
    int date=sc.nextInt();
    int month=sc.nextInt();
    int year=sc.nextInt();
    sc.close();
    final String[] DAYS = {"SUNDAY", "MONDAY","TUESDAY", "WEDNESDAY", "THURSDAY","FRIDAY","SATURDAY"};
        int a = (14 - month) / 12;
        int y = year - a;
        int m = month + 12 * a - 2;
            
        int d = (date + y + y/4 - y/100 + y/400 + (31*m)/12) % 7;
        System.out.println("The day was "+DAYS[d]);   
    }
}
