package za.ac.cput.ui;

import za.ac.cput.client.TeacherClient;
import za.ac.cput.domain.Teacher;
import za.ac.cput.factory.TeacherFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ManageTeacherFrame extends JFrame {
    private final TeacherClient client;
    private JTable table;
    private JTextField idField, firstNameField, lastNameField, subjectField;

    public ManageTeacherFrame() {
        this.client = new TeacherClient();

        setTitle("Manage Teachers");
        setSize(720, 440);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10,10));

        JPanel form = new JPanel(new GridLayout(4,2,10,10));
        idField = new JTextField();
        firstNameField = new JTextField();
        lastNameField = new JTextField();
        subjectField = new JTextField();
        form.add(new JLabel("Teacher ID:"));
        form.add(idField);
        form.add(new JLabel("First Name:"));
        form.add(firstNameField);
        form.add(new JLabel("Last Name:"));
        form.add(lastNameField);
        form.add(new JLabel("Subject:"));
        form.add(subjectField);

        JPanel buttons = new JPanel();
        JButton addBtn = new JButton("Add");
        JButton updateBtn = new JButton("Update");
        JButton deleteBtn = new JButton("Delete");
        JButton refreshBtn = new JButton("Refresh");
        buttons.add(addBtn);
        buttons.add(updateBtn);
        buttons.add(deleteBtn);
        buttons.add(refreshBtn);

        table = new JTable(new DefaultTableModel(new Object[]{"ID", "First Name", "Last Name", "Subject"}, 0));
        JScrollPane scrollPane = new JScrollPane(table);

        add(form, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);

        loadTable();

        addBtn.addActionListener(e -> addTeacher());
        updateBtn.addActionListener(e -> updateTeacher());
        deleteBtn.addActionListener(e -> deleteTeacher());
        refreshBtn.addActionListener(e -> loadTable());

        table.getSelectionModel().addListSelectionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                idField.setText(table.getValueAt(row, 0).toString());
                firstNameField.setText(table.getValueAt(row, 1).toString());
                lastNameField.setText(table.getValueAt(row, 2).toString());
                subjectField.setText(table.getValueAt(row, 3).toString());
            }
        });
    }

    private void loadTable() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        try {
            List<Teacher> teachers = client.getAll();
            for (Teacher t : teachers) {
                model.addRow(new Object[]{t.getTeacherID(), t.getFirstName(), t.getLastName(), t.getSubject()});
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Failed to load teachers: " + ex.getMessage());
        }
    }

    private void addTeacher() {
        String id = idField.getText().trim();
        String fn = firstNameField.getText().trim();
        String ln = lastNameField.getText().trim();
        String sub = subjectField.getText().trim();
        if (id.isEmpty() || fn.isEmpty() || ln.isEmpty() || sub.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Fill in all fields");
            return;
        }
        try {
            Teacher t = TeacherFactory.createTeacher(id, fn, ln, sub);
            client.create(t);
            loadTable();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Create failed: " + ex.getMessage());
        }
    }

    private void updateTeacher() {
        String id = idField.getText().trim();
        String fn = firstNameField.getText().trim();
        String ln = lastNameField.getText().trim();
        String sub = subjectField.getText().trim();
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Select a teacher first");
            return;
        }
        try {
            Teacher t = new Teacher.Builder().setTeacherID(id).setFirstName(fn).setLastName(ln).setSubject(sub).build();
            client.update(t);
            loadTable();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Update failed: " + ex.getMessage());
        }
    }

    private void deleteTeacher() {
        String id = idField.getText().trim();
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Select a teacher first");
            return;
        }
        try {
            client.delete(id);
            loadTable();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Delete failed: " + ex.getMessage());
        }
    }
}
