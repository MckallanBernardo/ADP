package org.assignment1.factory;

import org.assignment1.domain.*;
import org.junit.jupiter.api.*;
import org.assignment1.domain.Classroom;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.assignment1.domain.Teacher;


import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;

/* AttendanceRecordFactoryTest.java
Attendance Record Factory Test model class
Author: Ross Barth (220612986)
Date: 28 March 2025
*/

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AttendanceRecordFactoryTest {


    @Test
    public void testCreateAttendanceRecord() {
        ArrayList<AttendanceRecord> attendanceRecords = new ArrayList<>();
        ArrayList<Student> students = new ArrayList<>();
        ArrayList<Teacher> teachers = new ArrayList<>();
        ArrayList<Classroom> classrooms = new ArrayList<>();

        attendanceRecords.add(new AttendanceRecord.AttendanceRecordBuilder()
                .setRecordID("AR003")
                .setDate(LocalDate.of(2023, 2, 1))
                .setStatus("PRESENT")
                .setStudent(students)
                .setTeacher(teachers)
                .setClassroom(classrooms)
                .build());

        assertNotNull(attendanceRecords);
        assertEquals(1, attendanceRecords.size());
        AttendanceRecord attendanceRecord = attendanceRecords.get(0);

    }


}
