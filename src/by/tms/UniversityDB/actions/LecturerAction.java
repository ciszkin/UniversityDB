package by.tms.UniversityDB.actions;

import by.tms.UniversityDB.service.LecturerService;
import by.tms.UniversityDB.util.ConsoleReader;
import by.tms.UniversityDB.util.ConsoleWriter;
import by.tms.UniversityDB.util.Reader;
import by.tms.UniversityDB.util.Writer;

import java.util.Arrays;

public class LecturerAction {

    private static LecturerService lecturerService = null;
    private Reader reader = new ConsoleReader();
    private Writer writer = new ConsoleWriter();

    public void setService(LecturerService lecturerService) {
        LecturerAction.lecturerService = lecturerService;
    }

    public boolean isSetted() {
        return (lecturerService != null);
    }

    public void help() {
        writer.write("Accessible commands:");
        writer.write("<studs> - displays the list of your students");
        writer.write("<ranks> -  displays the ranks of your students");
        writer.write("<sel> - selects current student");
        writer.write("<mark> - sets the mark in current student's journal");
        writer.write("-----------------------------------");
        writer.write("");
    }

    public void displayStudents() {
        writer.write("The list of students:");
        for(int id: lecturerService.getMyStudents()) {
            writer.write(lecturerService.getStudentInfo(id));
        }
        writer.write("-----------------------------------");
        writer.write("");
    }

    public void displayRanks() {
        writer.write("The table of ranks:");
        for(int id: lecturerService.getMyStudents()) {
            writer.write(lecturerService.getStudentInfo(id));
            writer.write(Arrays.toString(lecturerService.getRank(id)));
        }
        writer.write("-----------------------------------");
        writer.write("");
    }

    public void selectStudent() {
        writer.write("Enter students ID:");
        int answer;
        try {
            answer = Integer.parseInt(reader.read());
        } catch (NumberFormatException e) {
            writer.write("Wrong input! Try again!");
            return;
        }
        boolean selected = false;
        for(int id: lecturerService.getMyStudents()) {
            if(answer == id) {
                lecturerService.setStudent(id);
                selected = true;
            }
        }

        if(selected) {
            writer.write("Student " + lecturerService.getStudentInfo(answer) + " selected!");
        } else {
            writer.write("There is no such student, or this student is not yours!");
        }
    }

    public void markStudent() {
        writer.write("Enter mark in the range [1...10]");
        int answer;
        try {
            answer = Integer.parseInt(reader.read());
        } catch (NumberFormatException e) {
            writer.write("Wrong input! Try again!");
            return;
        }
        if(answer < 1 || answer > 10) {
            writer.write("Wrong input! Try again!");
            return;
        }

        lecturerService.setMark(answer);

        writer.write("The <" + answer + "> mark is set!");
    }

    public void logOut() {
        lecturerService.logOut();
        lecturerService = null;
    }
}
