package za.ac.cput.domain;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "attendancerecord")
public class AttendanceRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String recordID;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "status")
    private String status;


    @OneToOne
    @JoinColumn(name = "classroom_classroom_id")
    private Classroom classroom;

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }


    protected AttendanceRecord(){

    }

    private AttendanceRecord(Builder builder){
        this.recordID = builder.recordID;
        this.date = builder.date;
        this.status = builder.status;
        this.classroom = builder.classroom;
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

    public static class Builder{
        private String recordID;
        private LocalDate date;
        private String status;
        private Classroom classroom;

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

        public Builder copy(AttendanceRecord attendanceRecord){
            this.recordID = attendanceRecord.recordID;
            this.date = attendanceRecord.date;
            this.status = attendanceRecord.status;
            this.classroom = attendanceRecord.classroom;
            return this;
        }

        public AttendanceRecord build(){
            return new AttendanceRecord(this);
        }
    }
}
