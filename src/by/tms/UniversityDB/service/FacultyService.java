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
        Person[] currentFacultyStudents = currentFaculty.getStudents();
        currentPerson = getPerson(currentFacultyStudents, personID);
        if(currentPerson != null && currentPerson.getPass().equals(pass)) {
            return this;
        } else {
            return null;
        }
    }

    @Override
    public LecturerService logInAsLecturer(int personID, String pass) {
        Person[] currentFacultyLecturers = currentFaculty.getLecturers();
        currentPerson = getPerson(currentFacultyLecturers, personID);
        if (currentPerson != null && currentPerson.getPass().equals(pass)) {
            return this;
        } else {
            return null;
        }
    }

    @Override
    public FacultyService logInAsAdmin() {
        return this;
    }

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

    }

    @Override
    public void loadBase() {
        System.out.println("Try to load base!");
        try {
            ObjectInputStream baseFile = new ObjectInputStream((new FileInputStream("base.txt")));

            faculties = (Map<String, Faculty>) baseFile.readObject();
            baseFile.close();

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //-------------------AdminService implementation---------------------------

    @Override
    public void addFaculty(String facultyName) {
        if(faculties.containsKey(facultyName)) return;
        faculties.put(facultyName, new Faculty(facultyName));
    }

    @Override
    public String[] getFaculties() {
        return faculties.keySet().toArray(new String[0]);
    }

    @Override
    public void setFaculty(String facultyName) {
        currentFaculty = faculties.get(facultyName);
    }

    @Override
    public int[] getStudents() {
        Person[] currentFacultyStudents = currentFaculty.getStudents();
        return getPersons(currentFacultyStudents);
    }

    @Override
    public int[] getLecturers() {
        Person[] currentFacultyLecturers = currentFaculty.getLecturers();
        return getPersons(currentFacultyLecturers);
    }

    @Override
    public void setPerson(int personID) {
        Person[] currentFacultyStudents = currentFaculty.getStudents();
        Person p = getPerson(currentFacultyStudents, personID);
        if(p == null) {
            Person[] currentFacultyLecturers = currentFaculty.getLecturers();
            p = getPerson(currentFacultyLecturers, personID);
        }

        currentPerson = p;
    }

    @Override
    public String getPersonInfo(int personID) {
        Person[] currentFacultyStudents = currentFaculty.getStudents();
        Person p = getPerson(currentFacultyStudents, personID);
        if(p == null) {
            Person[] currentFacultyLecturers = currentFaculty.getLecturers();
            p = getPerson(currentFacultyLecturers, personID);
        }
        if (p == null) return null;
        return p.toString();
    }

    @Override
    public void addPerson() {
        currentFaculty.addPerson(currentPerson);
    }

    @Override
    public void removePerson() {
        currentFaculty.removePerson(currentPerson);
    }

    @Override
    public void movePersonTo(String facultyName) {
        currentFaculty.removePerson(currentPerson);
        faculties.get(facultyName).addPerson(currentPerson);
    }

    @Override
    public int registerStudent(String name, String surname, Date birthDate, String pass, Subject[] subjects) {
        currentFaculty.addPerson(new Student(name, surname, birthDate, pass, subjects));
        return 0;
    }

    @Override
    public int registerLecturer(String name, String surname, Date birthDate, String pass, Subject subject) {
        currentFaculty.addPerson(new Lecturer(name, surname, birthDate, pass, subject));
        return 0;
    }

    @Override
    public String getCurrentFaculty() {
        if(currentFaculty == null) return null;
        return currentFaculty.getName();
    }

    @Override
    public int getCurrentPerson() {
        return currentPerson.getId();
    }

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

        return getPersons(myLecturers.toArray(new Person[0]));
    }

    @Override
    public void setLecturer(int lecturerID) {
        Person[] currentFacultyLecturers = currentFaculty.getLecturers();
        currentLecturer = (Lecturer) getPerson(currentFacultyLecturers, lecturerID);
    }

    @Override
    public String getLecturerInfo(int lecturerID) {
        Person[] currentFacultyLecturers = currentFaculty.getLecturers();
        Lecturer l = (Lecturer) getPerson(currentFacultyLecturers, lecturerID);
        if(l == null) return null;
        return l.toString();
    }

    @Override
    public int[] getMyRank(Subject subject) {
        return currentStudent.getRankJournal().getRankHistory(subject);
    }

    @Override
    public Subject[] getSubjects() {
        return currentStudent.getRankJournal().getSubjects();
    }

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

        return getPersons(myStudents.toArray(new Person[0]));
    }

    @Override
    public String getStudentInfo(int studentID) {
        Person[] currentFacultyStudents = currentFaculty.getStudents();
        Student s = (Student) getPerson(currentFacultyStudents, studentID);
        if(s == null) {
            return null;
        }
        return s.toString();
    }

    @Override
    public void setStudent(int studentID) {
        Person[] currentFacultyStudents = currentFaculty.getStudents();
        currentStudent = (Student) getPerson(currentFacultyStudents, studentID);
    }

    @Override
    public int[] getRank(int studentID) {
        setStudent(studentID);
        return currentStudent.getRankJournal().getRankHistory(currentLecturer.getSubject());
    }

    @Override
    public void setMark(int mark) {
        currentStudent.getRankJournal().setRank(currentLecturer.getSubject(), mark);
    }

    //------------helper methods---------------
    private int[] getPersons(Person[] persons) {
        int[] answer = new int[persons.length];
        for(int i = 0; i < persons.length; i++) {
            answer[i] = persons[i].getId();
        }

        return answer;
    }

    private Person getPerson(Person[] persons, int personID) {
        for(Person p: persons) {
            if (p.getId() == personID) {
                return p;
            }
        }
        return null;
    }

}
