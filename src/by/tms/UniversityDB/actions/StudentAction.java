package by.tms.UniversityDB.actions;

import by.tms.UniversityDB.entity.Subject;
import by.tms.UniversityDB.service.StudentService;
import by.tms.UniversityDB.util.ConsoleReader;
import by.tms.UniversityDB.util.ConsoleWriter;
import by.tms.UniversityDB.util.Reader;
import by.tms.UniversityDB.util.Writer;

import java.util.Arrays;

public class StudentAction {
    private static StudentService studentService = null;
    private Reader reader = new ConsoleReader();
    private Writer writer = new ConsoleWriter();

    public void setService(StudentService studentService) {
        StudentAction.studentService = studentService;
    }

    public boolean isSetted() {
        return (studentService != null);
    }

    public void help() {
        writer.write("Accessible commands:");
        writer.write("<llist> - displays the list of your lecturers");
        writer.write("<slist> - displays the list of your subjects");
        writer.write("<rlist> - displays the list of your ranks");
        writer.write("-----------------------------------");
        writer.write("");
    }

    public void displayLecturers() {
        writer.write("The list of lecturers:");
        for(int id: studentService.getMyLecturers()) {
            writer.write(studentService.getLecturerInfo(id));
        }
        writer.write("-----------------------------------");
        writer.write("");
    }

    public void displaySubjects() {
        writer.write("The list of subjects:");

        for(Subject s: studentService.getSubjects()) {
            writer.write(s.getName());
        }

        writer.write("-----------------------------------");
        writer.write("");
    }

    public void displayRanks() {
        writer.write("Table of ranks:");
        for(Subject s: studentService.getSubjects()) {
            writer.write(s.getName());
            writer.write(Arrays.toString(studentService.getMyRank(s)));
        }
        writer.write("-----------------------------------");
        writer.write("");
    }

    public void logOut() {
        studentService.logOut();
        studentService = null;
    }
}
