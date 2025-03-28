package org.assignment1.domain;

import org.junit.jupiter.api.BeforeEach;
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
            students = new ArrayList<>();
            students.add(new Student("JD", "Jaden","Doe", LocalDate.of(2009, 5, 28)));
            students.add(new Student("JS230", "Jaden", "Smith", LocalDate.of(2009, 8, 28)));

            teachers = new ArrayList<>();
            teachers.add(new Teacher("Mr. Anderson", "T001"));

            classrooms = new ArrayList<>();
            classrooms.add(new Classroom("Room 101"));

            // Create AttendanceRecord
            attendanceRecord = new AttendanceRecord("001", LocalDate.of(2025, 3, 25), "PRESENT", students, teachers, classrooms);
        }

        @Test
        void testAttendanceRecordCreation() {

        }
