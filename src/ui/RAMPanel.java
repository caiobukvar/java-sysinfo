package src.ui;

import src.info.RAMInfo;

import javax.swing.*;
import java.awt.*;

public class RAMPanel extends JPanel {
    private JTextArea textArea;

    public RAMPanel() {
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        loadRAMInfo();
    }

    private void loadRAMInfo() {
        textArea.setText(""); // Clear text area
        textArea.append(RAMInfo.getRAMDetails());
        textArea.append(RAMInfo.getRAMUsage());
    }
}
