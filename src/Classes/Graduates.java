package Classes;

import DatabaseConnector.Database;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

//Inclusive
public class Graduates {
    private int graduate_id;
    private String first_name;
    private String last_name;
    private String patronymic;
    private String email;
    private String date_of_birth;
    private int registration_year;
    private long phone;
    long max = 9223372036854775807L;//Inclusive

    public Graduates(int graduate_id, String first_name, String last_name, String patronymic, String email, String date_of_birth, int registration_year, long phone) {
        this.graduate_id = graduate_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.patronymic = patronymic;
        this.email = email;
        this.date_of_birth = date_of_birth;
        this.registration_year = registration_year;
        this.phone = phone;

    }


    public Graduates() {

    }

    public Graduates(String username) {
        //this.email = email;
    }
    public int getGraduate_id() {
        return graduate_id;
    }

    public void setGraduate_id(int graduate_id) {
        this.graduate_id = graduate_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(String date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public int getRegistration_year() {
        return registration_year;
    }

    public void setRegistration_year(int registration_year) {
        this.registration_year = registration_year;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public boolean createGraduate(String first_name, String last_name, String patronymic, String email, String date_of_birth, int registration_year, long phone) throws SQLException, IOException {
        long max = 9223372036854775807L;//Inclusive
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        conn = Database.getConnection();


        try {
            pst = conn.prepareStatement("SELECT setval('graduates_graduate_id_seq', max(graduate_id)) " +
                    "FROM graduates;");
            pst.executeQuery();
            pst = conn.prepareStatement("INSERT INTO graduates(first_name, last_name, patronymic, email, date_of_birth, registration_year, phone) " +
                    "VALUES ('" + first_name + "', '" + last_name + "','" + patronymic + "' ,'" + email + "' ,'" + date_of_birth + "' ,'" + registration_year + "' ,'" + phone + "' )");
            int i = pst.executeUpdate();
            return i > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean deleteGraduate(int id)throws SQLException, IOException {
        Connection con= null;
        PreparedStatement preparedStatement= null;
        //ResultSet resultSet = null;
        con = Database.getConnection();

        try {
            preparedStatement = con.prepareStatement("DELETE " +
                    "FROM graduates " +
                    "WHERE graduate_id=?");
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

    public void DisplayAllGrads() throws SQLException, IOException {
        Database database = new Database();
        Connection con = null;
        con = Database.getConnection();
        try{
        String sql = "SELECT * FROM graduates";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        System.out.println("Graduate id | First Name | Last Name | Patronymic | Email | Date of birth | Registration year | Phone ");

        while (rs.next()) {
            System.out.println(rs.getString(1)+" | "+rs.getString(2)+" | "+rs.getString(3)+" | "+
                    rs.getString(4)+ " | "+rs.getString(5)+ " | " +rs.getString(6)+ " | " +rs.getString(7)+ " | " +rs.getString(8));
        }
        }catch (Exception e){
            e.printStackTrace();
        }


    }
    public void DisplayOneGraduate(int graduate_id) throws SQLException, IOException {
        Database database = new Database();
        Connection con = null;
        con = Database.getConnection();
        try {


            String sql = "SELECT * FROM graduates where graduate_id= ?";
            PreparedStatement st = con.prepareStatement(sql);
            st.setInt(1, graduate_id);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt(1) + ", Name: " + rs.getString(2) + ", Surname: " + rs.getString(3) + ", Patronymic: " +
                        rs.getString(4) + ", Email: " + rs.getString(5) + ", Date of birth: " + rs.getDate(6) + ", Registration year:" + rs.getInt(7) + ", Phone: " + rs.getLong(8));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }


    public void UpdateGradField(String fieldname, String newvalue, int id) throws SQLException, IOException {
        Database database = new Database();
        Connection con = null;
        con = Database.getConnection();
        try {


            String sql = "UPDATE graduates SET " + fieldname + " = ? WHERE graduate_id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, newvalue);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            System.out.println("update");
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void UpdateRegYear(String fieldname, int newvalue, int id) throws SQLException, IOException {
        Database database = new Database();
        Connection con = null;
        con = Database.getConnection();
        try {
            String sql = "UPDATE graduates SET " + fieldname + " = ? WHERE graduate_id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, newvalue);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            System.out.println("update");
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void UpdatePhone(String fieldname, long newvalue, int id) throws SQLException, IOException {
        Database database = new Database();
        Connection con = null;
        con = Database.getConnection();
        try {
            String sql = "UPDATE graduates SET " + fieldname + " = ? WHERE graduate_id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setLong(1, newvalue);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            System.out.println("update");
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void UpdateBirthday(String fieldname, String newvalue, int id) throws SQLException, IOException {
        Database database = new Database();
        Connection con = null;
        con = Database.getConnection();
        try {
            String sql = "UPDATE graduates SET " + fieldname + " = ? WHERE graduate_id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setDate(1, Date.valueOf(newvalue));
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            System.out.println("update");
        }catch (Exception e){
            e.printStackTrace();
        }

    }

        public ArrayList getGraduatesInfo(String email) throws SQLException, IOException {
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        ArrayList userinfo = new ArrayList<>();
        conn = Database.getConnection();
        try {
            pst = conn.prepareStatement("SELECT * " +
                    "FROM graduates " +
                    "WHERE email=?");
            pst.setString(1, email);

            rs = pst.executeQuery();
            if (rs.next()) {
                userinfo.add(" First name:" + rs.getString("first_name"));
                userinfo.add(" Last name:" + rs.getString("last_name"));
                userinfo.add(" Patronymic:" +rs.getString("patronymic"));
                userinfo.add(" Email: " +rs.getString("email"));
                userinfo.add(" Date of birth:" +rs.getString("date_of_birth"));
                userinfo.add(" Registration year: " +rs.getInt("registration_year"));
                userinfo.add(" Phone: " +rs.getLong("phone"));
                return userinfo;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
        //, last_name, patronymic, email;
    }
    public boolean GraduateExist(int id) throws SQLException, IOException {
        Database database = new Database();
        Connection conn = null;
        conn= Database.getConnection();
        ResultSet rs = null;
        try{
            String sql= "select graduate_id from graduates where graduate_id=?";
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

