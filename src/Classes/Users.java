package Classes;

import DatabaseConnector.Database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Users {

    public String username;
    public String password;
    public String role;

    public Users( String username, String password, String role) {

        this.username = username;
        this.password = password;
        this.role = role;
    }
    public Users(){

    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    public boolean getAllStudents(){
        return false;
    }

    public boolean createUser(String username, String password) throws SQLException, IOException {
        long max = 9223372036854775807L;//Inclusive
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        conn = Database.getConnection();
        String role="Admin";

        try {

            pst = conn.prepareStatement("INSERT INTO users(email, password,role) " +
                    "VALUES ('" + username + "', '" + password + "','" +role+ "' )");
            int i = pst.executeUpdate();
            return i > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

}
