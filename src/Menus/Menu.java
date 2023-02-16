package Menus;

import java.io.IOException;
import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

import Classes.*;
import DatabaseConnector.Database;

public class Menu {
    private Scanner scan = new Scanner(System.in);

    public void displayMenu1() throws SQLException, IOException {
        boolean errorInteger = true;
        boolean valid = false;
        boolean x = false;
        System.out.println(" -----------------------------------------------");
        System.out.println("                UNIVERSITY SYSTEM                ");
        System.out.println(" -----------------------------------------------");
        System.out.println("Welcome to university system!");
        System.out.println("Press 1 to log in");
        System.out.println("Press 2 to exit program");
        String username, password, departname;
        int num = 0;
        do {
            try{
                /* insert a number */
                System.out.print("Enter a number ( 1-2 ): ");
                /* This method reads the number provided using keyboard */
                num = scan.nextInt();
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                errorInteger = false;
            }catch(InputMismatchException e){
                System.out.println("You give a none integer number.");
                scan.reset();
                scan.next();
                num = 0;

            }
        }while((num<1 || num>2) || errorInteger);
        switch (num) {
            case 1:

                try {System.out.println("Log in to system.");
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    do {

                        System.out.println("Enter your username:");
                        username = scan.next();
                        System.out.println("Enter your password:");
                        password = scan.next();
                        valid = checkCredentials(username, password) ;

                    }while(!valid);
                        System.out.println("Welcome to the system ");
                        x= IsAdmin(username);

                        if(IsAdmin(username))
                        {
                            System.out.println("Hello Admin");
                            displayMenu2();
                        }
                        else {
                            System.out.println("Hello user");
                            displayMenu3(username);

                        }

                }catch ( InputMismatchException e){
                    System.out.println("You give a none integer number.");
                    scan.reset();
                    scan.next();
                }
                break;
                //System.out.println("give a department name");
                //Classes.Department dept = new Classes.Department();
                //departname = scan.next();
                //dept.AddDepart(departname);

            //menu.displayContacts();
            case 2:
                System.out.println("You will exit now from the program.");
                System.exit(0);
                break;
            
        }
    }

    private int FindGradID(String email) throws SQLException, IOException {
        Database database = new Database();
        Connection conn = null;
        conn= Database.getConnection();
        String adm= "Admin";
        ResultSet rs = null;
        try{
            //String sql = "select exists(select username, is_admin from users where username = ? and is_admin = 1)";
            String sql= "select graduate_id from graduates where email = ?  ";
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getInt(1);
            }
        }catch (SQLException e){
            e.printStackTrace();

        }
        return 0;

    }

    void displayMenu2() throws SQLException, IOException {
        Menu2 menu = new Menu2();
        menu.dsMenu2();
    }

    void displayMenu3(String username) throws SQLException, IOException {
        boolean errorInteger = true;
        int current_grad_id;
        current_grad_id= FindGradID(username);

        System.out.println(" -----------------------------------------------");
        System.out.println(" |            UNIVERSITY SYSTEM (USER)          |");
        System.out.println(" -----------------------------------------------");
        System.out.println("You can choose one of this numbers, to see your information's about!");
        System.out.println("1. General information.");
        System.out.println("2. Graduation.");
        System.out.println("3. Department.");
        System.out.println("4. School.");
        System.out.println("5. Program.");
        System.out.println("6. Career.");
        System.out.println("7. Exit.");
        int num;
        do {
            try{
                /* insert a number */
                System.out.print("Enter a number ( from 1-7): ");
                /* This method reads the number provided using keyboard */
                num = scan.nextInt();
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                errorInteger = false;
            }catch(InputMismatchException e){
                System.out.println("You give a none integer number.");
                scan.reset();
                scan.next();
                num = 0;
            }
        }while((num<1 || num>7) || errorInteger);

        String search, phone;
        switch (num) {
            case 1 :
                System.out.println("Display general Information.");
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                Graduates grads = new Graduates();
                grads.getGraduatesInfo(username);
                System.out.println(grads.getGraduatesInfo(username));
                displayMenu3(username);
                break;
            case 2:
                System.out.println("Display graduation Information.");
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                Graduation graduation = new Graduation();
                graduation.FindOneGraduationInfo(current_grad_id);
                displayMenu3(username);
                break;
            case 3:
                System.out.println("Display department.");
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                Department dept= new Department();
                dept.FindDepartment(current_grad_id);
                displayMenu3(username);
                break;
            case 4:
                System.out.println("Display school.");
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

                School school = new School();
                school.FindSchool(current_grad_id);
                displayMenu3(username);
                break;

            case 5:
                System.out.println("Display programs.");
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                Program pg= new Program();
                pg.FindProgram(current_grad_id);
                displayMenu3(username);

                break;
            case 6:
                System.out.println("Display careers.");
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                Career career = new Career();
                career.DisplayOneCareer(current_grad_id);
                displayMenu3(username);
                break;
            
            case 7:
                System.exit(0);
                break;
        }


    }
    boolean checkCredentials(String username, String password) throws SQLException, IOException {
        Database database = new Database();
        Connection conn = null;
        conn = Database.getConnection();
        ResultSet rs= null;


        try{
//        String username1 = username;
//        String password1 = password;
        String sql = "select *  from users where email = ? and password=  ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);

        rs = preparedStatement.executeQuery();
        if(rs.next() ){
            //System.out.println("user exists.");
            return true;
        }
        else{
            System.out.println("user not exist try again");
            return false;
        }
        }
        catch (SQLException e){
            e.printStackTrace();

        }
        finally {
            try {
                if(rs!= null){
                    rs.close();
                }
            }catch (SQLException e){
                e.printStackTrace();

            }
        }
    return false;
    }
    boolean IsAdmin(String username) throws SQLException, IOException {
        Database database = new Database();
        Connection conn = null;
        conn= Database.getConnection();
        String adm= "Admin";
        ResultSet rs = null;
        try{
            //String sql = "select exists(select username, is_admin from users where username = ? and is_admin = 1)";
            String sql= "select email,role from users where email = ? and role='Admin' ";
            PreparedStatement ps= conn.prepareStatement(sql);
            ps.setString(1, username);
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

