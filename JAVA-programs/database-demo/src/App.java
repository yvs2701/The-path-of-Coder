import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class App {
    public static void main(String[] args) throws Exception {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            // dont use com.mysql.jdbc.Driver to avoid the following warning:
            // Loading class `com.mysql.jdbc.Driver'.
            // This is deprecated. The new driver class is `com.mysql.cj.jdbc.Driver'.
            // The driver is automatically registered via the SPI and manual loading
            // of the driver class is generally unnecessary.

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/yashv", "root", "root");
            // yashv is the name of the db

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from Student");

            while (rs.next()) {
                // second column was int, 1st and 3rd one were varchar
                System.out.println(rs.getInt(2) + "  \'" + rs.getString(1) + "\'  " + rs.getInt(3));
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
