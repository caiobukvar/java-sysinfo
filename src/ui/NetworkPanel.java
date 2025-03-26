package src.ui;

import src.info.NetworkInfo;

import javax.swing.*;
import java.awt.*;

public class NetworkPanel extends JPanel {
    private JTextArea textArea;

    public NetworkPanel() {
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(textArea);
        add(scrollPane, BorderLayout.CENTER);

        loadNetworkInfo();
    }

    private void loadNetworkInfo() {
        textArea.setText("");
        textArea.append(NetworkInfo.getPublicIPAddress());
        textArea.append(NetworkInfo.getLocalIPAddress());
    }
}
