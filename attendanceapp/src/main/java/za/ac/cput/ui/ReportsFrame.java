package za.ac.cput.ui;

import za.ac.cput.client.AttendanceRecordClient;
import za.ac.cput.client.StudentClient;
import za.ac.cput.client.AttendanceReportClient;
import za.ac.cput.domain.AttendanceRecord;
import za.ac.cput.domain.AttendanceReport;
import za.ac.cput.domain.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class ReportsFrame extends JFrame {
    private final AttendanceRecordClient recordClient = new AttendanceRecordClient();
    private final StudentClient studentClient = new StudentClient();
    private final AttendanceReportClient reportClient = new AttendanceReportClient();

    private JComboBox<StudentItem> studentCombo;
    private JTextField startField;
    private JTextField endField;
    private JButton filterBtn;
    private JButton saveBtn;
    private JTable table;
    private JLabel totalsLabel;

    int totalPresent = 0, totalAbsent = 0;

    // keep last loaded records for saving
    private List<AttendanceRecord> currentRecords;

    public ReportsFrame() {
        setTitle("Reports & Records");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top filter panel
        JPanel filter = new JPanel(new FlowLayout(FlowLayout.LEFT));
        studentCombo = new JComboBox<>();
        filter.add(new JLabel("Student:"));
        filter.add(studentCombo);

        startField = new JTextField(10);
        endField = new JTextField(10);
        startField.setToolTipText("YYYY-MM-DD");
        endField.setToolTipText("YYYY-MM-DD");
        filter.add(new JLabel("Start:"));
        filter.add(startField);
        filter.add(new JLabel("End:"));
        filter.add(endField);

        filterBtn = new JButton("Search");
        filter.add(filterBtn);

        saveBtn = new JButton("Save Report");
        saveBtn.setEnabled(false);
        filter.add(saveBtn);

        add(filter, BorderLayout.NORTH);

        // Table
        table = new JTable(new DefaultTableModel(new Object[]{"Date", "Status"}, 0));
        add(new JScrollPane(table), BorderLayout.CENTER);

        // Totals
        totalsLabel = new JLabel("Totals: ");
        add(totalsLabel, BorderLayout.SOUTH);

        // Load students for combo
        loadStudents();

        // Actions
        filterBtn.addActionListener(e -> loadRecords());
        saveBtn.addActionListener(e -> saveReport());
    }

    private void loadStudents() {
        try {
            List<Student> students = studentClient.getAll();
            studentCombo.removeAllItems();
            studentCombo.addItem(new StudentItem(null, "All students"));
            for (Student s : students) {
                String label = s.getFirstName() + " " + s.getLastName();
                studentCombo.addItem(new StudentItem(s.getStudentID(), label));
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error loading students: " + ex.getMessage());
        }
    }

    private void loadRecords() {
        try {
            String startTxt = startField.getText().trim();
            String endTxt = endField.getText().trim();
            if (!isIsoDate(startTxt) || !isIsoDate(endTxt)) {
                JOptionPane.showMessageDialog(this, "Please enter valid ISO dates (YYYY-MM-DD).");
                return;
            }

            LocalDate start = LocalDate.parse(startTxt);
            LocalDate end = LocalDate.parse(endTxt);

            StudentItem selected = (StudentItem) studentCombo.getSelectedItem();
            String selectedStudentId = selected != null ? selected.id : null;

            // Get all records and filter by date (and student if selected)
            List<AttendanceRecord> all = recordClient.getAll();
            currentRecords = all.stream()
                    .filter(r -> !r.getDate().isBefore(start) && !r.getDate().isAfter(end))
                    .filter(r -> selectedStudentId == null ||
                            (r.getStudent() != null && selectedStudentId.equals(r.getStudent().getStudentID())))
                    .collect(Collectors.toList());

            // Fill table + totals
            DefaultTableModel m = (DefaultTableModel) table.getModel();
            m.setRowCount(0);


            for (AttendanceRecord r : currentRecords) {
                m.addRow(new Object[]{r.getDate(), r.getStatus()});
                if ("Present".equalsIgnoreCase(r.getStatus())) totalPresent++;
                else if ("Absent".equalsIgnoreCase(r.getStatus())) totalAbsent++;
            }
            table.setModel(m);
            totalsLabel.setText("Total Present: " + totalPresent + "   Total Absent: " + totalAbsent);

            // enable save only if we have something
            saveBtn.setEnabled(!currentRecords.isEmpty());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error loading records: " + ex.getMessage());
        }
    }

    private void saveReport() {
        try {
            if (currentRecords == null || currentRecords.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No records to save. Search first.");
                return;
            }
            String startTxt = startField.getText().trim();
            String endTxt = endField.getText().trim();
            if (!isIsoDate(startTxt) || !isIsoDate(endTxt)) {
                JOptionPane.showMessageDialog(this, "Please enter valid ISO dates (YYYY-MM-DD).");
                return;
            }

            LocalDate start = LocalDate.parse(startTxt);
            LocalDate end = LocalDate.parse(endTxt);

            // Build AttendanceReport using your original builder API
            AttendanceReport report = new AttendanceReport.Builder()
                    .setReportID(UUID.randomUUID().toString())
                    .setRecords(currentRecords) // entity will re-filter by date range internally
                    .setStartDate(start)
                    .setEndDate(end)
                    .setTotalPresent(totalPresent)
                    .setTotalAbsent(totalAbsent)
                    .build();

            AttendanceReport saved = reportClient.create(report);
            JOptionPane.showMessageDialog(this,
                    "Report saved with ID: " + saved.getReportID());
            saveBtn.setEnabled(false);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error saving report: " + ex.getMessage());
        }
    }

    private boolean isIsoDate(String s) {
        try { LocalDate.parse(s); return true; } catch (Exception e) { return false; }
    }

    private static class StudentItem {
        final String id; final String label;
        StudentItem(String id, String label){ this.id = id; this.label = label; }
        @Override public String toString(){ return label; }
    }
}
