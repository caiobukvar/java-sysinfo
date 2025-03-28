package src.ui;

import javax.swing.*;
import java.awt.*;

public class InfoPanel extends JPanel {
    private JTextArea textArea;

    public InfoPanel() {
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        loadInfo();
    }

    private void loadInfo() {
        textArea.setText("");
        textArea.setText("Developed with Java, by Caio Bukvar");
    }
}
