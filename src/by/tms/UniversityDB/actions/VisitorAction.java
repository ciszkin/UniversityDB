package by.tms.UniversityDB.actions;

import by.tms.UniversityDB.service.AuthService;
import by.tms.UniversityDB.service.FacultyService;
import by.tms.UniversityDB.service.LecturerService;
import by.tms.UniversityDB.service.StudentService;
import by.tms.UniversityDB.util.ConsoleReader;
import by.tms.UniversityDB.util.ConsoleWriter;
import by.tms.UniversityDB.util.Reader;
import by.tms.UniversityDB.util.Writer;

public class VisitorAction {
    Reader reader = new ConsoleReader();
    Writer writer = new ConsoleWriter();
    AuthService authService = new FacultyService();

    public VisitorAction() {
    }

    public void greeting() {
        writer.write("Welcome in our UniversityDB app!");
        writer.write("Please, enter <li> command to login or <e> to exit. Only registered users can interact with our app.");
        authService.loadBase();
    }

    public void login() {

        boolean logged = false;
        String role = selectRole();

        int personID;

        switch (role) {
            case "a":
                writer.write("Enter your password:");
                if(reader.read().equals("admin1234")) {
                    (new AdminAction()).setService(authService.logInAsAdmin());
                    writer.write("Hello Administrator! Press <h> for help.");
                } else {
                    writer.write("Wrong password! Try again!");
                }

                break;
            case "l":
                personID = getID(authService.getLecturers());
                writer.write("Enter your password:");
                while(!logged) {
                    LecturerService ls = authService.logInAsLecturer(personID, reader.read());
                    if( ls == null) {
                        writer.write("Wrong password! Try again!");
                    } else {
                        logged = true;
                        (new LecturerAction()).setService(ls);
                        authService.setLecturer(personID);
                    }
                }
                break;
            case "s":
                personID = getID(authService.getStudents());
                writer.write("Enter your password:");
                while(!logged) {
                    StudentService ss = authService.logInAsStudent(personID, reader.read());
                    if( ss == null) {
                        writer.write("Wrong password! Try again!");
                    } else {
                        logged = true;
                        (new StudentAction()).setService(ss);
                        authService.setStudent(personID);
                    }
                }
                break;
        }
    }

    public void logout() {
        authService.logOut();
    }

    //------------------------------helper methods---------------------
    private String checkFaculty() {
        writer.write("Please, enter faculty name:");
        String facultyName = "";
        boolean found = false;

        while (!found) {
            facultyName = reader.read();
            for(String f :authService.getFaculties()) {
                if(f.equals(facultyName)) {
                    found = true;
                    break;

                }
            }
            if(!found) writer.write("There is no such faculty! Try another one!");
        }
        return facultyName;
    }

    private int getID(int[] persons) {
        boolean match = false;
        int answer = -1;
        while (!match) {
            writer.write("Enter your personal ID, please:");
            boolean isInt = false;
            while (!isInt) {
                try {
                    answer = Integer.parseInt(reader.read());
                    isInt = true;
                } catch (NumberFormatException e) {
                    writer.write("Wrong input! Try again!");
                }
            }

            for(int id:persons) {
                if(id == answer) {
                    match = true;
                    break;
                }
            }
        }
        return answer;
    }

    private String selectRole() {
        boolean roleSelected = false;
        String role = "";
        writer.write("Please, select your role in our University:");
        writer.write(" Admin - press <a/A>");
        writer.write(" Lecturer - press <l/L>");
        writer.write(" Student - press <s/S>");
        while (!roleSelected) {
            role = reader.read();
            if (role.equalsIgnoreCase("s") ||
                    role.equalsIgnoreCase("l")) {
                roleSelected = true;
                String facultyName = checkFaculty();
                authService.setFaculty(facultyName);

            } else if (role.equalsIgnoreCase("a")) {
                roleSelected = true;
            } else {
                writer.write("Wrong input! Try again.");
            }
        }
        return  role;
    }
}
