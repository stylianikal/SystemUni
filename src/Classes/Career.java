package Classes;

import DatabaseConnector.Database;

import java.io.IOException;
import java.sql.*;

public class Career {
    public int career_id;
    public int graduate_id;
    public String company;
    public String hob_title;
    public String employment_category;
    public String start_date;
    public String end_date;

    public Career(int career_id, int graduate_id, String company, String hob_title, String employment_category, String start_date, String end_date) {
        this.career_id = career_id;
        this.graduate_id = graduate_id;
        this.company = company;
        this.hob_title = hob_title;
        this.employment_category = employment_category;
        this.start_date = start_date;
        this.end_date = end_date;
    }
    public Career(){

    }

    public int getCareer_id() {
        return career_id;
    }

    public void setCareer_id(int career_id) {
        this.career_id = career_id;
    }

    public int getGraduate_id() {
        return graduate_id;
    }

    public void setGraduate_id(int graduate_id) {
        this.graduate_id = graduate_id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getHob_title() {
        return hob_title;
    }

    public void setHob_title(String hob_title) {
        this.hob_title = hob_title;
    }

    public String getEmployment_category() {
        return employment_category;
    }

    public void setEmployment_category(String employment_category) {
        this.employment_category = employment_category;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public boolean  AddCareer(int graduate_id, String company, String job_title, String employment_category, String start_date, String end_date ) throws SQLException, IOException {
        Database database = new Database();
        Connection con= null;
        PreparedStatement ps = null;
        con= Database.getConnection();
        try
        {
            ps = con.prepareStatement("SELECT setval('career_career_id_seq', max(career_id)) " +
                    "FROM career;");
            ps.executeQuery();
            ps = con.prepareStatement("INSERT INTO career(graduate_id, company,job_title, employment_category, start_date, end_date) " +
                    "VALUES ('" +graduate_id+ "' , '"+company+ "', '" +job_title+"', '" +employment_category+"', '"+start_date+"', '"+end_date+"')");
            int i = ps.executeUpdate();
            return i > 0;
        } catch (Exception e) {
            e.printStackTrace();

            return false;
        }
    }
    public boolean DeleteCareer(int id)throws SQLException, IOException {
        Connection con= null;
        PreparedStatement preparedStatement= null;
        //ResultSet resultSet = null;
        con = Database.getConnection();

        try {
            preparedStatement = con.prepareStatement("DELETE " +
                    "FROM career " +
                    "WHERE career_id=?");
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
    public void DisplayAllCarrers() throws SQLException, IOException {
        Database database = new Database();
        Connection con = null;
        con = Database.getConnection();
        String sql = "SELECT * FROM career";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        System.out.println("Career id | Graduate id | Company | Job Title | Employment Category | Start Date | End Date");

        while (rs.next()) {
            System.out.println(rs.getString(1)+" | "+rs.getString(2)+" | "+rs.getString(3)+" | "+
                    rs.getString(4)+ " | "+rs.getString(5)+ " | " +rs.getString(6)+ " | " +rs.getString(7));
        }


    }
    public void DisplayOneCareer(int graduate_id) throws SQLException, IOException {
        Database database = new Database();
        Connection con = null;
        con = Database.getConnection();
        try {


        String sql = "SELECT * FROM career where graduate_id= ?" ;
        PreparedStatement st = con.prepareStatement(sql);
        st.setInt(1, graduate_id);

        ResultSet rs = st.executeQuery();
        while (rs.next()) {
            System.out.println(rs.getInt(1)+", Graduate id: "+rs.getInt(2)+", Company: "+rs.getString(3)+", Job Title: "+
                    rs.getString(4)+ ", Employment Category: "+rs.getString(5)+ ", Start Date: " +rs.getDate(6)+ ", End Date: " +rs.getDate(7));
        }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void UpdateTexts(String fieldname, String newvalue, int id) throws SQLException, IOException {
        Database database = new Database();
        Connection con = null;
        con = Database.getConnection();
        try {
            String sql = "UPDATE career SET " + fieldname + " = ? WHERE graduate_id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, newvalue);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            System.out.println("update");
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void UpdateCareerDates(String fieldname, String newvalue, int id) throws SQLException, IOException {
        Database database = new Database();
        Connection con = null;
        con = Database.getConnection();
        try {
            String sql = "UPDATE career SET " + fieldname + " = ? WHERE career_id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setDate(1, Date.valueOf(newvalue));
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            System.out.println("update");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public boolean CareerExist(int id) throws SQLException, IOException {
        Database database = new Database();
        Connection conn = null;
        conn= Database.getConnection();
        ResultSet rs = null;
        try{
            String sql= "select career_id from career where career_id=?";
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
