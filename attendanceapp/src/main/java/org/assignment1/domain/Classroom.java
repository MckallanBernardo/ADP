package org.assignment1.domain;

import java.util.ArrayList;

public class Classroom {
    private String classroomID;
    private String className;
    private String teacherName;
    private ArrayList<Student> studentList;

    public Classroom() {
    }

    public Classroom(String classroomID, String className, String teacherName, ArrayList<Student> studentList) {
        this.classroomID = classroomID;
        this.className = className;
        this.teacherName = teacherName;
        this.studentList = studentList;
    }

    public String getClassroomID() {
        return classroomID;
    }

    public String getClassName() {
        return className;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public ArrayList<Student> getStudentList() {
        return studentList;
    }

    @Override
    public String toString() {
        return "Classroom{" +
                "classroomID='" + classroomID + '\'' +
                ", className='" + className + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", studentList=" + studentList +
                '}';
    }

public static class ClassroomBuilder {
        private String classroomID;
        private String className;
        private String teacherName;
        private ArrayList<Student> studentList;

        public ClassroomBuilder setClassroomID(String classroomID) {
        this.classroomID = classroomID;
        return this;
        }
        public ClassroomBuilder setClassName(String className) {
            this.className = className;
            return this;
        }
        public ClassroomBuilder setTeacherName(String teacherName) {
            this.teacherName = teacherName;
            return this;
        }
        public ClassroomBuilder setStudentList(ArrayList<Student> studentList) {
            this.studentList = studentList;
            return this;
        }
        public Classroom build() {
            return new Classroom(classroomID, className, teacherName, studentList);
        }
}
}

