package za.ac.cput.factory;

import za.ac.cput.domain.Classroom;
import za.ac.cput.domain.Student;
import za.ac.cput.domain.Teacher;
import za.ac.cput.util.Helper;

import java.util.List;

public class ClassroomFactory {
    public Classroom createClassroom(String classroomId, String className){
        if (Helper.isNullOrEmpty(classroomId))
            return null;
        if (Helper.isNullOrEmpty(className))
            return null;

        return new Classroom.Builder()
                .setClassroomId(classroomId)
                .setClassName(className)
                .build();
    }
}
