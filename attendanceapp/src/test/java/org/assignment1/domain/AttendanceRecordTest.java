package org.assignment1.domain;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/* AttendanceRecordTest.java
Attendance Record Test model class
Author: Ross Barth (220612986)
Date: 27 March 2025
*/

public class AttendanceRecordTest {


    class AttendanceRecordTest {
        private AttendanceRecord attendanceRecord;
        private List<Student> students;
        private List<Teacher> teachers;
        private List<Classroom> classrooms;

        @BeforeEach
        void setUp() {
            // Initialize lists
            attendanceRecord = new AttendanceRecord.AttendanceRecordBuilder()
                    .setRecordID("C0800")
                    .setDate(LocalDate.of(2011, 3, 26))
                    .setStatus("PRESENT")
                    .setStudent(new ArrayList<>(students))
                    .setTeacher(new ArrayList<>(teachers))
                    .setClassroom(new ArrayList<>(classrooms))
                    .build();
        }

        @BeforeEach
        void getRecordID() {
            String recordID = attendanceRecord.getRecordID();
            assertEquals("C0800", recordID, "This recordID should be set to C0800");
        }

        @BeforeEach
        void getDate() {
            LocalDate date = attendanceRecord.getDate();
            assertEquals(LocalDate.of(2011, 3, 26), date, "This date should be set to 2011-03-26");
        }

        @BeforeEach
        void getStatus() {
            String status = attendanceRecord.getStatus();
            assertEquals("PRESENT", status, "This status should be set to PRESENT");
        }

        @BeforeEach
        void getStudents() {
            students.add(new Student("AB100","AIDAN","BERNARDO",LocalDate.of(2013,6,12)));
            assertEquals("AB100",students.getFirst().getStudentID(), "This student should be set to AB100");
        }

        @BeforeEach
        void getTeachers() {
            teachers.add(new Teacher());
        }
//            students = new ArrayList<>();
//            students.add(new Student("JD", "Jaden", "Doe", LocalDate.of(2009, 5, 28)));
//            students.add(new Student("JS230", "Jaden", "Smith", LocalDate.of(2009, 8, 28)));
//
//            teachers = new ArrayList<>();
//            teachers.add(new Teacher("Mr. Anderson", "T001"));
//
//            classrooms = new ArrayList<>();
//            classrooms.add(new Classroom("Room 101"));
//
//            // Create AttendanceRecord
//            attendanceRecord = new AttendanceRecord("001", LocalDate.of(2025, 3, 25), "PRESENT", students, teachers, classrooms);
//        }

        @Test
        void testAttendanceRecordCreation() {

        }
    }
}