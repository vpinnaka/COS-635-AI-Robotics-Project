package android.app.com.emilyrobot;

import android.content.DialogInterface;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
    TextView statusMessage;
    boolean is_connected = false;
    String ip_address = "";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar mtoolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mtoolbar);

        getSupportActionBar().setTitle("EMILY");

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

                //statusMessage.setText("OK");
                /*
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
                        ip_address = input.getText().toString();
                        statusMessage.setText(ip_address);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
                */
            }
        });
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
    }

    private void stopConnection() {
        is_connected = false;
        statusMessage.setText("Disconnected");
        ImageButton connectButton = (ImageButton) findViewById(R.id.connectButton);
        connectButton.setBackgroundResource(R.drawable.disconnected_48);
    }
}
