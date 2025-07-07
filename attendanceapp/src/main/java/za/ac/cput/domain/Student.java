package za.ac.cput.domain;

import jakarta.persistence.*;

@Entity
public class Student {
    @Id
    private String studentID;
    private String firstName;
    private String lastName;

    protected Student() {
    }

    private Student(Builder builder) {
        this.studentID = builder.studentID;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentID='" + studentID + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    public static class Builder {
        private String studentID;
        private String firstName;
        private String lastName;

        public Builder setStudentID(String studentID) {
            this.studentID = studentID;
            return this;
        }

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder copy(Student student) {
            this.studentID = student.studentID;
            this.firstName = student.firstName;
            this.lastName = student.lastName;
            return this;
        }

        public Student build() {
            return new Student(this);
        }
    }
}