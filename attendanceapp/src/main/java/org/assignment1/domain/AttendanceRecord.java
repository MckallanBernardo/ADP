package org.assignment1.domain;


import java.util.*;
import java.time.*;


/* AttendanceRecord.java
Attendance Record model class
Author: Ross Barth (220612986)
Date: 24 March 2025
*/

public class AttendanceRecord {
    private String recordID;
    private LocalDate date;
    private String status;
    private ArrayList<Student> student;
    private ArrayList<Teacher> teacher;
    private ArrayList<Classroom> classroom;

    public AttendanceRecord() {

    }
    private AttendanceRecord(AttendanceRecordBuilder builder) {
        this.recordID = builder.recordID;
        this.date = builder.date;
        this.status = builder.status;
        this.student = builder.student;
        this.teacher = builder.teacher;
        this.classroom = builder.classroom;
    }
    public String getRecordID(){
        return recordID;
    }
    public LocalDate getDate(){
        return date;
    }
    public String getStatus(){
        return status;
    }
    public ArrayList<Student> getStudent() {
        return student;
    }
    public ArrayList<Teacher> getTeacher() {
        return teacher;
    }
    public ArrayList<Classroom> getClassroom() {
        return classroom;
    }

    @Override
    public String toString(){
        return "Attendance Record: " + recordID + "\nDate: " + date + "\nStatus: " + status + "\n"
                + "Student: " + student + "\nTeacher: " + teacher + "\nClassroom: " + classroom;
    }

    public static class AttendanceRecordBuilder{
        private String recordID;
        private LocalDate date;
        private String status;
        private ArrayList<Student> student;
        private ArrayList<Teacher> teacher;
        private ArrayList<Classroom> classroom;

        public AttendanceRecordBuilder(){}

        public AttendanceRecordBuilder(String recordID, LocalDate date, String status, ArrayList<Student> student,
                                       ArrayList<Teacher> teacher, ArrayList<Classroom> classroom){
            this.recordID = recordID;
            this.date = date;
            this.status = status;
            this.student = student;
            this.teacher = teacher;
            this.classroom = classroom;
        }
        public AttendanceRecordBuilder setRecordID(String recordID){
            this.recordID = recordID;
            return this;
        }
        public AttendanceRecordBuilder setDate(LocalDate date){
            this.date = date;
            return this;
        }
        public AttendanceRecordBuilder setStatus(String status) {
            this.status = status;
            return this;
        }
        public AttendanceRecordBuilder setStudent(ArrayList<Student> student) {
            this.student = student;
            return this;
        }

        public AttendanceRecordBuilder setClassroom(ArrayList<Classroom> classroom) {
            this.classroom = classroom;
            return this;
        }

        public AttendanceRecordBuilder setTeacher(ArrayList<Teacher> teacher) {
            this.teacher = teacher;
            return this;
        }

        public AttendanceRecord build(){
            return new AttendanceRecord(this);
        }
    }
}
