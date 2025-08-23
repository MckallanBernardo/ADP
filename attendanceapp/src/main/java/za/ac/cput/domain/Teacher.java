package za.ac.cput.domain;

import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Teacher {
    @Id
    private String teacherID;
    private String firstName;
    private String lastName;
    private String subject;


    protected Teacher() {
    }
    private Teacher(Builder builder){
        this.teacherID = builder.teacherID;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.subject = builder.subject;
    }

    public String getTeacherID() {
        return teacherID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSubject() {
        return subject;
    }
    public static class Builder {
        private String teacherID;
        private String firstName;
        private String lastName;
        private String subject;

        public Builder setTeacherID(String teacherID) {
            this.teacherID = teacherID;
            return this;
        }

        public Builder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setSubject(String subject) {
            this.subject = subject;
            return this;
        }

        public Builder copy(Teacher teacher) {
            this.teacherID = teacher.getTeacherID();
            this.firstName = teacher.getFirstName();
            this.lastName = teacher.getLastName();
            this.subject = teacher.getSubject();
            return this;
        }

        public Teacher build() {
            return new Teacher(this);
        }
    }
}
