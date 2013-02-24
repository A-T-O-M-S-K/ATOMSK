package com.longley.willis.atomsk;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    /* Calls the BluetoothTest activity */
    public void startBluetoothTest(View view) {
    	Intent intent = new Intent(this, BluetoothTestActivity.class);
    	startActivity(intent);
    }
    
    /* Calls the BluetoothTest activity */
    public void startGPSTest(View view) {
    	Intent intent = new Intent(this, GPSTestActivity.class);
    	startActivity(intent);
    }
    
}
