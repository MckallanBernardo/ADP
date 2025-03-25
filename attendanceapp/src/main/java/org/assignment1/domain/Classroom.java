package org.assignment1.domain;

import java.util.ArrayList;

/* Classroom.java
Classroom model class
Author: Mckallan Bernardo (219018243)
Date: 18 March 2025
*/

public class Classroom {
    private String classroomID;
    private String className;
    private ArrayList <Teacher> teacher;
    private ArrayList<Student> student;

    public Classroom() {
    }

    public Classroom(String classroomID, String className, ArrayList<Teacher> teacher, ArrayList<Student> student) {
        this.classroomID = classroomID;
        this.className = className;
        this.teacher = teacher;
        this.student = student;
    }

    public String getClassroomID() {
        return classroomID;
    }

    public String getClassName() {
        return className;
    }

    public ArrayList<Teacher> getTeacher() {
        return teacher;
    }

    public ArrayList<Student> getStudent() {
        return student;
    }

    @Override
    public String toString() {
        return "Classroom{" +
                "classroomID='" + classroomID + '\'' +
                ", className='" + className + '\'' +
                ", teacherName='" + teacher + '\'' +
                ", studentList=" + student +
                '}';
    }

public static class ClassroomBuilder {
        private String classroomID;
        private String className;
        private ArrayList<Teacher> teacher;
        private ArrayList<Student> student;

        public ClassroomBuilder setClassroomID(String classroomID) {
        this.classroomID = classroomID;
        return this;
        }
        public ClassroomBuilder setClassName(String className) {
            this.className = className;
            return this;
        }
        public ClassroomBuilder setTeacher(ArrayList<Teacher> teacher) {
            this.teacher = teacher;
            return this;
        }
        public ClassroomBuilder setStudent(ArrayList<Student> student) {
            this.student = student;
            return this;
        }
        public Classroom build() {
            return new Classroom(classroomID, className, teacher, student);
        }
}
}

