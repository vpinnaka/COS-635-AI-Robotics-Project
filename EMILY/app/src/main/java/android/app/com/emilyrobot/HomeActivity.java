package android.app.com.emilyrobot;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar mtoolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mtoolbar);

        getSupportActionBar().setTitle("EMILY");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.action_bar,menu);
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
}
