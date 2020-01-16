package by.tms.UniversityDB.service;

public interface AdminService {
    void addFaculty(String facultyName);
    String[] getFaculties();
    void setFaculty(String facultyName);                  //sets current faculty
    int[] getStudents();                                    //current faculty's students
    int[] getLecturers();                                   //current faculty's lecturers
    void addPerson();                           //add current person to current faculty
    void removePerson();                        //remove current person from current faculty
    void movePersonTo(String facultyName);    //move current person from current faculty to facultyName
}
