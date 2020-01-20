package by.tms.UniversityDB.actions;

import by.tms.UniversityDB.entity.Subject;
import by.tms.UniversityDB.service.FacultyService;
import by.tms.UniversityDB.util.ConsoleReader;
import by.tms.UniversityDB.util.ConsoleWriter;
import by.tms.UniversityDB.util.Reader;
import by.tms.UniversityDB.util.Writer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AdminAction {
    private static FacultyService facultyService = null;
    private Reader reader = new ConsoleReader();
    private Writer writer = new ConsoleWriter();

    public void setService(FacultyService facultyService) {
        AdminAction.facultyService = facultyService;
    }

    public boolean isSetted() {
        return (facultyService != null);
    }

    public void help() {
        writer.write("Accessible commands:");
        writer.write("<h> - help");
        writer.write("<e> - exit");
        writer.write("<lo> - log out");
        writer.write("<fnew> - creates new faculty");
        writer.write("<flist> - displays the list of faculties");
        writer.write("<fsel> - selects current faculty");
        writer.write("<fllist> - displays the list of lecturers of current faculty");
        writer.write("<fstlist> - displays the list of students of current faculty");
        writer.write("<psel> - selects current person (student/lecturer) on current faculty");
        writer.write("<stadd> - add new student to current faculty");
        writer.write("<ladd> - add new lecturer to current faculty");
        //writer.write("<pdel> - delete current person (student/lecturer) from current faculty");
        //writer.write("<pmov> - moves current person (student/lecturer) from current faculty to another faculty");
        writer.write("-----------------------------------");
        writer.write("");
    }

    public void newFaculty() {
        writer.write("Please, enter the name of new faculty:");
        String facultyName = "";
        boolean named = false;
        while (!named) {
            facultyName  = reader.read();
            for(String fn: facultyService.getFaculties()) {
                if(facultyName.equals(fn)) {
                    writer.write("There is the faculty with the same name as you chose!");
                    writer.write("Please, enter another name:");
                    break;
                }
            }
            named = true;
        }
        facultyService.addFaculty(facultyName);
        writer.write("Faculty " + facultyName + " successfully added!");
        writer.write("");
    }

    public void displayFaculties() {
        writer.write("The list of faculties:");
        for(String fn: facultyService.getFaculties()) {
            writer.write(fn);
        }
        writer.write("-----------------------------------");
        writer.write("");
    }

    public void selectFaculty() {
        writer.write("Please, enter the name of faculty:");
        String facultyName;
        boolean selected = false;

        facultyName  = reader.read();
        for(String fn: facultyService.getFaculties()) {
            if(facultyName.equals(fn)) {
                facultyService.setFaculty(facultyName);
                writer.write("Faculty selected! Current faculty: " + facultyName);
                writer.write("");
                selected = true;
                break;
            }
        }

        if(!selected) writer.write("There is no such faculty! Try another name!");

    }

    public void displayLecturers() {
        writer.write("The list of lecturers:");
        for(int id: facultyService.getLecturers()) {
            writer.write(facultyService.getLecturerInfo(id));
        }
        writer.write("-----------------------------------");
        writer.write("");
    }

    public void displayStudents() {
        writer.write("The list of students:");
        for(int id: facultyService.getStudents()) {
            writer.write(facultyService.getStudentInfo(id));
        }
        writer.write("-----------------------------------");
        writer.write("");
    }

    public void selectPerson() {
        writer.write("Enter persons ID:");
        int answer;
        try {
            answer = Integer.parseInt(reader.read());
        } catch (NumberFormatException e) {
            writer.write("Wrong input! Try again!");
            return;
        }
        boolean selected = false;
        for(int id: facultyService.getStudents()) {
            if(answer == id) {
                facultyService.setPerson(id);
                selected = true;
            }
        }
        if(!selected) {
            for(int id: facultyService.getLecturers()) {
                if(answer == id) {
                    facultyService.setPerson(id);
                    selected = true;
                }
            }
        }

        if(selected) {
            writer.write("Person " + facultyService.getPersonInfo(answer) + " selected!");
        } else {
            writer.write("There is no such person on the faculty!");
        }
    }

    public void newStudent() {
        if(facultyService.getCurrentFaculty() == null) {
            writer.write("Select faculty first with <fsel> command!");
            return;
        }

        String name, surname, pass;
        Date birthDate = null;
        writer.write("Enter new student's name:");
        name = reader.read();
        writer.write("Enter " + name + "'s surname: ");
        surname = reader.read();
        writer.write("Enter the date of birth of " + name + " " + surname);
        writer.write("in format dd/MM/yyyy.");
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        while(birthDate == null) {
            try {
                birthDate = formatter.parse(reader.read());
            } catch (ParseException e) {
                writer.write("Wrong date format! Try again!");
            }
        }

        writer.write("Enter new password:");
        pass = reader.read();

        writer.write("Choose desired subject(s)!");
        writer.write("Available subjects are: ");
        ArrayList<Subject> subjects = new ArrayList<>();
        for(Subject s: Subject.values()) {
            writer.write(s.getName());
        }
        writer.write("");
        for(Subject s: Subject.values()) {
            //writer.write(s.getName());
            boolean added = false;
            while (!added) {
                writer.write("Add " + s.getName() + "? <y/n>");
                String answer = reader.read().toLowerCase();
                switch (answer) {
                    case "y":
                        subjects.add(s);
                        added = true;
                        break;
                    case "n":
                        added = true;
                        break;
                    default:
                        writer.write("Wrong input! Try again!");
                        break;
                }
            }
        }

        facultyService.registerStudent(name, surname,birthDate, pass, subjects.toArray(new Subject[0]));
        writer.write("New student of " + facultyService.getCurrentFaculty() + " faculty added!");
        writer.write("");
    }

    public void newLecturer() {
        if(facultyService.getCurrentFaculty() == null) {
            writer.write("Select faculty first with <fsel> command!");
            return;
        }

        String name, surname, pass;
        Date birthDate = null;
        writer.write("Enter new lecturer's name:");
        name = reader.read();
        writer.write("Enter " + name + "'s surname: ");
        surname = reader.read();
        writer.write("Enter the date of birth of " + name + " " + surname);
        writer.write("in format dd/MM/yyyy.");
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        while(birthDate == null) {
            try {
                birthDate = formatter.parse(reader.read());
            } catch (ParseException e) {
                writer.write("Wrong date format! Try again!");
            }
        }

        writer.write("Enter new password:");
        pass = reader.read();

        writer.write("Choose subject!");
        writer.write("Available subjects are: ");
        //ArrayList<Subject> subjects = new ArrayList<>();
        Subject subject = null;
        for(Subject s: Subject.values()) {
            writer.write(s.getName());
        }
        writer.write("");
        int added = 0;
        for(Subject s: Subject.values()) {
            while (added == 0) {
                writer.write("Select " + s.getName() + "? <y/n>");
                String answer = reader.read().toLowerCase();
                switch (answer) {
                    case "y":
                        subject = s;
                        added = 1;
                        break;
                    case "n":
                        added = 2;
                        break;
                    default:
                        writer.write("Wrong input! Try again!");
                        break;
                }
            }
            if(added == 1) break;
        }

        facultyService.registerLecturer(name, surname,birthDate, pass, subject);
        writer.write("New student of " + facultyService.getCurrentFaculty() + " faculty added!");
        writer.write("");
    }

    public void logOut() {
        facultyService.logOut();
        facultyService = null;
    }

}
