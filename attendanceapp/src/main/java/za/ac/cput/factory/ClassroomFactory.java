package za.ac.cput.factory;

import za.ac.cput.domain.Classroom;
import za.ac.cput.domain.Student;
import za.ac.cput.domain.Teacher;
import za.ac.cput.util.Helper;

import java.util.List;

public class ClassroomFactory {
    public Classroom createClassroom(String classroomId, String className,
                                     Teacher teacher, List<Student> student){
        if (Helper.isNullOrEmpty(classroomId))
            return null;
        if (Helper.isNullOrEmpty(className))
            return null;
        if (teacher == null)
            return null;
        if (student == null)
            return null;

        return new Classroom.Builder()
                .setClassroomId(classroomId)
                .setClassName(className)
                .setTeacher(teacher)
                .setStudent(student)
                .build();
    }
}
