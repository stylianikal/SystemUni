package Classes;

import DatabaseConnector.Database;

import java.io.IOException;
import java.sql.*;

public class Graduation {
    public int graduation_id;
    public int graduate_id;
    public int department_id;
    public int school_id;
    public int program_id;
    public String graduation_date;
    public float degree_grade;

    public Graduation(int graduation_id, int graduate_id, int department_id, int school_id, int program_id, String graduation_date, float degree_grade) {
        this.graduation_id = graduation_id;
        this.graduate_id = graduate_id;
        this.department_id = department_id;
        this.school_id = school_id;
        this.program_id = program_id;
        this.graduation_date = graduation_date;
        this.degree_grade = degree_grade;
    }
    public Graduation(){

    }
    public int getGraduation_id() {
        return graduation_id;
    }

    public void setGraduation_id(int graduation_id) {
        this.graduation_id = graduation_id;
    }

    public int getGraduate_id() {
        return graduate_id;
    }

    public void setGraduate_id(int graduate_id) {
        this.graduate_id = graduate_id;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }

    public int getSchool_id() {
        return school_id;
    }

    public void setSchool_id(int school_id) {
        this.school_id = school_id;
    }

    public int getProgram_id() {
        return program_id;
    }

    public void setProgram_id(int program_id) {
        this.program_id = program_id;
    }

    public String getGraduation_date() {
        return graduation_date;
    }

    public void setGraduation_date(String graduation_date) {
        this.graduation_date = graduation_date;
    }

    public float getDegree_grade() {
        return degree_grade;
    }

    public void setDegree_grade(float degree_grade) {
        this.degree_grade = degree_grade;
    }


    public boolean  Addgraduation(int graduate_id, int department_id, int school_id, int program_id, String graduation_date, float degree_grade ) throws SQLException, IOException {
        Database database = new Database();
        Connection con= null;
        PreparedStatement ps = null;
        con= Database.getConnection();
        try
        {
            ps = con.prepareStatement("SELECT setval('graduation_graduation_id_seq', max(graduation_id)) " +
                    "FROM graduation;");
            ps.executeQuery();
            ps = con.prepareStatement("INSERT INTO graduation(graduate_id, department_id,school_id, program_id, graduation_date, degree_grade) " +
                    "VALUES ('" +graduate_id+ "' , '"+department_id+ "', '" +school_id+"', '" +program_id+"', '"+graduation_date+"', '"+degree_grade+"')");
            int i = ps.executeUpdate();
            return i > 0;
        } catch (Exception e) {
            e.printStackTrace();

            return false;
        }
    }
    public boolean DeleteGraduation(int id)throws SQLException, IOException {
        Connection con= null;
        PreparedStatement preparedStatement= null;
        //ResultSet resultSet = null;
        con = Database.getConnection();

        try {
            preparedStatement = con.prepareStatement("DELETE " +
                    "FROM graduation " +
                    "WHERE graduation_id=?");
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
    public void DisplayAllGraduations() throws SQLException, IOException {
        Database database = new Database();
        Connection con = null;
        con = Database.getConnection();
        try {
            String sql = "SELECT * FROM graduation";
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            System.out.println("Graduation id | Graduate id | Department id | School id | Program id | Graduation Date | Degree grade");

            while (rs.next()) {
                System.out.println(rs.getString(1) + " | " + rs.getString(2) + " | " + rs.getString(3) + " | " +
                        rs.getString(4) + " | " + rs.getString(5) + " | " + rs.getString(6) + " | " + rs.getString(7));
            }
        }catch (Exception e){
            e.printStackTrace();
        }


    }
    public boolean GraduationExist(int id) throws SQLException, IOException {
        Database database = new Database();
        Connection conn = null;
        conn= Database.getConnection();
        ResultSet rs = null;
        try{
            String sql= "select graduation_id from graduation where graduation_id=?";
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
    public void UpdateIDs(String fieldname, int newvalue, int id) throws SQLException, IOException {
        Database database = new Database();
        Connection con = null;
        con = Database.getConnection();
        try {
            String sql = "UPDATE graduation SET " + fieldname + " = ? WHERE graduation_id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setInt(1, newvalue);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            System.out.println("update");
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void UpdateDegree(String fieldname, float newvalue, int id) throws SQLException, IOException {
        Database database = new Database();
        Connection con = null;
        con = Database.getConnection();
        try {
            String sql = "UPDATE graduation SET " + fieldname + " = ? WHERE graduation_id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setFloat(1, newvalue);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            System.out.println("update");
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void UpdateGradDate(String fieldname, String newvalue, int id) throws SQLException, IOException {
        Database database = new Database();
        Connection con = null;
        con = Database.getConnection();
        try {
            String sql = "UPDATE graduation SET " + fieldname + " = ? WHERE graduation_id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setDate(1, Date.valueOf(newvalue));
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            System.out.println("update");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void FindOneGraduationInfo(int id) throws SQLException, IOException {
        Database database = new Database();
        Connection con = null;
        con = Database.getConnection();
        try {
            //String sql = "SELECT * FROM graduation where graduate_id= ? ";
            String query = "SELECT d.department_name, s.school_name, p.program_name, p.program_type,  g.degree_grade, g.graduation_date " +
                    "FROM graduation g " +
                    "JOIN graduates gr ON g.graduate_id = gr.graduate_id " +
                    "JOIN departments d ON g.department_id = d.department_id " +
                    "JOIN schools s ON g.school_id = s.school_id " +
                    "JOIN programs p ON g.program_id = p.program_id " +
                    "WHERE gr.graduate_id = ?";
            PreparedStatement st = con.prepareStatement(query);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            System.out.println("Department  | School  | Program | Program type | Degree Grade | Graduation Date ");

            while (rs.next()) {
                System.out.println(rs.getString(1) + " | " + rs.getString(2) + " | " + rs.getString(3) + " | " +
                        rs.getString(4) + " | " + rs.getFloat(5)+ " | " + rs.getDate(6));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}