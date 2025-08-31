package za.ac.cput.ui;

import javax.swing.*;
import java.awt.*;

public class HomeFrame extends JFrame {

    public HomeFrame() {
        setTitle("AttendanceApp - Home");
        setSize(900, 560);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Nav bar (menu bar)
        JMenuBar menuBar = new JMenuBar();

        JMenu home = new JMenu("Home");

        JMenuItem manageClassrooms = new JMenuItem("Manage Classrooms");

        JMenu manageStudents = new JMenu("Manage Students");
        manageStudents.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuSelected(javax.swing.event.MenuEvent e) {
                SwingUtilities.invokeLater(() -> new ManageStudentFrame().setVisible(true));
            }
            public void menuDeselected(javax.swing.event.MenuEvent e) {}
            public void menuCanceled(javax.swing.event.MenuEvent e) {}
        });
        JMenu manageTeachers = new JMenu("Manage Teachers");
        manageTeachers.addMenuListener(new javax.swing.event.MenuListener() {
            public void menuSelected(javax.swing.event.MenuEvent e) {
                SwingUtilities.invokeLater(() -> new ManageTeacherFrame().setVisible(true));
            }
            public void menuDeselected(javax.swing.event.MenuEvent e) {}
            public void menuCanceled(javax.swing.event.MenuEvent e) {}
        });

        JMenuItem logout = new JMenuItem("Logout");

        menuBar.add(home);
        menuBar.add(manageClassrooms);
        menuBar.add(manageStudents);
        menuBar.add(manageTeachers);
        menuBar.add(logout);

        setJMenuBar(menuBar);

        JPanel content = new JPanel(new BorderLayout());
        JPanel center = new JPanel();
        center.setLayout(new GridLayout(1, 2, 20, 20));

        JButton takeAttendance = new JButton("Take Attendance");
        JButton reports = new JButton("Reports & Records");
        takeAttendance.addActionListener(e -> SwingUtilities.invokeLater(() -> new TakeAttendanceFrame().setVisible(true)));
        reports.addActionListener(e -> SwingUtilities.invokeLater(() -> new ReportsFrame().setVisible(true)));
        takeAttendance.setFont(takeAttendance.getFont().deriveFont(Font.BOLD, 18f));
        reports.setFont(reports.getFont().deriveFont(Font.BOLD, 18f));
        center.add(takeAttendance);
        center.add(reports);

        JLabel bg = new JLabel();
        bg.setHorizontalAlignment(SwingConstants.CENTER);
        try {
            bg.setIcon(new ImageIcon(getClass().getResource("/images/home_mock.png")));
        } catch (Exception ignored) {}

        content.add(new JLabel("  "), BorderLayout.NORTH);
        content.add(center, BorderLayout.CENTER);
        add(content);

        // ===== ACTIONS =====

        manageClassrooms.addActionListener(e -> {
            SwingUtilities.invokeLater(() -> {
                ManageClassroomFrame frame = new ManageClassroomFrame();
                frame.setVisible(true);
            });
        });

        // Logout
        logout.addActionListener(e -> {
            SwingUtilities.invokeLater(() -> {
                new LoginFrame(new za.ac.cput.client.TeacherClient()).setVisible(true);
            });
            dispose();
        });
    }
}
