package org.assignment1.factory;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import org.assignment1.domain.Classroom;
import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AttendanceRecordFactoryTest {

    private static AttendanceRecordFactory attendanceRecordFactory1 = AttendanceRecordFactory.createAttendanceRecord("011",,
            "PRESENT","","","");

    @Test
    void createAttendanceRecord() {

    }
}