package by.tms.UniversityDB.service;

import by.tms.UniversityDB.entity.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class FacultyService implements AdminService, LecturerService, StudentService, AuthService {

    private static Map<String, Faculty> faculties = new HashMap<>();

    private Faculty currentFaculty = null;
    private Student currentStudent = null;
    private Lecturer currentLecturer = null;
    private Person currentPerson = null;
    

    public FacultyService() {
    }

    //-------------------AuthService implementation----------------------------

    @Override
    public StudentService logInAsStudent(int personID, String pass) {
        currentPerson = getPersonID(currentFaculty.getStudents(), personID);
        if(currentPerson != null && currentPerson.getPass().equals(pass)) {
            return this;
        } else {
            return null;
        }
    }//ok

    @Override
    public LecturerService logInAsLecturer(int personID, String pass) {
        currentPerson = getPersonID(currentFaculty.getLecturers(), personID);
        if (currentPerson != null && currentPerson.getPass().equals(pass)) {
            return this;
        } else {
            return null;
        }
    }//ok

    @Override
    public FacultyService logInAsAdmin() {
        return this;
    }//ok

    @Override
    public void logOut() {
        currentPerson = null;
        try {
            ObjectOutputStream baseFile = new ObjectOutputStream(new FileOutputStream("base.txt"));
            baseFile.writeObject(faculties);
            baseFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }//ok

    @Override
    public void loadBase() {

        try {
            ObjectInputStream baseFile = new ObjectInputStream((new FileInputStream("base.txt")));

            faculties = (Map<String, Faculty>) baseFile.readObject();
            baseFile.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }//TODO something wrong with downcast 

    //-------------------AdminService implementation---------------------------

    @Override
    public void addFaculty(String facultyName) {
        if(faculties.containsKey(facultyName)) return;
        faculties.put(facultyName, new Faculty(facultyName));
    }//ok

    @Override
    public String[] getFaculties() {
        return faculties.keySet().toArray(new String[0]);
    }//ok

    @Override
    public void setFaculty(String facultyName) {
        currentFaculty = faculties.get(facultyName);
    }//ok

    @Override
    public int[] getStudents() {
        return getPersonsIDs(currentFaculty.getStudents());
    }//ok

    @Override
    public int[] getLecturers() {
        return getPersonsIDs(currentFaculty.getLecturers());
    }//ok

    @Override
    public void setPerson(int personID) {
        Person p = getPersonID(currentFaculty.getStudents(), personID);
        if(p == null) p = getPersonID(currentFaculty.getLecturers(), personID);

        currentPerson = p;
    }//ok

    @Override
    public String getPersonInfo(int personID) {
        Person p = getPersonID(currentFaculty.getStudents(), personID);
        if(p == null) p = getPersonID(currentFaculty.getLecturers(), personID);
        if (p == null) return null;
        return p.toString();
    }//TODO refactor to avoid returning 'null'

    @Override
    public void addPerson() {
        currentFaculty.addPerson(currentPerson);
    }//ok

    @Override
    public void removePerson() {
        currentFaculty.removePerson(currentPerson);
    }//ok

    @Override
    public void movePersonTo(String facultyName) {
        currentFaculty.removePerson(currentPerson);
        faculties.get(facultyName).addPerson(currentPerson);
    }//ok

    @Override
    public int registerStudent(String name, String surname, Date birthDate, String pass, Subject[] subjects) {
        currentFaculty.addPerson(new Student(name, surname, birthDate, pass, subjects));
        return 0;
    }//ok

    @Override
    public int registerLecturer(String name, String surname, Date birthDate, String pass, Subject subject) {
        currentFaculty.addPerson(new Lecturer(name, surname, birthDate, pass, subject));
        return 0;
    }//ok

    @Override
    public String getCurrentFaculty() {
        if(currentFaculty == null) return null;
        return currentFaculty.getName();
    }//ok

    @Override
    public int getCurrentPerson() {
        return currentPerson.getId();
    }//ok

    //-------------------StudentService implementation-----------------------

    @Override
    public int[] getMyLecturers() {
        ArrayList<Person> myLecturers = new ArrayList<>();
        for(Person p: currentFaculty.getLecturers()) {
            for(Subject sb: currentStudent.getRankJournal().getSubjects()) {
                if(sb.equals(((Lecturer) p).getSubject())) {
                    myLecturers.add(p);
                }
            }

        }

        return getPersonsIDs(myLecturers.toArray(new Person[0]));
    }//ok

    @Override
    public void setLecturer(int lecturerID) {
        currentLecturer = (Lecturer) getPersonID(currentFaculty.getLecturers(), lecturerID);
    }//ok

    @Override
    public String getLecturerInfo(int lecturerID) {
        //Person[] currentFacultyLecturers = ;
        Lecturer l = (Lecturer) getPersonID(currentFaculty.getLecturers(), lecturerID);
        if(l == null) return null;
        return l.toString();
    }//TODO refactor to avoid returning 'null'

    @Override
    public int[] getMyRank(Subject subject) {
        return currentStudent.getRankJournal().getRankHistory(subject);
    }//ok

    @Override
    public Subject[] getSubjects() {
        return currentStudent.getRankJournal().getSubjects();
    }//ok

    //--------------LecturerService implementation


    @Override
    public int[] getMyStudents() {
        ArrayList<Person> myStudents = new ArrayList<>();
        for(Person p: currentFaculty.getStudents()) {
            for(Subject sb: ((Student)p).getRankJournal().getSubjects()) {
                if(sb.equals(currentLecturer.getSubject())) {
                    myStudents.add(p);
                }
            }

        }

        return getPersonsIDs(myStudents.toArray(new Person[0]));
    }//ok

    @Override
    public String getStudentInfo(int studentID) {
        Student s = (Student) getPersonID(currentFaculty.getStudents(), studentID);
        if(s == null) return null;
        return s.toString();
    }//TODO refactor to avoid returning 'null'

    @Override
    public void setStudent(int studentID) {
        currentStudent = (Student) getPersonID(currentFaculty.getStudents(), studentID);
    }//ok

    @Override
    public int[] getRank(int studentID) {
        setStudent(studentID);
        return currentStudent.getRankJournal().getRankHistory(currentLecturer.getSubject());
    }

    @Override
    public void setMark(int mark) {
        currentStudent.getRankJournal().setRank(currentLecturer.getSubject(), mark);
    }//ok

    //------------helper methods---------------
    private int[] getPersonsIDs(Person[] persons) {
        int[] answer = new int[persons.length];
        for(int i = 0; i < persons.length; i++) {
            answer[i] = persons[i].getId();
        }

        return answer;
    }

    private Person getPersonID(Person[] persons, int personID) {
        for(Person p: persons) {
            if (p.getId() == personID) {
                return p;
            }
        }
        return null;
    }//TODO refactor to avoid returning 'null'

}
