package za.ac.cput.ui;

import za.ac.cput.client.StudentClient;
import za.ac.cput.domain.Student;
import za.ac.cput.factory.StudentFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ManageStudentPanel extends JPanel {
    private final StudentClient client;
    private JTable table;
    private JTextField idField, firstNameField, lastNameField;

    public ManageStudentPanel() {
        this.client = new StudentClient();

        setLayout(new BorderLayout(10,10));

        // === Form Panel (Top) ===
        JPanel form = new JPanel(new GridLayout(3,2,10,10));
        idField = new JTextField();
        firstNameField = new JTextField();
        lastNameField = new JTextField();
        form.add(new JLabel("Student ID:"));
        form.add(idField);
        form.add(new JLabel("First Name:"));
        form.add(firstNameField);
        form.add(new JLabel("Last Name:"));
        form.add(lastNameField);

        // === Buttons (Bottom) ===
        JPanel buttons = new JPanel();
        JButton addBtn = new JButton("Add");
        JButton updateBtn = new JButton("Update");
        JButton deleteBtn = new JButton("Delete");
        JButton refreshBtn = new JButton("Refresh");
        buttons.add(addBtn);
        buttons.add(updateBtn);
        buttons.add(deleteBtn);
        buttons.add(refreshBtn);

        // === Table (Center) ===
        table = new JTable(new DefaultTableModel(new Object[]{"ID", "First Name", "Last Name"}, 0));
        JScrollPane scrollPane = new JScrollPane(table);

        add(form, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);

        // === Load Data ===
        loadTable();

        // === Action Listeners ===
        addBtn.addActionListener(e -> {
            // === Added for Update/Delete mode ===
            idField.setEditable(true); // Allow editing ID in Add mode

            addStudent();
        });

        updateBtn.addActionListener(e -> {
            // === Added for Update/Delete mode ===
            idField.setEditable(false); // Prevent ID changes when updating

            updateStudent();
            idField.setEditable(true); // Restore editability after update
        });

        deleteBtn.addActionListener(e -> {
            // === Added for Update/Delete mode ===
            idField.setEditable(false); // Prevent ID changes when deleting

            deleteStudent();
            idField.setEditable(true); // Restore editability after delete
        });

        refreshBtn.addActionListener(e -> {

            loadTable();
            clearFields();

        });

        // When selecting a row, populate text fields
        table.getSelectionModel().addListSelectionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                idField.setText(table.getValueAt(row, 0).toString());
                firstNameField.setText(table.getValueAt(row, 1).toString());
                lastNameField.setText(table.getValueAt(row, 2).toString());

                // === Added for Update/Delete mode ===
                // Make ID read-only when a record is selected
                idField.setEditable(false);
            }
        });
    }

    // === Load Table Data ===
    private void loadTable() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        try {
            List<Student> students = client.getAll();
            for (Student s : students) {
                model.addRow(new Object[]{s.getStudentID(), s.getFirstName(), s.getLastName()});
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Failed to load students: " + ex.getMessage());
        }
    }

    // === Add Student ===
    private void addStudent() {
        String id = idField.getText().trim();
        String fn = firstNameField.getText().trim();
        String ln = lastNameField.getText().trim();
        if (id.isEmpty() || fn.isEmpty() || ln.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Fill in all fields");
            return;
        }
        try {
            Student s = StudentFactory.createStudent(id, fn, ln);
            client.create(s);
            loadTable();
            clearFields();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Create failed: " + ex.getMessage());
        }
    }

    // === Update Student ===
    private void updateStudent() {
        String id = idField.getText().trim();
        String fn = firstNameField.getText().trim();
        String ln = lastNameField.getText().trim();
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Select a student first");
            return;
        }
        try {
            Student s = new Student.Builder()
                    .setStudentID(id)
                    .setFirstName(fn)
                    .setLastName(ln)
                    .build();
            client.update(s);
            loadTable();
            clearFields();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Update failed: " + ex.getMessage());
        }
    }

    // === Delete Student ===
    private void deleteStudent() {
        String id = idField.getText().trim();
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Select a student first");
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

    // === Clear Input Fields ===
    private void clearFields() {
        idField.setText("");
        firstNameField.setText("");
        lastNameField.setText("");
        idField.setEditable(true); // === Added for Update/Delete mode === Reset ID field to editable
    }
}
