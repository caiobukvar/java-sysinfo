package src.info;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class OSInfo {

    public static String getSystemTime() {
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return "Current Time: " + currentTime.format(formatter) +
                "\nTime Zone: " + ZoneId.systemDefault();
    }

    public static String getScreenResolution() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        int width = toolkit.getScreenSize().width;
        int height = toolkit.getScreenSize().height;
        return "Screen Resolution: " + width + " x " + height;
    }

    public static String getSystemLocale() {
        Locale locale = Locale.getDefault();
        return "Language: " + locale.getDisplayLanguage() +
                "\nLanguage Code: " + locale.getLanguage() +
                "\nCountry: " + locale.getDisplayCountry() +
                "\nLocale: " + locale;
    }

    public static String getOSDetails() {
        return "\nOperating System: " + System.getProperty("os.name") +
                "\nOS Version: " + System.getProperty("os.version") +
                "\nArchitecture: " + System.getProperty("os.arch") +
                "\nUser Language: " + System.getProperty("user.language") +
                "\nUser Country: " + System.getProperty("user.country");
    }

    public static String getWindowsDetails() {
        if (!System.getProperty("os.name").toLowerCase().contains("win")) {
            return "Windows details unavailable on non-Windows systems.";
        }

        StringBuilder result = new StringBuilder("\n--- Windows System Details ---\n");
        try {
            ProcessBuilder processBuilder = new ProcessBuilder(
                    "powershell",
                    "$OSInfo = Get-CimInstance Win32_OperatingSystem; " +
                            "Write-Host \"Windows Edition: $($OSInfo.Caption)\"; " +
                            "Write-Host \"System Type: $($OSInfo.OSArchitecture)\"; " +
                            "Write-Host \"Registered Owner: $($OSInfo.RegisteredUser)\""
            );
            processBuilder.redirectErrorStream(true);
            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line.trim()).append("\n");
            }
        } catch (Exception e) {
            return "Error retrieving Windows details: " + e.getMessage();
        }
        return result.toString();
    }
}
