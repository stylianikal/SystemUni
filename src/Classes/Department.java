package Classes;

import DatabaseConnector.Database;

import java.io.IOException;
import java.sql.*;

public class Department {
    private int department_id;
    private String department_name;

    public Department(int department_id, String department_name) {
        this.department_id = department_id;
        this.department_name = department_name;
    }

    public Department() {

    }

    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }

    public String getDepartment_name() {
        return department_name;
    }

    public void setDepartment_name(String department_name) {
        this.department_name = department_name;
    }


    public boolean  AddDepart(String department_name) throws SQLException, IOException {
        Database database = new Database();
        Connection con= null;
        PreparedStatement ps = null;
        con= Database.getConnection();
        try
        {
            ps = con.prepareStatement("SELECT setval('departments_department_id_seq', max(department_id)) " +
                    "FROM departments;");
            ps.executeQuery();
            ps = con.prepareStatement("INSERT INTO departments(department_name) " +
                    "VALUES ('" + department_name + "' )");
            int i = ps.executeUpdate();
            return i > 0;
        } catch (Exception e) {
            e.printStackTrace();

        return false;
        }
    }
    public boolean DeleteDepartment(int id)throws SQLException, IOException {
        Connection con= null;
        PreparedStatement preparedStatement= null;
        //ResultSet resultSet = null;
        con = Database.getConnection();

        try {
            preparedStatement = con.prepareStatement("DELETE " +
                    "FROM departments " +
                    "WHERE department_id=?");
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
    public void DisplayAllDepartments() throws SQLException, IOException {
        Database database = new Database();
        Connection con = null;
        con = Database.getConnection();
        try {


        String sel = "SELECT * FROM departments";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sel);
        System.out.println("Department id | Department Name");
        while (rs.next()) {
            System.out.println(rs.getString(1)+" | "+rs.getString(2));
        }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void UpdateDepartment(String departmentname, int department_id) throws SQLException, IOException {
        Database database = new Database();
        Connection con = null;
        con = Database.getConnection();
        try {

            String sql = "UPDATE departments SET department_name  = ?  WHERE department_id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, departmentname);
            preparedStatement.setInt(2, department_id);
            preparedStatement.executeUpdate();
            System.out.println("update department name.");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("Something went wrong!");


        }

    }
    public void FindDepartment(int graduate_id) throws SQLException, IOException {
        Database dat = new Database();
        Connection con= null;
        con = Database.getConnection();
        try {
            String sql= "SELECT  departments.department_name FROM departments  JOIN graduation" +
                    " ON departments.department_id = graduation.department_id WHERE graduation.graduate_id=? ";
            PreparedStatement ps= con.prepareStatement(sql);
            ps.setInt(1, graduate_id);
            ResultSet rs= ps.executeQuery();
            System.out.println(" Department name ");

            while (rs.next()) {
                System.out.println(rs.getString(1));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public boolean DepartmentExist(int id) throws SQLException, IOException {
        Database database = new Database();
        Connection conn = null;
        conn= Database.getConnection();
        ResultSet rs = null;
        try{
            String sql= "select department_name from departments where department_id=?";
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
