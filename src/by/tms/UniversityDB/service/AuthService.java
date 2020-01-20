package by.tms.UniversityDB.service;

import by.tms.UniversityDB.entity.Subject;

import java.util.Date;

public interface AuthService {

    StudentService logInAsStudent(int personID, String pass);
    LecturerService logInAsLecturer(int personID, String pass);
    FacultyService logInAsAdmin();
    void logOut();
    String[] getFaculties();
    void setFaculty(String facultyName);                    //sets current faculty
    int[] getStudents();                                    //current faculty's students
    int[] getLecturers();                                   //current faculty's lecturers
    void setStudent(int studentID);
    void setLecturer(int lecturerID);
    void loadBase();
}
