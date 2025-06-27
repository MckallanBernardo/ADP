package za.ac.cput.factory;

import za.ac.cput.domain.Teacher;
import za.ac.cput.util.Helper;

public class TeacherFactory {
    public static Teacher createTeacher(String teacherID, String firstName, String lastName, String subject) {
        if(Helper.isNullOrEmpty(teacherID) ||
         Helper.isNullOrEmpty(firstName) ||
         Helper.isNullOrEmpty(lastName) ||
         Helper.isNullOrEmpty(subject))
            return null;

        return new Teacher.Builder().setTeacherID(teacherID)
                .setFirstName(firstName)
                .setLastName(lastName)
                .setSubject(subject)
                .build();
    }
}
