package za.ac.cput.ui;

import za.ac.cput.client.AttendanceRecordClient;
import za.ac.cput.client.ClassroomClient;
import za.ac.cput.client.StudentClient;
import za.ac.cput.client.TeacherClient;
import za.ac.cput.domain.AttendanceRecord;
import za.ac.cput.domain.Classroom;
import za.ac.cput.domain.Student;
import za.ac.cput.domain.Teacher;
import za.ac.cput.factory.AttendanceRecordFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class TakeAttendanceFrame extends JFrame {
    private final StudentClient studentClient = new StudentClient();
    private final ClassroomClient classroomClient = new ClassroomClient();
    private final TeacherClient teacherClient = new TeacherClient();
    private final AttendanceRecordClient recordClient = new AttendanceRecordClient();

    private JTable studentTable;
    private JComboBox<ClassroomItem> classroomCombo;
    private JComboBox<TeacherItem> teacherCombo;
    private JSpinner dateSpinner;

    public TakeAttendanceFrame() {
        setTitle("Take Attendance");
        setSize(900, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));

        // Classroom combo
        classroomCombo = new JComboBox<>();
        top.add(new JLabel("Classroom:"));
        top.add(classroomCombo);

        // Teacher combo
        teacherCombo = new JComboBox<>();
        top.add(new JLabel("Teacher:"));
        top.add(teacherCombo);

        // Date picker
        dateSpinner = new JSpinner(new SpinnerDateModel(new Date(), null, null, java.util.Calendar.DAY_OF_MONTH));
        JSpinner.DateEditor editor = new JSpinner.DateEditor(dateSpinner, "yyyy-MM-dd");
        dateSpinner.setEditor(editor);
        top.add(new JLabel("Date:"));
        top.add(dateSpinner);

        JButton loadBtn = new JButton("Load students");
        top.add(loadBtn);

        add(top, BorderLayout.NORTH);

        studentTable = new JTable(new DefaultTableModel(new Object[]{"Student ID", "First Name", "Last Name", "Present"}, 0) {
            @Override public Class<?> getColumnClass(int columnIndex) {
                if (columnIndex == 3) return Boolean.class;
                return super.getColumnClass(columnIndex);
            }
            @Override public boolean isCellEditable(int row, int column) {
                return column == 3;
            }
        });
        add(new JScrollPane(studentTable), BorderLayout.CENTER);

        JButton saveBtn = new JButton("Save Attendance");
        add(saveBtn, BorderLayout.SOUTH);

        // Load dropdowns
        loadClassrooms();
        loadTeachers();

        loadBtn.addActionListener(e -> loadStudents());
        saveBtn.addActionListener(e -> saveAttendance());
    }

    private void loadClassrooms() {
        try {
            List<Classroom> rooms = classroomClient.getAll();
            classroomCombo.removeAllItems();
            for (Classroom c : rooms) {
                classroomCombo.addItem(new ClassroomItem(c.getClassroomId(), c.getClassName()));
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Failed to load classrooms: " + ex.getMessage());
        }
    }

    private void loadTeachers() {
        try {
            List<Teacher> teachers = teacherClient.getAll();
            teacherCombo.removeAllItems();
            for (Teacher t : teachers) {
                teacherCombo.addItem(new TeacherItem(t.getTeacherID(), t.getFirstName(), t.getLastName()));
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Failed to load teachers: " + ex.getMessage());
        }
    }

    private void loadStudents() {
        DefaultTableModel model = (DefaultTableModel) studentTable.getModel();
        model.setRowCount(0);
        try {
            List<Student> students = studentClient.getAll();
            for (Student s : students) {
                model.addRow(new Object[]{s.getStudentID(), s.getFirstName(), s.getLastName(), Boolean.FALSE});
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Failed to load students: " + ex.getMessage());
        }
    }

    private void saveAttendance() {
        ClassroomItem selectedClassroom = (ClassroomItem) classroomCombo.getSelectedItem();
        TeacherItem selectedTeacher = (TeacherItem) teacherCombo.getSelectedItem();

        if (selectedClassroom == null) {
            JOptionPane.showMessageDialog(this, "Select a classroom first");
            return;
        }
        if (selectedTeacher == null) {
            JOptionPane.showMessageDialog(this, "Select a teacher first");
            return;
        }

        LocalDate date = ((Date) dateSpinner.getValue()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        DefaultTableModel model = (DefaultTableModel) studentTable.getModel();
        int rows = model.getRowCount();
        int saved = 0;

        for (int i = 0; i < rows; i++) {
            String studentId = (String) model.getValueAt(i, 0);
            boolean present = Boolean.TRUE.equals(model.getValueAt(i, 3));
            String status = present ? "PRESENT" : "ABSENT";
            try {
                Classroom classroom = new Classroom.Builder()
                        .setClassroomId(selectedClassroom.id)
                        .setClassName(selectedClassroom.name)
                        .build();

                Student stu = new Student.Builder()
                        .setStudentID(studentId)
                        .setFirstName((String) model.getValueAt(i, 1))
                        .setLastName((String) model.getValueAt(i, 2))
                        .build();

                Teacher teacher = new Teacher.Builder()
                        .setTeacherID(selectedTeacher.id)
                        .setFirstName(selectedTeacher.firstName)
                        .setLastName(selectedTeacher.lastName)
                        .build();

                AttendanceRecord record = AttendanceRecordFactory.createAttendanceRecord(
                        "REC-" + UUID.randomUUID(),
                        date,
                        status,
                        classroom,
                        stu,
                        teacher
                );

                recordClient.create(record);
                saved++;
            } catch (Exception ex) {
                // continue
            }
        }
        JOptionPane.showMessageDialog(this, "Saved " + saved + " records for " + date.toString());
    }

    private static class ClassroomItem {
        String id;
        String name;
        ClassroomItem(String id, String name) { this.id = id; this.name = name; }
        @Override public String toString() { return name + " (" + id + ")"; }
    }

    private static class TeacherItem {
        String id;
        String firstName;
        String lastName;
        TeacherItem(String id, String firstName, String lastName) {
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
        }
        @Override public String toString() { return firstName + " " + lastName + " (" + id + ")"; }
    }
}
