package za.ac.cput.factory;

import za.ac.cput.domain.Student;
import za.ac.cput.util.Helper;

public class StudentFactory {
    public static Student createStudent(String studentID, String firstName, String lastName) {
        if (Helper.isNullOrEmpty(studentID))
            return null;
        if (Helper.isNullOrEmpty(firstName))
            return null;
        if (Helper.isNullOrEmpty(lastName))
            return null;

        return new Student.Builder()
                .setStudentID(studentID)
                .setFirstName(firstName)
                .setLastName(lastName)
                .build();
    }
}


