package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresConnection { public static final String HOSTNAME = "localhost";
    public static final String PORT = "5432";
    public static final String DBNAME = "postgres";
    public static final String USERNAME = "postgres";
    public static final String PASSWORD = "123456";

    /**
     * Get connection to postgresql Server
     *
     * @return Connection
     */
    public static Connection getConnection() {

        // Create a variable for the connection string.
        //String connectionUrl = "jdbc:postgresql://" + HOSTNAME + ":" + PORT + "databaseName=" + DBNAME;
        String connectionUrl = "jdbc:postgresql://localhost:5432/postgres";
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(connectionUrl, USERNAME, PASSWORD);
        }
        // Handle any errors that may have occurred.
        catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(getConnection());
    }
}


