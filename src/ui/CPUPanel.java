package src.ui;

import src.info.CPUInfo;

import javax.swing.*;
import java.awt.*;

public class CPUPanel extends JPanel {
    private JTextArea textArea;

    public CPUPanel() {
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        loadCPUInfo();
    }

    private void loadCPUInfo() {
        textArea.setText(""); // Clear text area
        textArea.append(CPUInfo.getCPUDetailedInfo());
    }
}
