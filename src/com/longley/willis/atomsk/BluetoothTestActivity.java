package com.longley.willis.atomsk;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class BluetoothTestActivity extends Activity {
	
	private BluetoothAdapter bAdapter;
	private TextView tView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bluetooth_test);
		
		/* Get the textview */
		tView = (TextView) findViewById(R.id.bluetoothtest_text);
		
		/* Get the BlueTooth Adapter */
		bAdapter = BluetoothAdapter.getDefaultAdapter();
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_bluetooth_test, menu);
		return true;
	}

}
