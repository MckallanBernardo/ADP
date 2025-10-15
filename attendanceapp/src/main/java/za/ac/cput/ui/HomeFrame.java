package za.ac.cput.ui;

import javax.swing.*;
import java.awt.*;

public class HomeFrame extends JFrame {

    public HomeFrame() {
        setTitle("AttendanceApp - Home");
        setSize(900, 560);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(0, 0));

        // === TOP PANEL with Logout button ===
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        JLabel titleLabel = new JLabel("AttendanceApp");
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 20f));

        JButton logoutBtn = new JButton("Logout");
        logoutBtn.setFocusPainted(false);
        logoutBtn.addActionListener(e -> {
            SwingUtilities.invokeLater(() -> new LoginFrame(new za.ac.cput.client.TeacherClient()).setVisible(true));
            dispose();
        });

        topPanel.add(titleLabel, BorderLayout.WEST);
        topPanel.add(logoutBtn, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);

        // === CREATE TABBED PANE ===
        JTabbedPane tabs = new JTabbedPane();

        // Create content panels
        JPanel homePanel = createHomePanel();
        ManageClassroomPanel manageClassroomPanel = new ManageClassroomPanel();
        ManageStudentPanel manageStudentPanel = new ManageStudentPanel();
        ManageTeacherPanel manageTeacherPanel = new ManageTeacherPanel();

        // Add tabs
        tabs.addTab("Home", homePanel);
        tabs.addTab("Manage Classrooms", manageClassroomPanel);
        tabs.addTab("Manage Students", manageStudentPanel);
        tabs.addTab("Manage Teachers", manageTeacherPanel);

        add(tabs, BorderLayout.CENTER);
    }

    // === HOME TAB CONTENT WITH BACKGROUND IMAGE AND CENTERED BUTTONS ===
    private JPanel createHomePanel() {
        // Main panel with overlay layout to stack background and buttons
        JPanel content = new JPanel();
        content.setLayout(new OverlayLayout(content));

        // === Background image label ===
        JLabel bg = new JLabel();
        bg.setAlignmentX(0.5f);
        bg.setAlignmentY(0.5f);
        try {
            bg.setIcon(new ImageIcon(getClass().getResource("/images/wallhaven-0wlqeq.jpg")));
        } catch (Exception ignored) {}

        // === Buttons panel ===
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 50, 50));
        buttonPanel.setOpaque(false); // transparent to show background

        JButton takeAttendance = new JButton("Take Attendance");
        JButton reports = new JButton("Reports & Records");

        // === BUTTON STYLING ===
        Font btnFont = new Font("SansSerif", Font.BOLD, 20);
        takeAttendance.setFont(btnFont);
        reports.setFont(btnFont);

        takeAttendance.setBackground(new Color(52, 152, 219)); // Blue
        takeAttendance.setForeground(Color.WHITE);
        reports.setBackground(new Color(46, 204, 113)); // Green
        reports.setForeground(Color.WHITE);

        takeAttendance.setFocusPainted(false);
        reports.setFocusPainted(false);

        takeAttendance.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        reports.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        // Hover effects
        takeAttendance.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                takeAttendance.setBackground(new Color(41, 128, 185));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                takeAttendance.setBackground(new Color(52, 152, 219));
            }
        });
        reports.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                reports.setBackground(new Color(39, 174, 96));
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                reports.setBackground(new Color(46, 204, 113));
            }
        });

        // Button actions
        takeAttendance.addActionListener(e ->
                SwingUtilities.invokeLater(() -> new TakeAttendanceFrame().setVisible(true))
        );
        reports.addActionListener(e ->
                SwingUtilities.invokeLater(() -> new ReportsFrame().setVisible(true))
        );

        buttonPanel.add(takeAttendance);
        buttonPanel.add(reports);

        // === Wrapper to vertically center buttons ===
        JPanel buttonWrapper = new JPanel(new GridBagLayout());
        buttonWrapper.setOpaque(false);
        buttonWrapper.add(buttonPanel, new GridBagConstraints());

        // Align wrapper in overlay panel
        buttonWrapper.setAlignmentX(0.5f);
        buttonWrapper.setAlignmentY(0.5f);

        // === Add to overlay panel ===
        content.add(buttonWrapper); // top layer
        content.add(bg);             // bottom layer

        return content;
    }

    // === MAIN ENTRY POINT ===
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HomeFrame().setVisible(true));
    }
}
