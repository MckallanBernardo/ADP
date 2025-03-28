package org.assignment1.factory;

import java.lang.String;
import org.assignment1.domain.Teacher;

/* TeacherFactory.java
TeacherFactory class
Author: Aidan Coetzee (230563724)
Date: 27 March 2025
 */

public class TeacherFactory {
    public static Teacher createTeacher(String teacherID,
                                        String firstName,
                                        String lastName,
                                        String subject) {

        if (Helper.isNullorEmpty(teacherID)
                || Helper.isNullorEmpty(lastName)
                || Helper.isNullorEmpty(firstName)
                || Helper.isNullorEmpty(subject)) {
            return null;
        }


        return new Teacher.Builder()
                .setTeacherID(teacherID)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setSubject(subject)
                .build();


    }
    public class Helper {
        public static boolean isNullorEmpty(String str) {
            return str == null || str.trim().isEmpty();
        }
    }
}
