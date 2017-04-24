package android.app.com.emilyrobot;

import com.google.android.gms.maps.model.LatLng;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by vinay on 4/17/17.
 */

public class Mydata {
    static String[] nameArray = {"100%", "100%", "100%", "0.00m/s", "0.00m", "0.00m", "0:00"};



    static Integer[] drawableArray = {R.drawable.battery, R.drawable.signal_strength, R.drawable.gpsreceiving,
            R.drawable.speedometer, R.drawable.home, R.drawable.marker, R.drawable.clock
            };

    static Integer[] id_ = {0, 1, 2, 3, 4, 5, 6};

    static ArrayList<Model> dataModals;
    
    private static boolean armed;
    private static LatLng EmilyLocation = new LatLng(27.709767,-97.320152);
    private static LatLng HomeLocation = new LatLng(27.709767,-97.320152);
    private static float[] mag_values = new float[3];

    private static void setArmedStatus(boolean value){
        armed = value;
    }


    public static void setEmilyLocation(float Lat, float Lng){
        EmilyLocation = new LatLng(Lat,Lng);

    }

    public static void setEmilyHomeLocation(float Lat, float Lng){
        HomeLocation = new LatLng(Lat,Lng);
    }

    private static void setCompassValues(float mx,float my,float mz){
        mag_values[0] = mx;
        mag_values[1] = my;
        mag_values[2] = mz;
    }

    public static float[] getCompassValues(){
        return mag_values;
    }

    public static boolean getArmedStatus(){
        return armed;
    }

    public static LatLng getEmilyLocation(){
        return EmilyLocation;
    }

    public static LatLng getEmilyHomeLocation(){
        return HomeLocation;
    }

    public static void setCurrentStatus(String key, Object value){

        if(key.equals("armed"))
            setArmedStatus(Boolean.valueOf((boolean)value));
        if(key.equals("battary"))
            setBatteryStatus(Double.valueOf((double)value).intValue());
        if(key.equals("wireless")){
            //Log.i("Wireless", value.toString());
            setWifiStrength(Double.valueOf((double)value).intValue());
        }
        if(key.equals("gps_status")){
            //Log.i("gps_status", value.toString());
            setGPSStrength(Double.valueOf((double)value).intValue());
        }
        if(key.equals("emily_speed")){
            //Log.i("emily_speed", value.toString());
            setSpeed(Double.valueOf((double)value).floatValue());
        }if(key.equals("dist2home")){
            //Log.i("dist2home", value.toString());
            setDistanceFromHome(Double.valueOf((double)value).floatValue());
        }
        if(key.equals("dist2waypoint")){
            //Log.i("dist2waypoint", value.toString());
            setDistanceToNextWaypoint(Double.valueOf((double)value).floatValue());
        }
        if(key.equals("emily_location")){
            //Log.i("dist2waypoint", value.toString());
            try {
                JSONObject location = (JSONObject) value;
                float Lat = Double.valueOf((double)location.get("lat")).floatValue();
                float Lng = Double.valueOf((double)location.get("lng")).floatValue();
                setEmilyLocation(Lat,Lng);
            }catch (JSONException e) {
                e.printStackTrace();
            }

        }
        if(key.equals("emily_home_location")){
            //Log.i("dist2waypoint", value.toString());
            try {
                JSONObject location = (JSONObject) value;
                float Lat = Double.valueOf((double)location.get("lat")).floatValue();
                float Lng = Double.valueOf((double)location.get("lng")).floatValue();
                //Log.i("dist2waypoint", Lat+" ,"+Lng);
                setEmilyHomeLocation(Lat,Lng);
            }catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if(key.equals("magnetic_compass")){
            //Log.i("dist2waypoint", value.toString());
            try {
                JSONObject location = (JSONObject) value;
                float mx = Double.valueOf((double)location.get("mx")).floatValue();
                float my = Double.valueOf((double)location.get("my")).floatValue();
                float mz = Double.valueOf((double)location.get("mz")).floatValue();
                //Log.i("dist2waypoint", Lat+" ,"+Lng);
                setCompassValues(mx,my,mz);
            }catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    private static void setBatteryStatus(int value) {
        dataModals.get(0).name = Integer.toString(value) + "%";
		  Model m = dataModals.get(0);
            m.name = Integer.toString(value) + "%";

            if (value < Settings.low_battery_threshold) {
                if (Settings.is_low_battery == false) {
                    Settings.is_play_low_battery_warning = true;
                }
                Settings.is_low_battery = true;
                m.image = R.drawable.low_battery;
            } else {
                Settings.is_low_battery = false;
                m.image = R.drawable.battery;
            }
    }

    private static void setWifiStrength(int value) {
        dataModals.get(1).name = Integer.toString(value) + "%";
    }

    private static void setGPSStrength(int value) {
        dataModals.get(2).name = Integer.toString(value) + "%";
    }

    private static void setSpeed(float value) {
        dataModals.get(3).name = String.format(java.util.Locale.US,"%.2f m/s", value);
    }

    private static void setDistanceFromHome(float value) {
        dataModals.get(4).name = String.format(java.util.Locale.US,"%.2f m", value);
    }

    private static void setDistanceToNextWaypoint(float value) {
        dataModals.get(5).name = String.format(java.util.Locale.US,"%.2f m", value);
    }

    private static void setElapsedTime(String s) {
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
