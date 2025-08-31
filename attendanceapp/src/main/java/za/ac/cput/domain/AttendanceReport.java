package za.ac.cput.domain;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class AttendanceReport {

    @Id
    private String reportID;
    private LocalDate startDate;
    private LocalDate endDate;
    private int totalDaysPresent;
    private int totalDaysAbsent;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AttendanceRecord> records;

    protected AttendanceReport() {}

    private AttendanceReport(Builder builder) {
        this.reportID = builder.reportID;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.records = builder.records;
        this.totalDaysPresent = builder.totalDaysPresent;
        this.totalDaysAbsent = builder.totalDaysAbsent;
    }

    public String getReportID() {
        return reportID;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public List<AttendanceRecord> getRecords() {
        return records;
    }

    public int getTotalDaysPresent() {
        return totalDaysPresent;
    }

    public int getTotalDaysAbsent() {
        return totalDaysAbsent;
    }

    // ✅ Builder pattern (unchanged, minimal updates only)
    public static class Builder {
        private String reportID;
        private LocalDate startDate;
        private LocalDate endDate;
        private List<AttendanceRecord> records;
        private int totalDaysPresent;
        private int totalDaysAbsent;

        public Builder setReportID(String reportID) {
            this.reportID = reportID;
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

        public Builder setRecords(List<AttendanceRecord> records) {
            this.records = records;
            return this;
        }

        public Builder setTotalDaysPresent(int totalDaysPresent) {
            this.totalDaysPresent = totalDaysPresent;
            return this;
        }

        public Builder setTotalDaysAbsent(int totalDaysAbsent) {
            this.totalDaysAbsent = totalDaysAbsent;
            return this;
        }

        public AttendanceReport build() {
            return new AttendanceReport(this);
        }
    }
}
