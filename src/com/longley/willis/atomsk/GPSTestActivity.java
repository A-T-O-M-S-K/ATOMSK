package com.longley.willis.atomsk;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class GPSTestActivity extends Activity {

	private LocationManager lManager;
	private LocationProvider lProvider;
	private Location currLoc;
	private TextView tView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gpstest);
		
		/* Get a LocationManager reference */
		lManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
		
		/* Get a LocationProvider GPS reference */
		lProvider = lManager.getProvider(LocationManager.GPS_PROVIDER);
		
		/* Get the current location */
		currLoc = lManager.getLastKnownLocation(lProvider.getName());
		
		/* Get the current TextView of this activity */
		tView = (TextView) findViewById(R.id.gpstest_text);
		
		/* Create String with the location data */
		String currLocStr = "";
		try{
		currLocStr = "Latitude: " + currLoc.getLatitude() + "\nLongitude: "
							+ currLoc.getLongitude();
		} catch(Exception e)
		{
			String something = lProvider.getName();
			if(currLoc == null)
			{
				currLocStr = " currLoc: null \n Current Provider: " + lProvider.getName() + "\nLatitude: unable to fetch \nLongitude: unable to fetch \n Available Providers:";
				List<String> availableProviders = lManager.getAllProviders();
				for(String provider : availableProviders)
				{
					currLocStr += "\n\t " + provider;
				}
			}
			else
			{
				currLocStr = " Latitude: No Data Available \nLongitude: No Data Available";
		
			}
		}
		/* Set text */
		tView.setText(currLocStr);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_gpstest, menu);
		return true;
	}

}
