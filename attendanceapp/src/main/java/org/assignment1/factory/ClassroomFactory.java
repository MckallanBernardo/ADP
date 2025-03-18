package org.assignment1.factory;

import org.assignment1.domain.Classroom;
import org.assignment1.domain.Student;

import java.util.ArrayList;

public class ClassroomFactory {
    public static Classroom createClassroom(String classroomID, String className, String teacherName, ArrayList<Student> studentList) {
        return new Classroom.ClassroomBuilder()
                .setClassroomID(classroomID)
                .setClassName(className)
                .setTeacherName(teacherName)
                .setStudentList(studentList)
                .build();
    }
}
