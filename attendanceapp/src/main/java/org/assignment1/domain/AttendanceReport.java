package org.assignment1.domain;

import java.time.LocalDate;
import java.util.ArrayList;

/* AttendanceReport.java
AttendanceReport model class
Author: Keagan Christians (230702732)
Date: 28 March 2025
*/

public class AttendanceReport {
    private String reportID;
    private ArrayList<AttendanceRecord> records;
    private LocalDate startDate;
    private LocalDate endDate;
    private int totalDaysPresent;
    private int totalDaysAbsent;

    public AttendanceReport(Builder builder) {
        this.reportID = builder.reportID;
        this.records = builder.records;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        calculateAttendance();
    }

    public String getReportID() {
        return reportID;
    }

    public ArrayList<AttendanceRecord> getRecords() {
        return records;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public int getTotalDaysPresent() {
        return totalDaysPresent;
    }

    public int getTotalDaysAbsent() {
        return totalDaysAbsent;
    }

    public void setRecords(ArrayList<AttendanceRecord> records) {
        this.records = records;
        calculateAttendance();
    }

    public void calculateAttendance() {
        totalDaysPresent = 0;
        totalDaysAbsent = 0;

        for (AttendanceRecord record : records) {
            if (record.getStatus().equalsIgnoreCase("PRESENT")) {
                totalDaysPresent++;
            } else if (record.getStatus().equalsIgnoreCase("ABSENT")) {
                totalDaysAbsent++;
            }
        }
    }

    public void printReport() {
        System.out.println("Attendance Report ID: " + reportID);
        System.out.println("From: " + startDate + " To: " + endDate);
        System.out.println("Total Days Present: " + totalDaysPresent);
        System.out.println("Total Days Absent: " + totalDaysAbsent);
    }

    public static class Builder {
        private String reportID;
        private ArrayList<AttendanceRecord> records;
        private LocalDate startDate;
        private LocalDate endDate;

        public Builder setReportID(String reportID) {
            this.reportID = reportID;
            return this;
        }

        public Builder setRecords(ArrayList<AttendanceRecord> records) {
            this.records = records;
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
            return new AttendanceReport(this);
        }
    }
}
