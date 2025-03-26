package src.ui;

import src.info.OSInfo;
import javax.swing.*;
import java.awt.*;

public class OSPanel extends JPanel {
    private JTextArea textArea;

    public OSPanel() {
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        loadOSInfo();
    }

    private void loadOSInfo() {
        textArea.setText("");
        textArea.append(OSInfo.getWindowsDetails());
        textArea.append(OSInfo.getSystemTime());
        textArea.append(OSInfo.getOSDetails());
        textArea.append(OSInfo.getSystemLocale());
        textArea.append(OSInfo.getScreenResolution());
    }
}
