package by.tms.UniversityDB.service;

import by.tms.UniversityDB.entity.Subject;

public interface StudentService {

    int[] getMyLecturers();
    void setLecturer(int lecturerID);
    String getLecturerInfo(int lecturerID);
    int[] getMyRank(Subject subject);
    Subject[] getSubjects();
    void logOut();

}
