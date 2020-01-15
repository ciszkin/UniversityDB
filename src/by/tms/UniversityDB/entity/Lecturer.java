package by.tms.UniversityDB.entity;

import java.util.Date;
import java.util.Objects;

public class Lecturer extends Person {
    private static final long serialVersionUID = 476007368263964558L;
    private String subject;

    public Lecturer(String name,
                    String surname,
                    Date birthDate,
                    String subject) {
        super(name, surname, birthDate);
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Lecturer lecturer = (Lecturer) o;
        return Objects.equals(subject, lecturer.subject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), subject);
    }

    @Override
    public String toString() {
        return super.toString() + " Lecturer{" +
                "subject='" + subject + '\'' +
                '}';
    }
}
