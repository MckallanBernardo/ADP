package za.ac.cput;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import za.ac.cput.client.TeacherClient;
import za.ac.cput.ui.LoginFrame;

import javax.swing.*;

public class AttendanceLauncher {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new SpringApplicationBuilder(Main.class)
                .headless(false) // enable AWT/Swing
                .run(args);
        TeacherClient client = ctx.getBean(TeacherClient.class);
        SwingUtilities.invokeLater(() -> new LoginFrame(client).setVisible(true));
    }
}
