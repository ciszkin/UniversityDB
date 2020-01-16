package by.tms.UniversityDB.service;

import by.tms.UniversityDB.entity.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FacultyService implements AdminService, LecturerService, StudentService {

    private static Map<String, Faculty> faculties = new HashMap<>();

    private Faculty currentFaculty = null;
    private Student currentStudent = null;
    private Lecturer currentLecturer = null;
    private Person currentPerson = null;

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
        return ((Lecturer) getPerson(currentFacultyLecturers, lecturerID)).toString();
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
        return ((Student) getPerson(currentFacultyStudents, studentID)).toString();
    }

    @Override
    public void setStudent(int studentID) {
        Person[] currentFacultyStudents = currentFaculty.getStudents();
        currentStudent = (Student) getPerson(currentFacultyStudents, studentID);
    }

    @Override
    public int[] getRank() {
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
        return new Student(); //empty student to avoid warning of exception from .toString in getStudentInfo()
    }

}
