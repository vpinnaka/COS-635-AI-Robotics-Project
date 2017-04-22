package android.app.com.emilyrobot;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by vinay on 4/17/17.
 */

public class FragmentMaps extends Fragment implements OnMapReadyCallback {

    static GoogleMap mMap;
    static ArrayList<LatLng> coordinations;
    static ArrayList<LatLng> waypoints;
    static Random rand=new Random();
    public static Handler mHandler;
    static PolylineOptions poption;
    static ArrayList<Polyline> polylines;
    CircleOptions circle;
    public static long started,ended;
    public FragmentMaps()
    {

    }


    public static FragmentMaps newInstance() {
        FragmentMaps fragment = new FragmentMaps();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        coordinations=new ArrayList<>();
        waypoints=new ArrayList<>();
        //coordinations.add(new LatLng(27.710527, -97.318175));
        //coordinations.add(new LatLng(27.708407, -97.319783));
        waypoints.add(new LatLng(27.710527, -97.318175));
        waypoints.add(new LatLng(27.708407, -97.319783));
        mHandler=new Handler();
        polylines=new ArrayList<>();
        poption= new PolylineOptions();
        circle=new CircleOptions();


    }
    public static Runnable mTask =new Runnable() {
        @Override

        public void run() {
            try{
                randomCreateArraylist(coordinations);
                trackingCor(coordinations,poption,mMap);
            }finally {
                if(System.currentTimeMillis()<ended) {
                    mHandler.postDelayed(mTask, 2000);
                }else{
                    mHandler.removeCallbacks(mTask);
                    lineRemove();
                }

            }
        }
    };


    @Override
    public void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacks(mTask);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_maps, container, false);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        return  v;


    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        //mMap.addMarker(new MarkerOptions().position(Mydata.getEmilyLocation()).title("Marker"));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Mydata.getEmilyLocation(),17));
        //drawCircle(waypoints);
        mTask.run();
        //Log.i("Latitude",Double.toString(coordinations.get(coordinations.size()-1).latitude));
        //Log.i("Longitude",Double.toString(coordinations.get(coordinations.size()-1).longitude));

    }
    public void drawCircle(ArrayList<LatLng> cor){
        for(int i=0;i<cor.size();i++){
            mMap.addCircle(circle.center(cor.get(i)).radius(5).strokeColor(Color.RED).fillColor(Color.RED));
        }
    }
    static void trackingCor(ArrayList<LatLng> cor, PolylineOptions line, GoogleMap map){
        if(cor.size()>2) {
            line.add(cor.get(cor.size() - 1));
            line.add(cor.get(cor.size() - 2));
            line.width(5).color(Color.WHITE).geodesic(true);
            //polylines.add(map.addPolyline(line));

            Polyline l=map.addPolyline(line);
            polylines.add(l);

        }
    }
    public static void lineRemove(){
        for(Polyline line:polylines){
            line.remove();
        }
        polylines.clear();
    }
    static void randomCreateArraylist(ArrayList<LatLng> a){

        int size=a.size();
        if(size==0) a.add(new LatLng(27.710527, -97.318175));
        LatLng c;
        double randomNum= 0.001*rand.nextDouble();
        double c1=a.get(a.size()-1).latitude+randomNum;
        double c2=a.get(a.size()-1).longitude+randomNum;
        c=new LatLng(c1,c2);
        a.add(c);
        //Log.i("Lat",Double.toString(a.get(a.size()-1).latitude));
        //Log.i("Longitude",Double.toString(a.get(a.size()-1).longitude));

    }

}
