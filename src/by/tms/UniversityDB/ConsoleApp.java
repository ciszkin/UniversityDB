package by.tms.UniversityDB;

import by.tms.UniversityDB.actions.AdminAction;
import by.tms.UniversityDB.actions.LecturerAction;
import by.tms.UniversityDB.actions.StudentAction;
import by.tms.UniversityDB.actions.VisitorAction;
import by.tms.UniversityDB.util.ConsoleReader;
import by.tms.UniversityDB.util.ConsoleWriter;
import by.tms.UniversityDB.util.Reader;
import by.tms.UniversityDB.util.Writer;

public class ConsoleApp implements App {
    private Reader reader = new ConsoleReader();
    private Writer writer = new ConsoleWriter();
    private boolean isRunning = false;
    private VisitorAction visitorAction = new VisitorAction();
    private AdminAction adminAction = new AdminAction();
    private LecturerAction lecturerAction = new LecturerAction();
    private StudentAction studentAction = new StudentAction();

    private String mode = "visitor";



    public static void main(String[] args) {
        App app = new ConsoleApp();
        app.run();
    }

    @Override
    public void run() {
        isRunning = true;

        visitorAction.greeting();

        while(isRunning) {
            update();
        }
    }

    @Override
    public void stop() {
        isRunning = false;
    }

    private void update() {
        writer.write("Enter command:");
        String answer = reader.read().toLowerCase();

        switch (mode) {
            case "visitor":
                switch (answer) {
                    case "e"://exit
                        stop();
                        break;
                    case "li":
                        visitorAction.login();

                        if(adminAction.isSetted()) {
                            mode = "admin";
                        } else if(lecturerAction.isSetted()) {
                            mode = "lecturer";
                        } else if(studentAction.isSetted()) {
                            mode = "student";
                        }
                        break;
                    default:
                        writer.write("No such command!");
                        break;
            }
                break;
            case "admin":
                switch (answer) {
                    case "e"://exit
                        adminAction.logOut();
                        stop();
                        break;
                    case "lo":
                        adminAction.logOut();
                        mode = "visitor";
                        break;
                    case "h":
                        adminAction.help();
                        break;
                    case "fnew":
                        adminAction.newFaculty();
                        break;
                    case "flist":
                        adminAction.displayFaculties();
                        break;
                    case "fsel":
                        adminAction.selectFaculty();
                        break;
                    case "fllist":
                        adminAction.displayLecturers();
                        break;
                    case "fstlist":
                        adminAction.displayStudents();
                        break;
                    case "psel":
                        adminAction.selectPerson();
                        break;
                    case "stadd":
                        adminAction.newStudent();
                        break;
                    case "ladd":
                        adminAction.newLecturer();
                        break;
                    case "pdel":
                        adminAction.deletePerson();
                        break;
                    case "pmov":
                        adminAction.movePerson();
                        break;
                    default:
                        writer.write("No such command! Enter <h> to help.");
                        break;
                }
                break;

            case "lecturer":
                switch (answer) {
                    case "e"://exit
                        lecturerAction.logOut();
                        stop();
                        break;
                    case "lo":
                        lecturerAction.logOut();
                        mode = "visitor";
                        break;
                    case "h":
                        lecturerAction.help();
                        break;
                    case "stlist":
                        lecturerAction.displayStudents();
                        break;
                    case "rlist":
                        lecturerAction.displayRanks();
                        break;
                    case "stsel":
                        lecturerAction.selectStudent();
                        break;
                    case "mark":
                        lecturerAction.markStudent();
                        break;
                    default:
                        writer.write("No such command! Enter <h> to help.");
                        break;
                }
                break;

            case "student":
                switch (answer) {
                    case "e"://exit
                        studentAction.logOut();
                        stop();
                        break;
                    case "lo":
                        studentAction.logOut();
                        mode = "visitor";
                        break;
                    case "h":
                        studentAction.help();
                        break;
                    case "llist":
                        studentAction.displayLecturers();
                        break;
                    case "slist":
                        studentAction.displaySubjects();
                        break;
                    case "rlist":
                        studentAction.displayRanks();
                    default:
                        writer.write("No such command! Enter <h> to help.");
                        break;
                }
                break;
        }


    }
}