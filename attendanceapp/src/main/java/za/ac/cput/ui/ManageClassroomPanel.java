package za.ac.cput.ui;

import za.ac.cput.client.ClassroomClient;
import za.ac.cput.domain.Classroom;
import za.ac.cput.factory.ClassroomFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ManageClassroomPanel extends JPanel {
    private final ClassroomClient client;
    private JTable table;
    private JTextField idField, nameField;

    public ManageClassroomPanel() {
        this.client = new ClassroomClient();

        setLayout(new BorderLayout(10, 10));

        // === TOP FORM ===
        JPanel form = new JPanel(new GridLayout(2, 2, 10, 10));
        form.setBorder(BorderFactory.createTitledBorder("Classroom Details"));

        form.add(new JLabel("Classroom ID:"));
        idField = new JTextField();
        form.add(idField);

        form.add(new JLabel("Classroom Name:"));
        nameField = new JTextField();
        form.add(nameField);

        add(form, BorderLayout.NORTH);

        // === TABLE ===
        table = new JTable(new DefaultTableModel(new Object[]{"ID", "Class Name"}, 0));
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Existing Classrooms"));
        add(scrollPane, BorderLayout.CENTER);

        // === BUTTONS ===
        JPanel buttons = new JPanel();
        JButton addBtn = new JButton("Add");
        JButton updateBtn = new JButton("Update");
        JButton deleteBtn = new JButton("Delete");
        JButton refreshBtn = new JButton("Refresh");
        buttons.add(addBtn);
        buttons.add(updateBtn);
        buttons.add(deleteBtn);
        buttons.add(refreshBtn);
        add(buttons, BorderLayout.SOUTH);

        // === ACTION LISTENERS ===
        addBtn.addActionListener(e -> addClassroom());
        updateBtn.addActionListener(e -> updateClassroom());
        deleteBtn.addActionListener(e -> deleteClassroom());
        refreshBtn.addActionListener(e -> loadClassrooms());

        // Load table data on startup
        loadClassrooms();

        // Populate form when row selected
        table.getSelectionModel().addListSelectionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                idField.setText(table.getValueAt(row, 0).toString());
                nameField.setText(table.getValueAt(row, 1).toString());
                idField.setEditable(false); // ID read-only during Update/Delete
            } else {
                idField.setEditable(true); // Add mode
                idField.setText("");
                nameField.setText("");
            }
        });
    }

    private void addClassroom() {
        String id = idField.getText().trim();
        String name = nameField.getText().trim();

        if (id.isEmpty() || name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in both fields.", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            Classroom classroom = ClassroomFactory.createClassroom(id, name);

            if (classroom == null) {
                JOptionPane.showMessageDialog(this, "Invalid classroom details.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            client.create(classroom);
            loadClassrooms();

            JOptionPane.showMessageDialog(this, "Classroom added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error adding classroom: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateClassroom() {
        String id = idField.getText().trim();
        String name = nameField.getText().trim();

        if (id.isEmpty() || name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Select a classroom and enter a valid name.", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            Classroom classroom = new Classroom.Builder()
                    .setClassroomId(id)
                    .setClassName(name)
                    .build();

            client.update(classroom);
            loadClassrooms();

            JOptionPane.showMessageDialog(this, "Classroom updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error updating classroom: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteClassroom() {
        String id = idField.getText().trim();

        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Select a classroom to delete.", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete this classroom?",
                "Confirm Delete",
                JOptionPane.YES_NO_OPTION);

        if (confirm != JOptionPane.YES_OPTION) return;

        try {
            client.delete(id);
            loadClassrooms();

            JOptionPane.showMessageDialog(this, "Classroom deleted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearFields();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error deleting classroom: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadClassrooms() {
        try {
            List<Classroom> classrooms = client.getAll();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0);

            for (Classroom c : classrooms) {
                model.addRow(new Object[]{c.getClassroomId(), c.getClassName()});
            }

            // Reset form if no row is selected
            if (table.getSelectedRow() < 0) {
                clearFields();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error loading classrooms: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        idField.setEditable(true);
        idField.setText("");
        nameField.setText("");
        table.clearSelection();
    }
}
