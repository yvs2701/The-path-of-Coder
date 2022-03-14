import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class App {
    public static void main(String[] args) throws Exception {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CitiesDB", "root", "root");

            Statement stmt = con.createStatement();
            // create table
            stmt.executeUpdate("CREATE TABLE Cities(name varchar(255) not null, population int not null, districts int not null, state varchar(255) not null)");
            
            // insert records
            stmt.executeUpdate("INSERT INTO Cities VALUES('City_1', 188012, 18, 'State_1')");
            stmt.executeUpdate("INSERT INTO Cities VALUES('City_2', 200015, 24, 'State_2')");
            stmt.executeUpdate("INSERT INTO Cities VALUES('City_3', 198970, 20, 'State_3')");
            stmt.executeUpdate("INSERT INTO Cities VALUES('City_4', 300112, 30, 'State_4')");

            // cities with population < 200000
            ResultSet rs = stmt.executeQuery("select population, districts, state from Cities where population < 200000");

            while (rs.next()) {
                // 1stand 3rd column (population and state) is string districts is int
                System.out.println(rs.getString(1) + "  \'" + rs.getInt(2) + "\'  " + rs.getString(3));
            }
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
