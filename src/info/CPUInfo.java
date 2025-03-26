package src.info;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CPUInfo {

    public static String getCPUDetailedInfo() {
        StringBuilder cpuInfo = new StringBuilder();
        cpuInfo.append("\n--- Detailed CPU Gaming Information ---\n");

        try {
            // Processor details via PowerShell (Updated to Get-CimInstance)
            ProcessBuilder processBuilder = new ProcessBuilder(
                    "powershell",
                    "$cpu = Get-CimInstance Win32_Processor; " +
                            "Write-Output ('CPU Name: ' + $cpu.Name); " +
                            "Write-Output ('Cores: ' + $cpu.NumberOfCores); " +
                            "Write-Output ('Logical Processors: ' + $cpu.NumberOfLogicalProcessors); " +
                            "Write-Output ('Max Clock Speed (MHz): ' + $cpu.MaxClockSpeed); " +
                            "Write-Output ('L2 Cache Size (KB): ' + $cpu.L2CacheSize); " +
                            "Write-Output ('L3 Cache Size (KB): ' + $cpu.L3CacheSize);"
            );
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                cpuInfo.append(line.trim()).append("\n");
            }

            // Additional runtime-based CPU information
            int physicalCores = Runtime.getRuntime().availableProcessors();
            cpuInfo.append("Available CPU Cores: ").append(physicalCores).append("\n");

            // Attempt to get CPU name from environment variables
            String cpuName = System.getenv().getOrDefault("PROCESSOR_IDENTIFIER", "Unknown");
            cpuInfo.append("Processor Identifier: ").append(cpuName).append("\n");

        } catch (Exception e) {
            cpuInfo.append("Error retrieving CPU information: ").append(e.getMessage()).append("\n");
        }

        return cpuInfo.toString();
    }
}
