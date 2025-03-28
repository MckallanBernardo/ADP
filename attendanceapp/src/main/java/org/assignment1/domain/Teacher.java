package org.assignment1.domain;

import java.lang.String;
/* Teacher.java
Teacher Interface
Author: Aidan Coetzee (230563724)
Date: 27 March 2025
 */
public class Teacher {

    private String teacherID;
    private String firstName;
    private String lastName;
    private String subject;

    public Teacher() {
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherID='" + teacherID + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }

    public Teacher(String teacherID, String firstName, String lastName, String subject) {
        this.teacherID = teacherID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.subject = subject;
    }

    public String getTeacherID() {
        return teacherID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSubject() {
        return subject;
    }

    public static class Builder{
        private String teacherID;
        private String firstName;
        private String lastName;
        private String subject;

        public Builder setTeacherID(String teacherID) {
            this.teacherID = teacherID;
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
        public Builder setSubject(String subject) {
            this.subject = subject;
            return this;

        }
        public Teacher build(){
            return new Teacher(teacherID, firstName, lastName, subject);

        }


    }
}
