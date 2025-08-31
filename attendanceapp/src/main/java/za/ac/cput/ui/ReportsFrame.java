package za.ac.cput.ui;

import za.ac.cput.client.AttendanceRecordClient;
import za.ac.cput.client.StudentClient;
import za.ac.cput.domain.AttendanceRecord;
import za.ac.cput.domain.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.List;

public class ReportsFrame extends JFrame {
    private final AttendanceRecordClient recordClient = new AttendanceRecordClient();
    private final StudentClient studentClient = new StudentClient();

    private JComboBox<StudentItem> studentCombo;
    private JTextField startField;
    private JTextField endField;
    private JButton filterBtn;
    private JTable table;
    private JLabel totalsLabel;

    public ReportsFrame() {
        setTitle("Reports & Records");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10,10));

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

        table = new JTable(new DefaultTableModel(new Object[]{"Date","Status","Classroom","Teacher"}, 0));
        JScrollPane scroll = new JScrollPane(table);

        totalsLabel = new JLabel("Total Present: 0   Total Absent: 0");

        add(filter, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(totalsLabel, BorderLayout.SOUTH);

        loadStudents();
        filterBtn.addActionListener(e -> doFilter());
    }

    private void loadStudents(){
        try {
            List<Student> students = studentClient.getAll();
            DefaultComboBoxModel<StudentItem> model = new DefaultComboBoxModel<>();
            for(Student s : students){
                model.addElement(new StudentItem(s.getStudentID(), s.getFirstName() + " " + s.getLastName()));
            }
            studentCombo.setModel(model);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Failed to load students: " + ex.getMessage());
        }
    }

    private void doFilter(){
        StudentItem sel = (StudentItem) studentCombo.getSelectedItem();
        if(sel == null){
            JOptionPane.showMessageDialog(this, "Select a student first.");
            return;
        }
        String start = startField.getText().trim();
        String end = endField.getText().trim();
        // basic validation
        if(!start.isEmpty() && !isIsoDate(start)){ JOptionPane.showMessageDialog(this, "Start date must be YYYY-MM-DD"); return; }
        if(!end.isEmpty() && !isIsoDate(end)){ JOptionPane.showMessageDialog(this, "End date must be YYYY-MM-DD"); return; }

        try {
            List<AttendanceRecord> list = recordClient.findByStudentAndDateRange(sel.id, start.isEmpty()?null:start, end.isEmpty()?null:end);
            populateTable(list);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Search failed: " + ex.getMessage());
        }
    }

    private void populateTable(List<AttendanceRecord> list){
        DefaultTableModel m = new DefaultTableModel(new Object[]{"Date","Status","Classroom","Teacher"}, 0);
        int present = 0, absent = 0;
        for(AttendanceRecord r : list){
            String status = r.getStatus();
            if(status != null && status.equalsIgnoreCase("Present")) present++;
            else if(status != null && status.equalsIgnoreCase("Absent")) absent++;
            String classroom = r.getClassroom() != null ? r.getClassroom().getClassName() : "";
            String teacher = r.getTeacher() != null ? (r.getTeacher().getFirstName() + " " + r.getTeacher().getLastName()) : "";
            m.addRow(new Object[]{ r.getDate(), status, classroom, teacher });
        }
        table.setModel(m);
        totalsLabel.setText("Total Present: " + present + "   Total Absent: " + absent);
    }

    private boolean isIsoDate(String s){
        try { LocalDate.parse(s); return true; } catch(Exception e) { return false; }
    }

    private static class StudentItem {
        final String id; final String label;
        StudentItem(String id, String label){ this.id=id; this.label=label; }
        @Override public String toString(){ return label; }
    }
}
