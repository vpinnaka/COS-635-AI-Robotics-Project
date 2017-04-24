package android.app.com.emilyrobot;

/**
 * Created by jjjj222 on 4/21/17.
 */

public class Settings {
    static int detail_refresh_rate = 1000;

    static int low_battery_threshold = 20;
    static int low_wifi_threshold = 20;
    static int low_gps_threshold = 20;
    static boolean is_low_battery = false;
    static boolean is_connected = false;
    static boolean is_display_video = false;
    static String ip_address = "192.168.75.1";
    static int port_number = 8000;
    static String status_message = "Disconnected";

    static boolean is_play_low_battery_warning = false;
}
