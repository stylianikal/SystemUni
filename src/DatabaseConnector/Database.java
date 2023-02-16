package DatabaseConnector;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    static Connection conn = null;
    public static Connection getConnection() throws SQLException, IOException {
        if(conn != null) return conn;
        return getConnection("universitysystem","postgres", "$tellA26");
    }

    public static Connection getConnection(String dbname, String username, String pass) throws IOException, SQLException {

    Connection conn = null;
    try {

        Class.forName("org.postgresql.Driver");
        conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + dbname + "?user=" + username + "&password=" + pass);

        if (conn !=null){
        }
        else {
            System.out.println("fail to connect");
        }
    }catch (Exception e){
        System.out.println(e);
    }
    return conn;
}
}
