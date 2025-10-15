package za.ac.cput.ui;

import za.ac.cput.client.TeacherClient;
import za.ac.cput.domain.Teacher;
import za.ac.cput.factory.TeacherFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ManageTeacherPanel extends JPanel {

    private final TeacherClient client;
    private JTable table;
    private JTextField idField, firstNameField, lastNameField, subjectField;

    public ManageTeacherPanel() {
        this.client = new TeacherClient();

        setLayout(new BorderLayout(10, 10));

        // === Form Panel (Top) ===
        JPanel form = new JPanel(new GridLayout(4, 2, 10, 10));
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

        // === Buttons Panel (Bottom) ===
        JPanel buttons = new JPanel();
        JButton addBtn = new JButton("Add");
        JButton updateBtn = new JButton("Update");
        JButton deleteBtn = new JButton("Delete");
        JButton refreshBtn = new JButton("Refresh");

        buttons.add(addBtn);
        buttons.add(updateBtn);
        buttons.add(deleteBtn);
        buttons.add(refreshBtn);

        // === Table Panel (Center) ===
        table = new JTable(new DefaultTableModel(new Object[]{"ID", "First Name", "Last Name", "Subject"}, 0));
        JScrollPane scrollPane = new JScrollPane(table);

        add(form, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);

        // === Load data initially ===
        loadTable();

        // === Action Listeners ===
        addBtn.addActionListener(e -> addTeacher());
        updateBtn.addActionListener(e -> updateTeacher());
        deleteBtn.addActionListener(e -> deleteTeacher());
        refreshBtn.addActionListener(e -> {
            loadTable();
            clearFields(); // 🧹 Clear input fields when Refresh is clicked
        });

        // Populate fields when selecting a row
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

    // === Load Table Data ===
    private void loadTable() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        try {
            List<Teacher> teachers = client.getAll();
            for (Teacher t : teachers) {
                model.addRow(new Object[]{
                        t.getTeacherID(),
                        t.getFirstName(),
                        t.getLastName(),
                        t.getSubject()
                });
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Failed to load teachers: " + ex.getMessage());
        }
    }

    // === Add Teacher ===
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
            clearFields();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Create failed: " + ex.getMessage());
        }
    }

    // === Update Teacher ===
    private void updateTeacher() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Select a teacher first");
            return;
        }

        // Get the original ID from the selected table row
        String originalId = table.getValueAt(selectedRow, 0).toString();
        String enteredId = idField.getText().trim();
        String fn = firstNameField.getText().trim();
        String ln = lastNameField.getText().trim();
        String sub = subjectField.getText().trim();

        if (enteredId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Teacher ID cannot be empty");
            return;
        }


        if (!enteredId.equals(originalId)) {
            JOptionPane.showMessageDialog(this,
                    "You cannot change the Teacher ID when updating an entry.",
                    "Invalid Update",
                    JOptionPane.WARNING_MESSAGE);
            idField.setText(originalId); // reset to original
            return;
        }

        try {
            Teacher t = new Teacher.Builder()
                    .setTeacherID(originalId)  // keep original ID
                    .setFirstName(fn)
                    .setLastName(ln)
                    .setSubject(sub)
                    .build();

            client.update(t);
            loadTable();
            clearFields();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Update failed: " + ex.getMessage());
        }
    }

    // === Delete Teacher ===
    private void deleteTeacher() {
        String id = idField.getText().trim();
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Select a teacher first");
            return;
        }

        try {
            client.delete(id);
            loadTable();
            clearFields();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Delete failed: " + ex.getMessage());
        }
    }

    // === Clear input fields ===
    private void clearFields() {
        idField.setText("");
        firstNameField.setText("");
        lastNameField.setText("");
        subjectField.setText("");
    }
}
