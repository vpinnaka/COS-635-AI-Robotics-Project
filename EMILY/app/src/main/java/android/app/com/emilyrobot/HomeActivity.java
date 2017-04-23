package android.app.com.emilyrobot;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
    TextView statusMessage;
    boolean is_connected = false;
    boolean is_display_video = false;
    String ip_address = "";
    Handler h;
    //int delay;
    Integer tmp = 10;
    NetworkConnection client;
    //class Evil implements Runnable {
    //    public void run() {
    //        tmp++;
    //        statusMessage.setText(tmp.toString());
    //    }
    //}

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar mtoolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        //getSupportActionBar().setTitle("EMILY");



        statusMessage = (TextView) findViewById(R.id.statusMessage);
        ImageButton connectButton = (ImageButton) findViewById(R.id.connectButton);
        connectButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (!is_connected) {
                    showConnectButtonPopupAlert();
                } else {
                    stopConnection();
                }
            }
        });

        ImageButton cameraButton = (ImageButton) findViewById(R.id.cameraButton);
        cameraButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                ImageView cameraDisplay = (ImageView) findViewById(R.id.cameraDisplay);
                if (is_display_video) {
                    cameraDisplay.setVisibility(View.INVISIBLE);
                    is_display_video = false;
                } else {
                    cameraDisplay.setVisibility(View.VISIBLE);
                    is_display_video = true;
                }

            }
        });


        //ImageButton settingButton = (ImageButton) findViewById(R.id.settingButton);
        //settingButton.setOnClickListener(openSettingsPage());
        //Thread t = new Thread(new Evil());
        //t.setDaemon(true);//success is here now
        //t.start();
        //Thread.sleep(1000);
        h = new Handler();
        //delay = 1000; //milliseconds

        h.postDelayed(new Runnable(){
            public void run(){
                tmp--;
                if (tmp < 0) {
                    tmp = 30;

                    MediaPlayer mPlayer = MediaPlayer.create(HomeActivity.this, R.raw.low_battery);
                    mPlayer.start();
                }
                //statusMessage.setText(tmp.toString());
                //Mydata.setBatteryStatus(tmp);
                //Mydata.setWifiStrength(tmp);
                //Mydata.setGPSStrength(tmp);
                //Mydata.setSpeed(tmp);
                //Mydata.setDistanceFromHome(tmp);
                //Mydata.setDistanceToNextWaypoint(tmp);
                //Mydata.setElapsedTime(Integer.toString(tmp));
                Mydata.setBatteryStatusForDevelopment(tmp);
                //do something
                h.postDelayed(this, Settings.detail_refresh_rate);
            }
        }, Settings.detail_refresh_rate);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // menuInflater=getMenuInflater();
        // menuInflater.inflate(R.menu.action_bar,menu);
        /*TextView tv = new TextView(this);
        tv.setText(getString(R.string.app_name)+"  ");
       // tv.setTextColor(getResources().getColor(R.color.colorAccent);
        //tv.setOnClickListener(t);
        tv.setPadding(5, 0, 5, 0);
        tv.setTypeface(null, Typeface.BOLD);
        tv.setTextSize(14);
        //MenuItem add = menu.add(0, findViewById(R.id.statusTextview), 1, R.string.app_name);//(0,findViewById(R.id.statusTextview),1,R.string.app_name)
        //add.setActionView(tv).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);*/
        return super.onCreateOptionsMenu(menu);
    }

    private void showConnectButtonPopupAlert() {
        AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
        builder.setTitle("IP address");

        // Set up the input
        final EditText input = new EditText(HomeActivity.this);
        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String result = input.getText().toString();
                setupConnection(result);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    private void setupConnection(String ip) {
        ip_address = ip;
        statusMessage.setText(ip);
        is_connected = true;
        ImageButton connectButton = (ImageButton) findViewById(R.id.connectButton);
        connectButton.setBackgroundResource(R.drawable.connected_48);
        client = new NetworkConnection(ip_address,8005);
        client.execute();
    }

    private void stopConnection() {
        is_connected = false;
        statusMessage.setText("Disconnected");
        ImageButton connectButton = (ImageButton) findViewById(R.id.connectButton);
        connectButton.setBackgroundResource(R.drawable.disconnected_48);
    }

    public void openSettingsPage(View view) {
        Intent intent = new Intent(this, SettingActivity.class);
        //EditText editText = (EditText) findViewById(R.id.editText);
        String message = "123";
        //intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
