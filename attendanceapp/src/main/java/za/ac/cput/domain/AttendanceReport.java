package za.ac.cput.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import za.ac.cput.domain.Teacher;


@Entity
public class AttendanceReport {

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private String reportID;

    @OneToMany
    private ArrayList<AttendanceRecord> records;

    private LocalDate startDate;
    private LocalDate endDate;
    private int totalDaysPresent;
    private int totalDaysAbsent;

    protected AttendanceReport() {}

    private AttendanceReport(Builder builder) {
        this.reportID = builder.reportID;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;

        // Filter records to only include those within the date range
        this.records = (ArrayList<AttendanceRecord>) builder.records.stream()
                .filter(record -> !record.getDate().isBefore(startDate) && !record.getDate().isAfter(endDate))
                .collect(Collectors.toList());

        calculateAttendance();
    }

    private void calculateAttendance() {
        totalDaysPresent = 0;
        totalDaysAbsent = 0;

        for (AttendanceRecord record : records) {
            if ("Present".equalsIgnoreCase(record.getStatus())) {
                totalDaysPresent++;
            } else if ("Absent".equalsIgnoreCase(record.getStatus())) {
                totalDaysAbsent++;
            }
        }
    }

    public void printReport() {
        System.out.println("===== Attendance Report =====");
        System.out.println("Report ID: " + reportID);
        System.out.println("Start Date: " + startDate);
        System.out.println("End Date: " + endDate);
        System.out.println("Total Days Present: " + totalDaysPresent);
        System.out.println("Total Days Absent: " + totalDaysAbsent);
        System.out.println("Filtered Records:");
        for (AttendanceRecord record : records) {
            System.out.println(record.toString());
        }

    }

    // Getters
    public String getReportID() { return reportID; }
    public ArrayList<AttendanceRecord> getRecords() { return records; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public int getTotalDaysPresent() { return totalDaysPresent; }
    public int getTotalDaysAbsent() { return totalDaysAbsent; }

    // Builder
    public static class Builder {
        private String reportID;
        private ArrayList<AttendanceRecord> records;
        private LocalDate startDate;
        private LocalDate endDate;

        public Builder setReportID(String reportID) {
            this.reportID = reportID;
            return this;
        }

        public Builder setRecords(List<AttendanceRecord> records) {
            this.records = new ArrayList<>(records); // store as ArrayList internally
            return this;
        }


        public Builder setStartDate(LocalDate startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder setEndDate(LocalDate endDate) {
            this.endDate = endDate;
            return this;
        }

        public AttendanceReport build() {
            if (reportID == null || records == null || startDate == null || endDate == null) {
                throw new IllegalArgumentException("All fields must be provided.");
            }
            return new AttendanceReport(this);
        }
    }
}