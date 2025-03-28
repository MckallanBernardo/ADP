package org.assignment1.domain;

import java.time.LocalDate;

/* Student.java
Student model class
Author: Nur Amod (230543502)
Date: 18 March 2025
*/

    public class Student {
    private String studentID;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;

     public Student(String studentID, String firstName, String lastName, LocalDate dateOfBirth ) {
        this.studentID = studentID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    // Getters
    public String getStudentID() {
        return studentID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentID='" + studentID + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }

    // Builder Class
    public static class Builder {
        private String studentID;
        private String firstName;
        private String lastName;
        private LocalDate dateOfBirth;

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

        public Builder setDateOfBirth(LocalDate dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
        }

        public Student build() {
            return new Student(studentID, firstName, lastName, dateOfBirth);
        }
    }
}
