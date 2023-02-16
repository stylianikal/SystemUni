package Classes;

import DatabaseConnector.Database;

import java.io.IOException;
import java.sql.*;

public class School {
    public int school_id;
    public String school_name;

    public School(int school_id, String school_name) {
        this.school_id = school_id;
        this.school_name = school_name;
    }
    public School(){

    }

    public int getSchool_id() {
        return school_id;
    }

    public void setSchool_id(int school_id) {
        this.school_id = school_id;
    }

    public String getSchool_name() {
        return school_name;
    }

    public void setSchool_name(String school_name) {
        this.school_name = school_name;
    }


    public boolean  AddSchool(String school_name) throws SQLException, IOException {
        Database database = new Database();
        Connection con= null;
        PreparedStatement ps = null;
        con= Database.getConnection();
        try
        {
            ps = con.prepareStatement("SELECT setval('schools_school_id_seq', max(school_id)) " +
                    "FROM schools;");
            ps.executeQuery();
            ps = con.prepareStatement("INSERT INTO schools(school_name) " +
                    "VALUES ('" + school_name + "' )");
            int i = ps.executeUpdate();
            return i > 0;
        } catch (Exception e) {
            e.printStackTrace();

            return false;
        }

    }
    public boolean DeleteSchool(int id)throws SQLException, IOException {
        Connection con= null;
        PreparedStatement preparedStatement= null;
        //ResultSet resultSet = null;
        con = Database.getConnection();

        try {
            preparedStatement = con.prepareStatement("DELETE " +
                    "FROM schools " +
                    "WHERE school_id=?");
            preparedStatement.setInt(1, id);
            int i = preparedStatement.executeUpdate();
            return i > 0;
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("Something went wrong!");
        }

        return false;
    }
    public void DisplayAllSchools() throws SQLException, IOException {
        Database database = new Database();
        Connection con = null;
        con = Database.getConnection();
        try{

        String sel = "SELECT * FROM schools";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sel);
        System.out.println("School id | School Name");

        while (rs.next()) {
            //System.out.println("List of schools:");
            System.out.println(rs.getString(1)+" | "+rs.getString(2));
        }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void UpdateSchool(String schoolname, int school_id) throws SQLException, IOException {
        Database database = new Database();
        Connection con = null;
        con = Database.getConnection();
        try {

            String sql = "UPDATE schools SET school_name  = ?  WHERE school_id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, schoolname);
            preparedStatement.setInt(2, school_id);
            preparedStatement.executeUpdate();
            System.out.println("update school name.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Something went wrong!");


        }
    }
        public void FindSchool(int id) throws SQLException, IOException {
            Database database = new Database();
            Connection con = null;
            con = Database.getConnection();
            try {

                String sql = "SELECT schools.school_id, school_name FROM graduation g" +
                        " JOIN schools ON g.school_id=schools.school_id  WHERE g.graduate_id = ?";
                PreparedStatement preparedStatement = con.prepareStatement(sql);
                preparedStatement.setInt(1, id);

                ResultSet rs= preparedStatement.executeQuery();
                System.out.println("School id | School name");

                while (rs.next()) {
                    System.out.println(rs.getInt(1) + " | " + rs.getString(2));
                }
            }catch (Exception e){
                e.printStackTrace();
                System.out.println("Something went wrong!");

            }

    }
    public boolean SchoolExist(int id) throws SQLException, IOException {
        Database database = new Database();
        Connection conn = null;
        conn= Database.getConnection();
        ResultSet rs = null;
        try{
            String sql= "select school_name from schools where school_id=?";
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if(rs.next()){
                return true;
            }
            else {
                return false;
            }
        }catch (SQLException e){
            e.printStackTrace();

        }
        return false;
    }
}
