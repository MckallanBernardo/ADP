package org.assignment1.factory;

import org.assignment1.domain.Classroom;
import org.assignment1.domain.Student;
import org.assignment1.domain.Teacher;
import org.assignment1.domain.AttendanceRecord;

import java.util.ArrayList;
import java.time.*;

/* AttendanceRecordFactory.java
Attendance Record Factory model class
Author: Ross Barth (220612986)
Date: 26 March 2025
*/

public class AttendanceRecordFactory {
    public static AttendanceRecord createAttendanceRecord(String recordID, LocalDate date, String status, ArrayList<Student> student,
                                                         ArrayList<Teacher> teacher, ArrayList<Classroom> classroom){
        return new AttendanceRecord.AttendanceRecordBuilder()
                .setRecordID(recordID)
                .setDate(date)
                .setStatus(status)
                .setStudent(student)
                .setTeacher(teacher)
                .setClassroom(classroom)
                .build();
    }
}