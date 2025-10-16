package za.ac.cput.ui;

import za.ac.cput.client.TeacherClient;
import za.ac.cput.domain.Teacher;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class LoginFrame extends JFrame {

    private final TeacherClient client;

    private JTextField lastNameField;
    private JPasswordField teacherIdField;

    private JTextField suFirst, suLast, suId, suSubject;

    public LoginFrame(TeacherClient client) {
        this.client = client;

        setTitle("AttendanceApp - Login / Sign up");
        setSize(750, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(Color.WHITE); // frame background white

        // ---------- Header ----------
        JLabel header = new JLabel("AttendanceApp", SwingConstants.CENTER);
        header.setFont(new Font("SansSerif", Font.BOLD, 24));
        header.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        try {
            header.setIcon(new ImageIcon(getClass().getResource("/images/")));
            header.setHorizontalTextPosition(SwingConstants.CENTER);
            header.setVerticalTextPosition(SwingConstants.BOTTOM);
        } catch (Exception ignored) {}
        add(header, BorderLayout.NORTH);

        // ---------- Login Panel ----------
        JPanel loginPanel = new JPanel(new GridBagLayout());
        loginPanel.setBackground(UIManager.getColor("Panel.background")); // original panel color
        loginPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                "Login",
                TitledBorder.CENTER, // centered title
                TitledBorder.DEFAULT_POSITION,
                loginPanel.getFont().deriveFont(Font.BOLD),
                Color.BLACK
        ));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        lastNameField = new JTextField(15);
        teacherIdField = new JPasswordField(15);

        gbc.gridx = 0; gbc.gridy = 0; loginPanel.add(new JLabel("Lastname:"), gbc);
        gbc.gridx = 1; gbc.gridy = 0; loginPanel.add(lastNameField, gbc);
        gbc.gridx = 0; gbc.gridy = 1; loginPanel.add(new JLabel("Teacher ID:"), gbc);
        gbc.gridx = 1; gbc.gridy = 1; loginPanel.add(teacherIdField, gbc);

        JButton loginBtn = new JButton("Login");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;               // span both columns
        gbc.anchor = GridBagConstraints.CENTER;
        loginPanel.add(loginBtn, gbc);

        // ---------- Signup Panel ----------
        JPanel signupPanel = new JPanel(new GridBagLayout());
        signupPanel.setBackground(UIManager.getColor("Panel.background")); // original panel color
        signupPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                "Sign up",
                TitledBorder.CENTER,
                TitledBorder.DEFAULT_POSITION,
                signupPanel.getFont().deriveFont(Font.BOLD),
                Color.BLACK
        ));

        GridBagConstraints sgbc = new GridBagConstraints();
        sgbc.insets = new Insets(8, 8, 8, 8);
        sgbc.fill = GridBagConstraints.HORIZONTAL;

        suFirst = new JTextField(15);
        suLast = new JTextField(15);
        suId = new JTextField(15);
        suSubject = new JTextField(15);

        sgbc.gridx = 0; sgbc.gridy = 0; signupPanel.add(new JLabel("Firstname:"), sgbc);
        sgbc.gridx = 1; sgbc.gridy = 0; signupPanel.add(suFirst, sgbc);
        sgbc.gridx = 0; sgbc.gridy = 1; signupPanel.add(new JLabel("Lastname:"), sgbc);
        sgbc.gridx = 1; sgbc.gridy = 1; signupPanel.add(suLast, sgbc);
        sgbc.gridx = 0; sgbc.gridy = 2; signupPanel.add(new JLabel("Teacher ID:"), sgbc);
        sgbc.gridx = 1; sgbc.gridy = 2; signupPanel.add(suId, sgbc);
        sgbc.gridx = 0; sgbc.gridy = 3; signupPanel.add(new JLabel("Subject:"), sgbc);
        sgbc.gridx = 1; sgbc.gridy = 3; signupPanel.add(suSubject, sgbc);

        JButton signupBtn = new JButton("Create Account");
        sgbc.gridx = 0;
        sgbc.gridy = 4;
        sgbc.gridwidth = 2;               // span both columns
        sgbc.anchor = GridBagConstraints.CENTER;
        signupPanel.add(signupBtn, sgbc);

        // ---------- Center Layout ----------
        JPanel center = new JPanel(new GridLayout(2, 1, 0, 20));
        center.setBorder(BorderFactory.createEmptyBorder(20, 200, 20, 200));
        center.setBackground(Color.WHITE); // frame background
        center.add(loginPanel);
        center.add(signupPanel);

        add(center, BorderLayout.CENTER);

        // ---------- Actions ----------
        loginBtn.addActionListener(e -> doLogin());
        signupBtn.addActionListener(e -> doSignup());
    }

    private void doLogin() {
        String last = lastNameField.getText().trim();
        String id = new String(teacherIdField.getPassword()).trim();

        if (last.isEmpty() || id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Enter lastname and teacherID");
            return;
        }

        boolean ok = client.authenticate(last, id);
        if (ok) {
            SwingUtilities.invokeLater(() -> {
                HomeFrame home = new HomeFrame();
                home.setVisible(true);
            });
            dispose();
        } else {
            int create = JOptionPane.showConfirmDialog(this,
                    "Teacher not found or lastname doesn't match. Create a new account?",
                    "Sign up?", JOptionPane.YES_NO_OPTION);
            if (create == JOptionPane.YES_OPTION) {
                suLast.setText(last);
                suId.setText(id);
                suFirst.requestFocus();
            }
        }
    }

    private void doSignup() {
        String first = suFirst.getText().trim();
        String last = suLast.getText().trim();
        String id = suId.getText().trim();
        String subject = suSubject.getText().trim();

        if (first.isEmpty() || last.isEmpty() || id.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Firstname, lastname and teacherID are required");
            return;
        }

        Teacher t = new Teacher.Builder()
                .setTeacherID(id)
                .setFirstName(first)
                .setLastName(last)
                .setSubject(subject)
                .build();

        try {
            client.create(t);
            JOptionPane.showMessageDialog(this, "Account created. You can now log in.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Failed to create teacher: " + ex.getMessage());
        }
    }
}
