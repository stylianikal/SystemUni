package Classes;

import DatabaseConnector.Database;

import java.io.IOException;
import java.sql.*;

public class Program {
    private int program_id;
    private String program_name;
    private String program_type;

    public Program(int program_id, String program_name, String program_type) {
        this.program_id = program_id;
        this.program_name = program_name;
        this.program_type = program_type;

    }

    public Program(){

    }

    public String getProgram_type() {
        return program_type;
    }

    public void setProgram_type(String program_type) {
        this.program_type = program_type;
    }

    public int getProgram_id() {
        return program_id;
    }

    public String getProgram_name() {
        return program_name;
    }

    public void setProgram_name(String program_name) {
        this.program_name = program_name;
    }

    public void setProgram_id(int program_id) {
        this.program_id = program_id;
    }

    public boolean  AddProgram(String program_name, String program_type) throws SQLException, IOException {
        Database database = new Database();
        Connection con= null;
        PreparedStatement ps = null;
        con= Database.getConnection();
        try
        {
            ps = con.prepareStatement("SELECT setval('programs_program_id_seq', max(program_id)) " +
                    "FROM programs;");
            ps.executeQuery();
            ps = con.prepareStatement("INSERT INTO programs(program_name, program_type) " +
                    "VALUES ('" + program_name + "', '" + program_type + "' )");
            int i = ps.executeUpdate();
            return i > 0;
        } catch (Exception e) {
            e.printStackTrace();

            return false;
        }

    }
    public boolean DeleteProgram(int id) throws SQLException, IOException {
        Connection con= null;
        PreparedStatement preparedStatement= null;
        //ResultSet resultSet = null;
        con = Database.getConnection();

        try {
            preparedStatement = con.prepareStatement("DELETE " +
                    "FROM programs " +
                    "WHERE program_id=?");
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
    public void DisplayAllPrograms() throws SQLException, IOException {
        Database database = new Database();
        Connection con = null;
        con = Database.getConnection();
        try {
        String sel = "SELECT * FROM programs";
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sel);
        System.out.println("Program id | Program Name | Program Type");

        while (rs.next()) {
            //System.out.println("List of all programs: ");
            System.out.println(rs.getString(1)+" | "+rs.getString(2)+" | "+rs.getString(3));
        }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public boolean ProgramExist(int id) throws SQLException, IOException {
        Database database = new Database();
        Connection conn = null;
        conn= Database.getConnection();
        ResultSet rs = null;
        try{
            String sql= "select program_name from programs where program_id=?";
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
    public void UpdateProgramField(String fieldname, String newvalue, int id) throws SQLException, IOException {
        Database database = new Database();
        Connection con = null;
        con = Database.getConnection();
        try {

            String sql = "UPDATE programs SET " + fieldname + " = ? WHERE program_id = ?";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setObject(1,newvalue, Types.OTHER);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            System.out.println("update");
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    public void FindProgram(int graduate_id) throws SQLException, IOException {
        Database dat = new Database();
        Connection con= null;
        con = Database.getConnection();
        try {
            String sql= "SELECT programs.program_name , programs.program_type FROM programs  JOIN graduation" +
                    " ON programs.program_id = graduation.program_id WHERE graduation.graduate_id=? ";
            PreparedStatement ps= con.prepareStatement(sql);
            ps.setInt(1, graduate_id);
            ResultSet rs= ps.executeQuery();
            System.out.println("Program name | Program type");

            while (rs.next()) {
                System.out.println(rs.getString(1) + " | " + rs.getString(2));
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}