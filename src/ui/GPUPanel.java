package src.ui;

import src.info.GPUInfo;

import javax.swing.*;
import java.awt.*;

public class GPUPanel extends JPanel {
    private JTextArea textArea;

    public GPUPanel() {
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        loadGPUInfo();
    }

    private void loadGPUInfo() {
        textArea.setText("");
        textArea.append(GPUInfo.getGPUDetailedInfo());
    }
}
