package by.tms.UniversityDB.service;

import by.tms.UniversityDB.entity.Subject;

import java.util.Date;

public interface AdminService {
    void addFaculty(String facultyName);
    String[] getFaculties();
    void setFaculty(String facultyName);                  //sets current faculty
    int[] getStudents();                                    //current faculty's students
    int[] getLecturers();                                   //current faculty's lecturers
    void setPerson(int personID);               //sets current person
    String getPersonInfo(int personID);         //returns info about person
    void addPerson();                           //add current person to current faculty
    void removePerson();                        //remove current person from current faculty
    void movePersonTo(String facultyName);    //move current person from current faculty to facultyName
    int registerStudent(String name,
                        String surname,
                        Date birthDate,
                        String pass,
                        Subject[] subjects);
    int registerLecturer(String name,
                         String surname,
                         Date birthDate,
                         String pass,
                         Subject subject);
    String getCurrentFaculty();                 //returns the name of current faculty
    int getCurrentPerson();                     //returns the ID of current person
}
