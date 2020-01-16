package by.tms.UniversityDB.service;

import by.tms.UniversityDB.entity.Faculty;
import by.tms.UniversityDB.entity.Lecturer;
import by.tms.UniversityDB.entity.Subject;

public interface LecturerService {

    int[] getMyStudents();
    String getStudentInfo(int studentID);
    void setStudent(int studentID);
    int[] getRank();
    void setMark(int mark);

}
