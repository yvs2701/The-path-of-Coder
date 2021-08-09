import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;

class formatting {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double payment = scanner.nextDouble();
        scanner.close();
/*The Java Locale class object represents a specific geographic, cultural, or political region. 
It is a mechanism to for identifying objects, not a container for the objects themselves. 
A Locale object logically consists of the fields like languages, script, country, variant, extensions.*/
        NumberFormat objus=NumberFormat.getCurrencyInstance(Locale.US);
        NumberFormat objin=NumberFormat.getCurrencyInstance(new Locale("en","IN"));
        NumberFormat objch=NumberFormat.getCurrencyInstance(Locale.CHINA);
        NumberFormat objfr=NumberFormat.getCurrencyInstance(Locale.FRANCE);

        String us=objus.format(payment);
        String in=objin.format(payment);
        String ch=objch.format(payment) ;
        String fr=objfr.format(payment);
        
        System.out.println("US: " + us);
        System.out.println("India: " + in);
        System.out.println("China: " + ch);
        System.out.println("France: " + fr);
    }
}