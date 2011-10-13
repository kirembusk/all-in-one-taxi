package com.helloandroid.tutorial.settings;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class StartScreen extends Activity implements LocationListener {
	private int gps=0;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    @Override	
    public void onResume() { 
        super.onResume();
        if(gps<2){
	        try {
				// get location manager to check gps availability
	        	LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
				locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000L,1.0f, this);
			
				boolean isGPS = locationManager.isProviderEnabled (LocationManager.GPS_PROVIDER); 
	
				if(!isGPS){
					gps++;
					if(gps<2) checkGps();
					else finish();
				}
				else{
					gps=2;				
					//gps is available, do actions here		    			        
				}				
			
		 	} catch (Exception e1) {
		 		gps++;
		 		if(gps<2) checkGps();
		 		else finish();
			}
        }
    }
    
    public void checkGps(){
		final Dialog dialog=new Dialog(this);
    	dialog.setContentView(R.layout.gps);
    	dialog.setTitle("GPS");
    	dialog.setCancelable(false);
    	dialog.show();
    	
    	Button gpsSettings = (Button) dialog.findViewById(R.id.gps_sett_button);
    	gpsSettings.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dialog.dismiss();
				startActivityForResult(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS), 0);
			}
		});
		
    	Button gpsCancel = (Button) dialog.findViewById(R.id.gps_cancel_button);
    	gpsCancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				dialog.dismiss();
				finish();
			}
		});
	}

	@Override
	public void onLocationChanged(Location arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
}