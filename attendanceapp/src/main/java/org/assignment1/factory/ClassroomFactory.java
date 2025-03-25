package org.assignment1.factory;

import org.assignment1.domain.Classroom;
import org.assignment1.domain.Student;
import org.assignment1.domain.Teacher;

import java.util.ArrayList;

/* ClassroomFactory.java
ClassroomFactory class
Author: Mckallan Bernardo (219018243)
Date: 18 March 2025
*/

public class ClassroomFactory {
    public static Classroom createClassroom(String classroomID, String className, ArrayList<Teacher> teacher, ArrayList<Student> student) {
        return new Classroom.ClassroomBuilder()
                .setClassroomID(classroomID)
                .setClassName(className)
                .setTeacher(teacher)
                .setStudent(student)
                .build();
    }
}
