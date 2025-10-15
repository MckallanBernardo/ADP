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
        form.setBorder(BorderFactory.createTitledBorder("Add Classroom"));

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
        JButton refreshBtn = new JButton("Refresh");
        buttons.add(addBtn);
        buttons.add(refreshBtn);
        add(buttons, BorderLayout.SOUTH);

        // === ACTION LISTENERS ===
        addBtn.addActionListener(e -> addClassroom());
        refreshBtn.addActionListener(e -> loadClassrooms());

        // Load table data on startup
        loadClassrooms();
    }

    private void addClassroom() {
        String id = idField.getText().trim();
        String name = nameField.getText().trim();

        if (id.isEmpty() || name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in both fields.", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // ✅ Use your real factory method
            Classroom classroom = ClassroomFactory.createClassroom(id, name);

            if (classroom == null) {
                JOptionPane.showMessageDialog(this, "Invalid classroom details.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            client.create(classroom);
            loadClassrooms();

            JOptionPane.showMessageDialog(this, "Classroom added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            idField.setText("");
            nameField.setText("");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error adding classroom: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void loadClassrooms() {
        try {
            List<Classroom> classrooms = client.getAll();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0);

            // ✅ Match your actual domain getters
            for (Classroom c : classrooms) {
                model.addRow(new Object[]{c.getClassroomId(), c.getClassName()});
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error loading classrooms: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
