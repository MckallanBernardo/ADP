package za.ac.cput.domain;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "attendancerecord")
public class AttendanceRecord {
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private String recordID;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "status")
    private String status;

    @ManyToOne //lol
    @JoinColumn(name = "student_student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "classroom_classroom_id")
    private Classroom classroom;

    @ManyToOne
    @JoinColumn(name = "teacher_teacher_id")
    private Teacher teacher;

    protected AttendanceRecord(){
    }

    private AttendanceRecord(Builder builder){
        this.recordID = builder.recordID;
        this.date = builder.date;
        this.status = builder.status;
        this.classroom = builder.classroom;
        this.student = builder.student;
        this.teacher = builder.teacher;
    }

    public Student getStudent() {
        return student;
    }

    public String getRecordID(){
        return recordID;
    }

    public LocalDate getDate(){
        return date;
    }

    public String getStatus(){
        return status;
    }

    public Classroom getClassroom(){
        return classroom;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    @Override
    public String toString() {
        return "AttendanceRecord{" +
                "recordID='" + recordID + '\'' +
                ", date=" + date +
                ", status='" + status + '\'' +
                ", student=" + student +
                ", classroom=" + classroom +
                ", teacher=" + teacher +
                '}';
    }

    public static class Builder{
        private String recordID;
        private LocalDate date;
        private String status;
        private Classroom classroom;
        private Student student;
        private Teacher teacher;

        public Builder setRecordID(String recordID){
            this.recordID = recordID;
            return this;
        }

        public Builder setDate(LocalDate date){
            this.date = date;
            return this;
        }

        public Builder setStatus(String status){
            this.status = status;
            return this;
        }

        public Builder setClassroom(Classroom classroom){
            this.classroom = classroom;
            return this;
        }

        public Builder setStudent(Student student){
            this.student = student;
            return this;
        }

        public Builder setTeacher(Teacher teacher){
            this.teacher = teacher;
            return this;
        }

        public Builder copy(AttendanceRecord attendanceRecord){
            this.recordID = attendanceRecord.recordID;
            this.date = attendanceRecord.date;
            this.status = attendanceRecord.status;
            this.classroom = attendanceRecord.classroom;
            this.student = attendanceRecord.student;
            this.teacher = attendanceRecord.teacher;
            return this;
        }

        public AttendanceRecord build(){
            return new AttendanceRecord(this);
        }
    }
}
