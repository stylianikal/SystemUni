package Menus;

import Classes.*;
import DatabaseConnector.Database;

import java.io.IOException;
import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu2 {
    private Scanner scan = new Scanner(System.in);
    void dsMenu2() throws SQLException, IOException {
        boolean errorInteger = true;
        Graduates graduates= new Graduates();
        Department department = new Department();
        School school = new School();
        Career career = new Career();
        Graduation graduation = new Graduation();
        Program program = new Program();
        System.out.println(" -----------------------------------------------");
        System.out.println("               UNIVERSITY SYSTEM                ");
        System.out.println(" -----------------------------------------------");
        System.out.println("You can choose one of this numbers!");
        System.out.println("1. Insert");
        System.out.println("2. Update");
        System.out.println("3. Delete");
        System.out.println("4. View ");
        System.out.println("5. See basic reports");
        System.out.println("6. Exit.");
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        int num;
        do {
            try {
                /* insert a number */
                System.out.print("Enter a number ( from 1-6 ): ");
                /* This method reads the number provided using keyboard */
                num = scan.nextInt();
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                errorInteger = false;
            } catch (InputMismatchException e) {
                System.out.println("You give a none integer number.");
                scan.reset();
                scan.next();
                num = 0;
            }
        } while ((num < 1 || num > 6) || errorInteger);
        //Classes.Graduates graduates = new Classes.Graduates();

        String search, search2;
        switch (num) {
            case 1: {
                System.out.println(" INSERT ");
                System.out.println("1. Graduate ");
                System.out.println("2. Career ");
                System.out.println("3. Department ");
                System.out.println("4. School ");
                System.out.println("5. Program ");
                System.out.println("6. Graduation.");
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                int num2;
                do {
                    try {
                        /* insert a number */
                        System.out.print("Enter a number ( from 1-6 ): ");
                        /* This method reads the number provided using keyboard */
                        num2 = scan.nextInt();
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        errorInteger = false;
                    } catch (InputMismatchException e) {
                        System.out.println("You give a none integer number.");
                        scan.reset();
                        scan.next();
                        num2 = 0;
                    }
                } while ((num2 < 1 || num2 > 6) || errorInteger);
                switch (num2) {
                    case 1:{
                        System.out.println("Add new graduate.");
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        AddGraduate();
                        dsMenu2();
                    }
                    case 2:{
                        System.out.println("Add career to graduate");
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        AddCareer();
                        dsMenu2();
                    }
                    case 3:{
                        System.out.println("Add new Department");
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        do {
                            System.out.print("Give the name of the department you want to add: ");
                            search = scan.next();
                        } while (search.isEmpty());
                        Department dp = new Department();
                        dp.AddDepart(search);
                        dsMenu2();
                    }
                    case 4 :{
                        System.out.println("Add new School");
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        do {
                            System.out.print("Give the name of the school you want to add: ");
                            search = scan.next();
                        } while (search.isEmpty());
                        School school3 = new School();
                        school3.AddSchool(search);
                        dsMenu2();
                    }
                    case 5:{
                        System.out.println("Add new program");
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        do {
                            System.out.print("Give the name of the program you want to add: ");
                            search = scan.next();
                            System.out.print("Give the type of the program you want to add (must be undergraduate,master,phd):");
                            search2 = scan.next();
                        } while (search2.isEmpty() || search.isEmpty() || (!search2.equals("master") && !search2.equals("phd") && !search2.equals("undergraduate")));
                        Program pr = new Program();
                        pr.AddProgram(search, search2);
                        dsMenu2();
                    }
                    case 6:{
                        System.out.println("Add graduation information's.");
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        AddGraduation();
                        dsMenu2();
                    }
                }

//                System.out.println("Display graduates.");
//                graduates.DisplayAllGrads();
                dsMenu2();
            }
            case 2:{
                System.out.println(" UPDATE ");
                System.out.println("1. Graduate ");
                System.out.println("2. Career ");
                System.out.println("3. Department ");
                System.out.println("4. School ");
                System.out.println("5. Program ");
                System.out.println("6. Graduation.");
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                int num4;
                do {
                    try {
                        /* insert a number */
                        System.out.print("Enter a number ( from 1-6 ): ");
                        /* This method reads the number provided using keyboard */
                        num4 = scan.nextInt();
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        errorInteger = false;
                    } catch (InputMismatchException e) {
                        System.out.println("You give a none integer number.");
                        scan.reset();
                        scan.next();
                        num4 = 0;
                    }
                } while ((num4 < 1 || num4 > 6) || errorInteger);
                switch (num4) {
                    case 1:
                        System.out.println("Edit graduate.");
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        do {
                            Graduates grads = new Graduates();
                            System.out.print("Give the id of the graduate you want to edit: ");
                            search = scan.next();
                            String search3;
                            grads.DisplayOneGraduate(Integer.parseInt(search));
                            System.out.println("Which field you want to change? ");
                            search2 = scan.next();
                            System.out.println("Give the new value. ");
                            search3 = scan.next();
                            switch (search2) {
                                case "name":
                                case "Name":
                                case "first name":
                                    grads.UpdateGradField("first_name", search3, Integer.parseInt(search));
                                    break;
                                case "surname":
                                case "Surname":
                                case "last name":
                                    grads.UpdateGradField("last_name", search3, Integer.parseInt(search));

                                    break;
                                case "patronymic":
                                case "Patronymic":
                                    grads.UpdateGradField("patronymic", search3, Integer.parseInt(search));

                                    break;

                                case "birthday":
                                case "date of birth":
                                    grads.UpdateBirthday("date_of_birth", search3, Integer.parseInt(search));

                                    break;
                                case "registration":

                                    do {
                                        System.out.print("Give the register year of the new graduate: ");
                                        search3 = String.valueOf(Integer.parseInt(scan.next()));
                                    }while (Integer.parseInt(search3) >2023);
                                    grads.UpdateRegYear("registration_year",Integer.parseInt(search3), Integer.parseInt(search));

                                    break;
                                case "phone":
                                case "Phone":
                                    boolean valid;
                                    do{
                                        System.out.print("Enter phone number: ");
                                        search3 = String.valueOf(scan.nextLong());
                                        String phoneNumberString = Long.toString(Long.parseLong(search3));
                                        valid = (phoneNumberString.matches("(69|21)\\d{8}"));
                                        if (!valid){
                                            System.out.println("Invalid phone number. Please enter a 10-digit number starting with 69 or 21.");
                                        }

                                    }while(!valid);
                                    grads.UpdatePhone("phone", Long.parseLong(search3), Integer.parseInt(search));

                                    break;
                            }
                            //grads.UpdateGradField(search2,search3, Integer.parseInt(search));
                            //grads.IndexQuery(Integer.parseInt(search));
                            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        } while (search.isEmpty() || !graduates.GraduateExist(Integer.parseInt(search)));
                        dsMenu2();
                        break;

                    case 2:
                        System.out.println("Edit career.");
                        do {
                            //Classes.Career care = new Classes.Career();
                            System.out.print("Give the id of the career you want to edit: ");
                            search = scan.next();
                            String search3;
                            career.DisplayAllCarrers();
                            System.out.println("Which field you want to change? ");
                            search2 = scan.next();
                            System.out.println("Give the new value. ");
                            search3 = scan.next();
                            switch (search2) {
                                case "company":
                                case "Company":
                                    career.UpdateTexts("company", search3, Integer.parseInt(search));
                                    break;
                                case "job":
                                case "title":
                                case "jobtitle":
                                    career.UpdateTexts("job_title", search3, Integer.parseInt(search));
                                    break;
                                case "category":
                                case "employment_category":
                                    career.UpdateTexts("employment_category", search3, Integer.parseInt(search));

                                    break;
                                case "start":
                                case "startdate":
                                    career.UpdateCareerDates(search2, search3, Integer.parseInt(search));

                                    break;
                                case "end":
                                case "enddate":
                                    break;
                            }

                        }while(search.isEmpty() || !career.CareerExist(Integer.parseInt(search)));
                        break;

                    case 3:
                        System.out.println("Edit department.");
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        Department dept = new Department();
                        dept.DisplayAllDepartments();
                        int x;
                        do {
                            System.out.print("Give id of the department you want to edit name: ");
                            x = scan.nextInt();
                            System.out.println("Give the new name of the Department");
                            search = scan.next();
                            dept.UpdateDepartment(search, x);
                        } while (search.isEmpty() || !dept.DepartmentExist(x));
                        dsMenu2();
                        break;
                    case 4:
                        System.out.println("Edit school.");
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        School school2 = new School();
                        school2.DisplayAllSchools();
                        int y;
                        do {
                            System.out.print("Give id of the school you want to edit name: ");
                            y = scan.nextInt();
                            System.out.println("Give the new name of the School");
                            search = scan.next();
                            school2.UpdateSchool(search, y);
                        } while (search.isEmpty() || !school2.SchoolExist(y));
                        dsMenu2();
                        break;
                    case 5:
                        Program prg = new Program();
                        String search3;
                        do {
                            System.out.print("Give the id of the program you want to edit: ");
                            search = scan.next();
                            prg.DisplayAllPrograms();
                            System.out.println("Which field you want to change from programs? ");
                            search2 = scan.next();
                            System.out.println("Give the new value ");
                            search3 = scan.next();
                            switch (search2) {
                                case "name":
                                case "Name":
                                case "program name":
                                    prg.UpdateProgramField("program_name", search3, Integer.parseInt(search));
                                    break;
                                case "type":
                                case "Type":
                                case "program type":
                                    prg.UpdateProgramField("program_type", search3, Integer.parseInt(search));
                                    break;
                            }
                            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

                        } while (search.isEmpty() || !prg.ProgramExist(Integer.parseInt(search) )|| (!search3.equals("master") && !search3.equals("undergraduate") && !search3.equals("phd")) );
                        dsMenu2();
                        break;
                    case 6:
                        System.out.println("Edit graduation.");
                        do{
                        System.out.print("Give the id of the graduation you want to edit: ");
                        search = scan.next();

                        System.out.println("Which field you want to change from graduation? ");
                        search2 = scan.next();
                        System.out.println("Give the new value ");
                        search3 = scan.next();
                        switch (search2) {
                            case "graduateid":
                            case "graduate_id":
                            case "departmentid":
                            case "department_id":
                            case "schoolid":
                            case "school_id":
                            case "programid":
                            case "program_id":
                                graduation.UpdateIDs(search2, Integer.parseInt(search3), Integer.parseInt(search));
                                break;
                            case "graduationdate":
                            case "graduation_date":
                                graduation.UpdateGradDate("graduation_date", search3, Integer.parseInt(search));
                                break;
                            case "degree":
                            case "degreegrade":
                            case "degree_grade":
                                graduation.UpdateDegree("degree_grade", Float.parseFloat(search3), Integer.parseInt(search));
                                break;
                        }
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

                    } while (search.isEmpty() || !graduation.GraduationExist(Integer.parseInt(search))  );
                        dsMenu2();
                        break;
                }
                dsMenu2();
                break;
            }
            case 3:{
                System.out.println(" DELETE ");
                System.out.println("1. Graduate ");
                System.out.println("2. Career ");
                System.out.println("3. Department ");
                System.out.println("4. School ");
                System.out.println("5. Program ");
                System.out.println("6. Graduation.");
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                int num3;
                do {
                    try {
                        /* insert a number */
                        System.out.print("Enter a number ( from 1-6 ): ");
                        /* This method reads the number provided using keyboard */
                        num3 = scan.nextInt();
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        errorInteger = false;
                    } catch (InputMismatchException e) {
                        System.out.println("You give a none integer number.");
                        scan.reset();
                        scan.next();
                        num3 = 0;
                    }
                } while ((num3 < 1 || num3 > 6) || errorInteger);
                switch (num3) {
                    case 1:{
                        System.out.println("Delete graduate.");
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        do {

                            Graduates delgrad = new Graduates();
                            delgrad.DisplayAllGrads();
                            System.out.print("Give the id of the graduate you want to delete: ");
                            search = scan.next();
                            delgrad.deleteGraduate(Integer.parseInt(search));
                            delgrad.DisplayAllGrads();
                            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        } while (search.isEmpty() && !graduates.GraduateExist(Integer.parseInt(search)) );
                        break;
                    }
                    case 2:{
                        System.out.println("Delete career");
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        String searchc;
                        Career carer = new Career();
                        do {
                            carer.DisplayAllCarrers();
                            System.out.print("Give the id of the graduate you want to delete: ");
                            searchc = scan.next();
                            carer.DeleteCareer(Integer.parseInt(searchc));
                            carer.DisplayAllCarrers();
                            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

                        } while (searchc.isEmpty() && !carer.CareerExist(Integer.parseInt(searchc)) );
                        dsMenu2();
                        break;

                    }
                    case 3:{
                        System.out.println("Delete department.");
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        do {

                            Department deldept = new Department();
                            deldept.DisplayAllDepartments();
                            System.out.print("Give the id of the department you want to delete: ");
                            search = scan.next();
                            deldept.DeleteDepartment(Integer.parseInt(search));
                            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        } while (search.isEmpty() && !department.DepartmentExist(Integer.parseInt(search)));
                        break;
                    }
                    case 4:{
                        System.out.println("Delete school.");
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        do {
                            //Classes.School delschool = new Classes.School();
                            school.DisplayAllSchools();
                            System.out.print("Give the id of the department you want to delete: ");
                            search = scan.next();
                            school.DeleteSchool(Integer.parseInt(search));
                            school.DisplayAllSchools();
                            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        } while (search.isEmpty() && !school.SchoolExist(Integer.parseInt(search)));
                        break;
                    }
                    case 5:{
                        System.out.println("Delete program.");
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        do {

                            //Classes.Program delprogram = new Classes.Program();
                            program.DisplayAllPrograms();
                            System.out.print("Give the id of the program you want to delete: ");
                            search = scan.next();
                            program.DeleteProgram(Integer.parseInt(search));
                            program.DisplayAllPrograms();
                            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        } while (search.isEmpty() && !program.ProgramExist(Integer.parseInt(search)));
                        break;
                    }
                    case 6:{
                        System.out.println("Delete graduation");
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        do {
                            //Classes.Graduation  grdtn= new Classes.Graduation();
                            graduation.DisplayAllGraduations();
                            System.out.print("Give the id of the graduation you want to delete: ");
                            search = scan.next();
                            graduation.DeleteGraduation(Integer.parseInt(search));
                            graduation.DisplayAllGraduations();
                            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        } while (search.isEmpty() && !graduation.GraduationExist(Integer.parseInt(search)));

                        break;
                    }
                }

//                System.out.println("Display graduates.");
//                graduates.DisplayAllGrads();
                dsMenu2();
                break;
            }
            case 4: {
                System.out.println(" View All ");
                System.out.println("1. Graduate ");
                System.out.println("2. Career ");
                System.out.println("3. Department ");
                System.out.println("4. School ");
                System.out.println("5. Program ");
                System.out.println("6. Graduation.");
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                int num5;
                do {
                    try {
                        /* insert a number */
                        System.out.print("Enter a number ( from 1-6 ): ");
                        /* This method reads the number provided using keyboard */
                        num5 = scan.nextInt();
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        errorInteger = false;
                    } catch (InputMismatchException e) {
                        System.out.println("You give a none integer number.");
                        scan.reset();
                        scan.next();
                        num5 = 0;
                    }
                } while ((num5 < 1 || num5 > 6) || errorInteger);
                switch (num5) {
                    case 1:{
                        System.out.println("Display all graduates.");
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        graduates.DisplayAllGrads();
                        break;
                    }
                    case 2:{
                        System.out.println("Display all careers.");
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        career.DisplayAllCarrers();
                        break;
                    }
                    case 3:{
                        System.out.println("Display all departments.");
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        department.DisplayAllDepartments();
                        break;
                    }
                    case 4:{
                        System.out.println("Display all schools.");
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        school.DisplayAllSchools();
                        break;
                    }
                    case 5 :{
                        System.out.println("Display all programs.");
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        program.DisplayAllPrograms();
                        break;
                    }
                    case 6:{
                        System.out.println("Display all graduations.");
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        graduation.DisplayAllGraduations();
                        break;
                    }
                }
                dsMenu2();
                break;
            }
            case 5:{
                System.out.println("See basic reports");
                System.out.println("Choose store procedures to call");
                System.out.println("1. A printout (report) with graduate statistics, such as percentage graduates per year, per Classes.Department and per study program");
                System.out.println("2. A printout (report) showing in which employment categories (in percentage) the graduates of each Classes.Department are working.");
                int num7;
                //boolean errorInteger2=true;
                //errorInteger= true;
                do {
                    try {
                        System.out.print("Enter a number ( from 1-3 ): ");
                        /* This method reads the number provided using keyboard */
                        num7 = scan.nextInt();
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        errorInteger=false;
                    }catch (InputMismatchException e ){
                        System.out.println("You give a none integer number.");
                        scan.reset();
                        scan.next();
                        num7= 0;
                    }
                }while((num7<1 || num7>2) || errorInteger);

                //dsMenu2();
                String namePr;
                switch (num7) {
                    case 1:{
                        namePr = "graduate_stats()";
                        callStoredProcedure(namePr);
                        break;
                    }
                    case 2:{
                        namePr = "employment_categories()";
                        callStoredProcedure(namePr);
                        break;
                    }
                }
                dsMenu2();
                break;
            }
            case 6:
                System.exit(0);
                break;
        }
    }

    private void callStoredProcedure2(String namePr, int search) throws SQLException, IOException {
        Database database = new Database();
        Connection conn = null;
        ResultSet rs;
        conn = Database.getConnection();
        CallableStatement statement;
        String sql = "CALL public.time_gaps(?);";
        try {
            //statement = conn.prepareCall(sql);
            statement = conn.prepareCall(sql);
            statement.setInt(1, search);
            statement.execute();
            //rs= statement.getResultSet();
            String sqlWarning = String.valueOf(statement.getWarnings().getMessage());
            String sqlWarn = String.valueOf(statement.getWarnings().getNextWarning().getMessage());
            System.out.println(sqlWarning);
            System.out.println(sqlWarn);
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    private void AddCareer() throws SQLException, IOException {
        String company, job_title, employment_category, start_date, end_date;
        int graduate_id;
        Graduates grds = new Graduates();
        do {
            System.out.print("Give the graduate id: ");
            graduate_id = Integer.parseInt(scan.next());
        }while (graduate_id == 0 || !grds.GraduateExist(graduate_id));
        do {
            System.out.print("Give the name of the company: ");
            company = scan.next();
        }while(company.isEmpty());

        do {
            System.out.print("Give the job title of thew new position: ");
            job_title = scan.next();
        }while (job_title.isEmpty());

        do {
            System.out.print("Give the employment category:");
            employment_category = scan.next();
        }while(employment_category.isEmpty());

        do {
            System.out.print("Give the start date:(2002-12-30) ");
            start_date = scan.next();
        }while(start_date.isEmpty());
        do {
            System.out.print("Give the end date: ");
            end_date = scan.next();
        }while (end_date.isEmpty());
        Career career = new Career();
        career.AddCareer(graduate_id, company, job_title, employment_category, start_date, end_date);
    }

    public void callStoredProcedure(String storedProcedureName) throws SQLException, IOException, NullPointerException {
        Database database = new Database();
        Connection conn = null;
        ResultSet rs;
        conn = Database.getConnection();
        CallableStatement statement;
        if (storedProcedureName.equals("graduate_stats()")) {
            String sql = "CALL public.graduate_stats();";
            try {
                //statement = conn.prepareCall(sql);
                statement = conn.prepareCall(sql);
                statement.execute();
                //rs= statement.getResultSet();
                String sqlWarning = String.valueOf(statement.getWarnings().getMessage());
                String sqlWarn = String.valueOf(statement.getWarnings().getNextWarning().getMessage());
                System.out.println(sqlWarning);
                System.out.println(sqlWarn);
                //System.out.print(statement.execute());
//            while (rs.next()) {
//                int year = rs.getInt("registration_year");
//                String department = rs.getString("department_name");
//                String program = rs.getString("program_name");
//                float percentage = rs.getFloat("percentage");
//
//                System.out.println("Year: " + year + ", Classes.Department: " + department + ", Classes.Program: " + program + ", Percentage: " + percentage);
//            }
            } catch (SQLException e) {
                e.printStackTrace();

            }
        } else if (storedProcedureName.equals("employment_categories()")){
            String sql = "CALL public.employment_categories();";
            try {
                //statement = conn.prepareCall(sql);
                statement = conn.prepareCall(sql);
                statement.execute();
                //rs= statement.getResultSet();
                String sqlWarning = String.valueOf(statement.getWarnings().getMessage());
                String sqlWarn = String.valueOf(statement.getWarnings().getNextWarning().getMessage());
                System.out.println(sqlWarning);
                System.out.println(sqlWarn);
            } catch (SQLException e) {
                e.printStackTrace();

            }

        }
    }

    private void AddGraduate() throws SQLException, IOException {
        String name, surname,patronymic, email, datebirth, password;
        int register;
        long phone;

        do {
            System.out.print("Give the name of the new graduate: ");
            name = scan.next();
        }while(name.isEmpty());

        do {
            System.out.print("Give the surname of the new graduate: ");
            surname = scan.next();
        }while (surname.isEmpty());

        do {
            System.out.print("Give the patronymic of the new graduate: ");
            patronymic = scan.next();
        }while (patronymic.isEmpty());
        boolean valid3;
        do {
            System.out.print("Give the email of the new graduate:");
            email = scan.next();
            valid3= (email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$"));
            if (!valid3){
                System.out.println("invalid email, must be: name@domain.com");
            }
        }while(email.isEmpty() || !valid3);
        boolean valid2;
        do {
            System.out.print("Give birth date of the  new graduate:(yyyy-mm-dd) ");
            datebirth = scan.next();
            valid2= (datebirth.matches("^(200[0-4]|[1-9][0-9]{0,3})-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])"));

        }while(datebirth.isEmpty() || ! valid2);
        do {
            System.out.print("Give the register year of the new graduate: ");
            register = Integer.parseInt(scan.next());
        }while (register >2023 || register == 0);
        boolean valid;
        do {
            System.out.print("Give the phone number of the new graduate: ");
            phone = Long.parseLong(scan.next());
            String phonenum= Long.toString(phone);
            valid= (phonenum.matches("(69|21)\\d{8}"));
            if (!valid){
                System.out.println("invalid phone, must start 21 or 69 and must be 10 digits");
            }

        }while (phone==0 ||!valid);
        do {
            System.out.print("Give the password of the new graduate: ");
            password = scan.next();
        }while (password.isEmpty());


        Users us = new Users();
        us.createUser(email, password);
        Graduates gr = new Graduates();
        gr.createGraduate(name,surname,patronymic,email,datebirth,register,phone);
        System.out.println("The new graduate added to the system.");
        gr.DisplayAllGrads();
    }
    private void AddGraduation() throws SQLException, IOException {
        int graduate_id, department_id, school_id, program_id;
        String graduation_date;
        float degree_grade;
        Graduates grs= new Graduates();

        do {
            System.out.print("Give the graduate_id: ");
            graduate_id = Integer.parseInt(String.valueOf(scan.nextInt()));
        } while (graduate_id == 0 || !grs.GraduateExist(graduate_id));
        Department dpt = new Department();
        //boolean b  =dpt.DepartmentExist(department_id);
        School schl= new School();
        do {
            System.out.print("Give the department: ");
            department_id = Integer.parseInt(scan.next());
        } while ( department_id == 0 || !dpt.DepartmentExist(department_id));

        do {
            System.out.print("Give the school: ");
            school_id = Integer.parseInt(scan.next());
//            boolean x= schl.SchoolExist();

        } while (school_id == 0 || !schl.SchoolExist(school_id));

        do {
            System.out.print("Give the program_id:");
            program_id = Integer.parseInt(scan.next());
        } while (program_id == 0);

        do {
            System.out.print("Give graduation date:(2002-12-30) ");
            graduation_date = scan.next();
        } while (graduation_date.isEmpty());
        do {
            System.out.print("Give the degree grade: ");
            degree_grade = Float.parseFloat((scan.next()));
        } while (degree_grade < 5 || degree_grade > 10);

        Graduation graduation = new Graduation();
        graduation.Addgraduation(graduate_id, department_id, school_id, program_id, graduation_date, degree_grade);
        System.out.println("The graduation info added to the system.");

    }

}
