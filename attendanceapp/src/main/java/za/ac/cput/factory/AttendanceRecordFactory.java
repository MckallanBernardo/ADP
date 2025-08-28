package za.ac.cput.factory;


import za.ac.cput.domain.AttendanceRecord;
import za.ac.cput.domain.Classroom;
import za.ac.cput.domain.Student;
import za.ac.cput.domain.Teacher;
import za.ac.cput.util.Helper;

import java.time.LocalDate;

public class AttendanceRecordFactory {
     public static AttendanceRecord createAttendanceRecord(
             String recordID,
             LocalDate date,
             String status,
             Classroom classroom,
             Student student,
             Teacher teacher
     ){
          if(
                  Helper.isNullOrEmpty(recordID) ||   date == null   ||   Helper.isNullOrEmpty(status) ||   classroom == null
          )
               return null;
          return new AttendanceRecord.Builder()
                  .setRecordID(recordID)
                  .setDate(date)
                  .setStatus(status)
                  .setClassroom(classroom)
                  .setStudent(student)
                  .setTeacher(teacher)
                  .build();
     }
}
