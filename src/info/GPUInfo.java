package src.info;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class GPUInfo {

    public static String getGPUDetailedInfo() {
        StringBuilder gpuInfo = new StringBuilder();
        gpuInfo.append("\n--- Detailed GPU Gaming Information ---\n");

        try {
            // Updated PowerShell command using Get-CimInstance
            ProcessBuilder processBuilder = new ProcessBuilder(
                    "powershell",
                    "$gpus = Get-CimInstance Win32_VideoController; " +
                            "foreach ($gpu in $gpus) { " +
                            "    $vramGB = if ($gpu.AdapterRAM) { [math]::Round($gpu.AdapterRAM / 1GB, 2) } else { 'Unknown' }; " +
                            "    Write-Output ('GPU Name: ' + $gpu.Name); " +
                            "    Write-Output ('Total VRAM: ' + $vramGB + ' GB'); " +
                            "    Write-Output ('Driver Version: ' + $gpu.DriverVersion); " +
                            "}"
            );
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                gpuInfo.append(line.trim()).append("\n");
            }

        } catch (Exception e) {
            gpuInfo.append("Error retrieving GPU information: ").append(e.getMessage()).append("\n");
        }

        return gpuInfo.toString();
    }
}
