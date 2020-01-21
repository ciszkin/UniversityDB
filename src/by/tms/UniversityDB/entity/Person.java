package by.tms.UniversityDB.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public abstract class Person implements Serializable {

    private static final long serialVersionUID = 1432611242907805802L;
    private static int firstID = (int) (System.currentTimeMillis()/10000);
    private final int id = firstID++;
    private String name;
    private String surname;
    private Date birthDate;
    private String pass;

    public Person() {
    }

    public Person(String name, String surname, Date birthDate, String pass) {
        this.name = name;
        this.surname = surname;
        this.birthDate = birthDate;
        this.pass = pass;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id &&
                Objects.equals(name, person.name) &&
                Objects.equals(surname, person.surname) &&
                Objects.equals(birthDate, person.birthDate) &&
                Objects.equals(pass, person.pass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, birthDate, pass);
    }

    @Override
    public String toString() {
        return "Person:" +
                "id: " + id +
                ", name: " + name +
                ", surname: " + surname +
                ", birthDate: " + birthDate;
    }
}
