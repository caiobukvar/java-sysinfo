package src.info;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class NetworkInfo {

    public static String getPublicIPAddress() {
        StringBuilder result = new StringBuilder("\n--- Network Details ---\n");
        try {
            URL whatismyip = new URL("http://checkip.amazonaws.com");
            try (BufferedReader in = new BufferedReader(new InputStreamReader(whatismyip.openStream()))) {
                result.append("Public IP Address: ").append(in.readLine()).append("\n");
            }
        } catch (IOException e) {
            result.append("Error retrieving public IP: ").append(e.getMessage()).append("\n");
        }
        return result.toString();
    }

    public static String getLocalIPAddress() {
        StringBuilder result = new StringBuilder("");
        try {
            ProcessBuilder processBuilder;
            if (System.getProperty("os.name").toLowerCase().contains("win")) {
                // Windows: Get local IP from ipconfig
                processBuilder = new ProcessBuilder("cmd.exe", "/c", "ipconfig | findstr IPv4");
            } else {
                // Linux/macOS: Get local IP from hostname -I
                processBuilder = new ProcessBuilder("bash", "-c", "hostname -I");
            }
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            String localIP = "";

            while ((line = reader.readLine()) != null) {
                if (System.getProperty("os.name").toLowerCase().contains("win")) {
                    // Extract IP address from the ipconfig output (Windows format)
                    if (line.contains("IPv4")) {
                        localIP = line.split(":")[1].trim();  // Get the part after the colon
                        break;
                    }
                } else {
                    // Linux/macOS (multiple IPs, just pick the first one)
                    localIP = line.split(" ")[0].trim();
                    break;
                }
            }

            if (localIP.isEmpty()) {
                result.append("Error retrieving local IP.\n");
            } else {
                result.append("Local IP Address: ").append(localIP).append("\n");
            }

        } catch (Exception e) {
            result.append("Error retrieving local IP: ").append(e.getMessage()).append("\n");
        }
        return result.toString();
    }
}
