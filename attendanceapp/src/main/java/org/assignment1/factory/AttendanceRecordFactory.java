package org.assignment1.factory;

import org.assignment1.domain.Classroom;
import org.assignment1.domain.Student;
import org.assignment1.domain.Teacher;
import org.assignment1.domain.AttendanceRecord;

import java.util.ArrayList;
import java.util.Date;

/* AttendanceRecordFactory.java
Attendance Record Factory model class
Author: Ross Barth (220612986)
Date: 26 March 2025
*/

public class AttendanceRecordFactory {
    public static AttendanceRecord createAttendanceRecord(String recordID, Date date, String status, ArrayList<Student> studentList,
                                                         ArrayList<Teacher> teacherList, ArrayList<Classroom> classroomList){
        return new AttendanceRecord.AttendanceRecordBuilder()
                .setRecordID("")
                .setDate()
                .setStatus("")
                .setStudent("")
                .setTeacher("")
                .setClassroom("")
                .build();
    }
}