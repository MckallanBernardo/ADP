package za.ac.cput.ui;

import za.ac.cput.client.ClassroomClient;
import za.ac.cput.domain.Classroom;
import za.ac.cput.factory.ClassroomFactory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ManageClassroomFrame extends JFrame {
    private final ClassroomClient client;
    private JTable table;
    private JTextField idField, nameField;

    public ManageClassroomFrame() {
        this.client = new ClassroomClient();

        setTitle("Manage Classrooms");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10,10));

        // Top form
        JPanel form = new JPanel(new GridLayout(2,2,10,10));
        idField = new JTextField();
        nameField = new JTextField();
        form.add(new JLabel("Classroom ID:"));
        form.add(idField);
        form.add(new JLabel("Classroom Name:"));
        form.add(nameField);

        // Buttons
        JPanel buttons = new JPanel();
        JButton addBtn = new JButton("Add");
        JButton updateBtn = new JButton("Update");
        JButton deleteBtn = new JButton("Delete");
        JButton refreshBtn = new JButton("Refresh");
        buttons.add(addBtn);
        buttons.add(updateBtn);
        buttons.add(deleteBtn);
        buttons.add(refreshBtn);

        // Table
        table = new JTable(new DefaultTableModel(new Object[]{"ID", "Name"}, 0));
        JScrollPane scrollPane = new JScrollPane(table);

        add(form, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);

        // Load data initially
        loadTable();

        // Actions
        addBtn.addActionListener(e -> addClassroom());
        updateBtn.addActionListener(e -> updateClassroom());
        deleteBtn.addActionListener(e -> deleteClassroom());
        refreshBtn.addActionListener(e -> loadTable());

        table.getSelectionModel().addListSelectionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                idField.setText(table.getValueAt(row, 0).toString());
                nameField.setText(table.getValueAt(row, 1).toString());
            }
        });
    }

    private void loadTable() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        List<Classroom> classrooms = client.getAll();
        for (Classroom c : classrooms) {
            model.addRow(new Object[]{c.getClassroomId(), c.getClassName()});
        }
    }

    private void addClassroom() {
        String id = idField.getText().trim();
        String name = nameField.getText().trim();
        if (id.isEmpty() || name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Fill in both fields");
            return;
        }
        Classroom c = ClassroomFactory.createClassroom(id, name);
        client.create(c);
        loadTable();
    }

    private void updateClassroom() {
        String id = idField.getText().trim();
        String name = nameField.getText().trim();
        if (id.isEmpty() || name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Select a classroom first");
            return;
        }
        Classroom c = new Classroom.Builder().setClassroomId(id).setClassName(name).build();
        client.update(c);
        loadTable();
    }

    private void deleteClassroom() {
        String id = idField.getText().trim();
        if (id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Select a classroom first");
            return;
        }
        client.delete(id);
        loadTable();
    }
}
