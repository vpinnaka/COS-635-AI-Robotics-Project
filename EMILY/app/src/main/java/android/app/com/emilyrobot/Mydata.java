package android.app.com.emilyrobot;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

/**
 * Created by vinay on 4/17/17.
 */

public class Mydata {
    static String[] nameArray = {"99%", "100%", "100%", "0.00m/s", "0.00m", "0.00m", "0:00"};



    static Integer[] drawableArray = {R.drawable.battery, R.drawable.signal_strength, R.drawable.gpsreceiving,
            R.drawable.speedometer, R.drawable.home, R.drawable.marker, R.drawable.clock
            };

    static Integer[] id_ = {0, 1, 2, 3, 4, 5, 6};

    static ArrayList<Model> dataModals;
    
    private boolean armed;

    private int wirelessStrength;
    private int gpsStatus;
    private float speed;
    private float distToHome;
    private float distToWaypoint;

    private static LatLng EmilyLocation = new LatLng(27.709767,-97.320152);
    private static LatLng HomeLocation = new LatLng(27.709767,-97.320152);


    public void setArmedStatus(boolean armed){
        this.armed = armed;
    }
    //public static void setBatteryStatus(int batteryStatus){
    //    nameArray[0] = Integer.toString(batteryStatus)+ "%";
    //
    //}

    public void setWirelessStrength(int wirelessStrength){
        this.wirelessStrength = wirelessStrength;
    }

    public void setGpsStatus(int gpsStatus){
        this.gpsStatus = gpsStatus;
    }

    public void setEmilySpeed(float speed){
        this.speed = speed;
    }

    public void setDistToHome(float distToHome){
        this.distToHome = distToHome;
    }

    public void setDistToWaypoint(float distToWaypoint){
        this.distToWaypoint = distToWaypoint;
    }



    public void setEmilyLocation(float Lat, float Lng){
        EmilyLocation = new LatLng(Lat,Lng);

    }

    public void setEmilyHomeLocation(float Lat, float Lng){
        HomeLocation = new LatLng(Lat,Lng);
    }


    public static LatLng getEmilyLocation(){
        return EmilyLocation;
    }

    public static LatLng getEmilyHomeLocation(){
        return HomeLocation;
    }

    public static void setCurrentStatus(String key, Object value){

    }

    public static void setBatteryStatus(int value) {
        dataModals.get(0).name = Integer.toString(value) + "%";

        if (value < 20) {
            dataModals.get(0).image = R.drawable.low_battery;
        } else {
            dataModals.get(0).image = R.drawable.battery;
        }
    }

    public static void setWifiStrength(int value) {
        dataModals.get(1).name = Integer.toString(value) + "%";
    }

    public static void setGPSStrength(int value) {
        dataModals.get(2).name = Integer.toString(value) + "%";
    }

    public static void setSpeed(float value) {
        dataModals.get(3).name = String.format(java.util.Locale.US,"%.2f m/s", value);
    }

    public static void setDistanceFromHome(float value) {
        dataModals.get(4).name = String.format(java.util.Locale.US,"%.2f m", value);
    }

    public static void setDistanceToNextWaypoint(float value) {
        dataModals.get(5).name = String.format(java.util.Locale.US,"%.2f m", value);
    }

    public static void setElapsedTime(String s) {
        dataModals.get(6).name = s;
    }



    public static ArrayList<Model> generateData() {

        dataModals = new ArrayList<>();

        for (int i = 0; i < Mydata.nameArray.length; i++) {

            dataModals.add(new Model(nameArray[i],id_[i],drawableArray[i]));

            String m = nameArray[i];

            System.out.print(m);

        }

        return dataModals;
    }
}
