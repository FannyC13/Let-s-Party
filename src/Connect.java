import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
    public static java.sql.Statement getConnect() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver Loaded");
            java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/projet_java", "root",
                    "6210");
            System.out.println("Database Connected");
            java.sql.Statement stmt = (java.sql.Statement) con.createStatement();
            return stmt;
        }

        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;

    }
}
