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

//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
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


    //    ArrayList <Student> student1 = new ArrayList<Student>();
//    ArrayList <Teacher> teacher1 = new ArrayList<Teacher>();


//    Classroom classroom1 = ClassroomFactory.createClassroom("987","English101",teacher1,student1);

//    private static AttendanceRecordFactory attendanceRecordFactory1 = AttendanceRecordFactory.createAttendanceRecord("011",LocalDate.of(2021,3,10),
//            "PRESENT",StudentFactory.createStudent("","","",LocalDate.of(2007,9,13)),
//            TeacherFactory.createTeacher("2939","John","Stevens","English"), ;
//
//    @Test
//    public void testCreateAttendanceRecord() {
//        assertNotNull(attendanceRecordFactory1);
//        System.out.println(attendanceRecordFactory1.toString());
//    }
//    @Test
//    public void testCreateAttendanceRecordWithAllAttributes() {
//        assertNotNull(attendanceRecordFactory1);
//        System.out.println(attendanceRecordFactory1.toString());
//    }
//    @Test
//    @Disabled
//    public void testCreateAttendanceRecordThatFails(){
//        assertNotNull(attendanceRecordFactory1);
//        System.out.println(attendanceRecordFactory1.toString());
//    }
}