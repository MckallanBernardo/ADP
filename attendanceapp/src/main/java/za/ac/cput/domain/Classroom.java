package za.ac.cput.domain;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Classroom {
    @Id
    private String classroomId;
    private String className;
    @OneToOne
    @JoinColumn(name = "teacher_teacher_id") //come back to this later
    private Teacher teacher;
    @OneToMany
    @JoinColumn(name = "classroom_id") //come back to this later
    private List<Student> student;


    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    protected Classroom() {
    }
    private Classroom(Builder builder){
        this.classroomId = builder.classroomId;
        this.className = builder.className;
        this.teacher = builder.teacher;
        this.student = builder.student;
    }

    public String getClassroomId() {
        return classroomId;
    }

    public String getClassName() {
        return className;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public List<Student> getStudent() {
        return student;
    }

    @Override
    public String toString() {
        return "Classroom{" +
                "classroomId='" + classroomId + '\'' +
                ", className='" + className + '\'' +
                ", teacher=" + teacher +
                ", student=" + student +
                '}';
    }

    public static class Builder{
        private String classroomId;
        private String className;
        private Teacher teacher;
        private List<Student> student;

        public Builder setClassroomId(String classroomId) {
            this.classroomId = classroomId;
            return this;
        }
        public Builder setClassName(String className) {
            this.className = className;
            return this;
        }
        public Builder setTeacher(Teacher teacher) {
            this.teacher = teacher;
            return this;
        }
        public Builder setStudent(List<Student> student) {
            this.student = student;
            return this;
        }
        public Builder copy(Classroom classroom) {
            this.classroomId = classroom.classroomId;
            this.className = classroom.className;
            this.teacher = classroom.teacher;
            this.student = classroom.student;
            return this;
        }

        public Classroom build() {
            return new Classroom(this);
        }
    }
}
