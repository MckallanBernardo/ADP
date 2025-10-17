package za.ac.cput.ui;

import za.ac.cput.client.TeacherClient;
import za.ac.cput.domain.Teacher;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
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

        // === Main content panel with overlay layout ===
        JPanel content = new JPanel();
        content.setLayout(new OverlayLayout(content));
        setContentPane(content);

        // === Background image label ===
        JLabel bg = new JLabel();
        bg.setAlignmentX(0.5f);
        bg.setAlignmentY(0.5f);
        try {
            bg.setIcon(new ImageIcon(getClass().getResource("/images/wallhaven-0wlqeq.jpg")));
        } catch (Exception ignored) {}

        // === Transparent container panel for login/signup ===
        JPanel container = new JPanel(new BorderLayout(10, 10));
        container.setOpaque(false); // transparent to show background

        // ---------- Header ----------
        JLabel header = new JLabel("AttendanceApp", SwingConstants.CENTER);
        header.setFont(new Font("SansSerif", Font.BOLD, 24));
        header.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));
        try {
            header.setIcon(new ImageIcon(getClass().getResource("/images/")));
            header.setHorizontalTextPosition(SwingConstants.CENTER);
            header.setVerticalTextPosition(SwingConstants.BOTTOM);
        } catch (Exception ignored) {}
        container.add(header, BorderLayout.NORTH);

        // ---------- Login Panel ----------
        JPanel loginPanel = new JPanel(new GridBagLayout());
        loginPanel.setBackground(new Color(255, 255, 255, 200)); // slightly transparent
        loginPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                "Login",
                TitledBorder.CENTER,
                TitledBorder.DEFAULT_POSITION,
                loginPanel.getFont().deriveFont(Font.BOLD),
                Color.BLACK
        ));

        // Add padding inside login panel
        loginPanel.setBorder(BorderFactory.createCompoundBorder(
                loginPanel.getBorder(),
                new EmptyBorder(30, 30, 30, 30)
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
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        loginPanel.add(loginBtn, gbc);

        // ---------- Signup Panel ----------
        JPanel signupPanel = new JPanel(new GridBagLayout());
        signupPanel.setBackground(new Color(255, 255, 255, 200)); // slightly transparent
        signupPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                "Sign up",
                TitledBorder.CENTER,
                TitledBorder.DEFAULT_POSITION,
                signupPanel.getFont().deriveFont(Font.BOLD),
                Color.BLACK
        ));

        // Add padding inside signup panel
        signupPanel.setBorder(BorderFactory.createCompoundBorder(
                signupPanel.getBorder(),
                new EmptyBorder(30, 30, 30, 30)
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
        sgbc.gridwidth = 2;
        sgbc.anchor = GridBagConstraints.CENTER;
        signupPanel.add(signupBtn, sgbc);

        // ---------- Center Layout with fixed width ----------
        JPanel center = new JPanel();
        center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
        center.setOpaque(false);
        center.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        // Wrap login panel
        JPanel loginWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        loginWrapper.setOpaque(false);
        loginPanel.setMaximumSize(new Dimension(400, loginPanel.getPreferredSize().height));
        loginWrapper.add(loginPanel);

        // Wrap signup panel
        JPanel signupWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER));
        signupWrapper.setOpaque(false);
        signupPanel.setMaximumSize(new Dimension(400, signupPanel.getPreferredSize().height));
        signupWrapper.add(signupPanel);

        center.add(loginWrapper);
        center.add(Box.createVerticalStrut(20));
        center.add(signupWrapper);

        container.add(center, BorderLayout.CENTER);

        // Add container and background to overlay
        content.add(container); // top layer: forms
        content.add(bg);        // bottom layer: background

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
