package src.ui;

import javax.swing.*;

public class SystemInfoApp extends JFrame {

    public SystemInfoApp() {
        setTitle("SYSINFO - By Caio Bukvar");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("CPU", new CPUPanel());
        tabbedPane.addTab("GPU", new GPUPanel());
        tabbedPane.addTab("RAM", new RAMPanel());
        tabbedPane.addTab("Network", new NetworkPanel());
        tabbedPane.addTab("OS", new OSPanel());

        add(tabbedPane);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SystemInfoApp app = new SystemInfoApp();
            app.setVisible(true);
        });
    }
}