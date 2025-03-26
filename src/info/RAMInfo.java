package src.info;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

public class RAMInfo {

    public static String getRAMDetails() {
        if (!System.getProperty("os.name").toLowerCase().contains("win")) {
            return "RAM details unavailable on non-Windows systems.";
        }

        StringBuilder result = new StringBuilder("\n--- RAM Details ---");
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(
                    "powershell",
                    "Get-WmiObject Win32_PhysicalMemory | " +
                            "Select-Object Manufacturer, PartNumber, Speed, FormFactor, MemoryType"
            );
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line.trim()).append("\n");
            }
        } catch (Exception e) {
            return "Error retrieving RAM details: " + e.getMessage();
        }
        return result.toString();
    }

    public static String getRAMUsage() {
        StringBuilder result = new StringBuilder("\n--- RAM Usage ---\n");
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(
                    "powershell",
                    "Get-CimInstance Win32_OperatingSystem | " +
                            "Select-Object TotalVisibleMemorySize, FreePhysicalMemory"
            );
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            long totalMemory = 0, freeMemory = 0;

            while ((line = reader.readLine()) != null) {
                if (line.contains("TotalVisibleMemorySize")) {
                    totalMemory = Long.parseLong(line.replaceAll("\\D+", ""));
                }
                if (line.contains("FreePhysicalMemory")) {
                    freeMemory = Long.parseLong(line.replaceAll("\\D+", ""));
                }
            }

            long usedMemory = totalMemory - freeMemory;
            DecimalFormat df = new DecimalFormat("#.##");

            result.append("Total RAM: ").append(df.format(totalMemory / 1024.0 / 1024.0)).append(" GB\n");
            result.append("Free RAM: ").append(df.format(freeMemory / 1024.0 / 1024.0)).append(" GB\n");
            result.append("Used RAM: ").append(df.format(usedMemory / 1024.0 / 1024.0)).append(" GB\n");

        } catch (Exception e) {
            return "Error retrieving RAM usage: " + e.getMessage();
        }
        return result.toString();
    }
}
