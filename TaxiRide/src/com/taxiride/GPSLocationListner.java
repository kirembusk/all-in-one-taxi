package com.taxiride;

import java.util.Locale;

import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.widget.Toast;

public class GPSLocationListner implements LocationListener 
	{
	    private int lat;
	    private int log;
	    private double latitude;
	    private double longtitude;
	    
	    public void onLocationChanged(Location location) {
		 //  updateLocation(location);
	    	Address.passengerInfo.setFromLat(lat);
	    	Address.passengerInfo.setFromlog(log);
	    	latitude = (location.getLatitude() * 1E6);
	    	longtitude = (location.getLatitude() * 1E6);
		  	lat= (int) (location.getLatitude() * 1E6);
            log = (int)(location.getLongitude() * 1E6);
           
	    }
	    	public int getLat(){
	    		return lat;
	    	}
	    	public int getlog(){
	    		return log;
	    	}
	    	
	    	public double getLatitude(){return latitude;}
	    	
	    	public double getLongtitude(){return longtitude;}
	
	  public Location locationReturn(Location location){return location;}
	public void onProviderDisabled(String arg0) {
	//	Toast toast3 = Toast.makeText(getApplicationContext(), "GPS disable", BIND_AUTO_CREATE);
	//	toast3.show();
		// TODO Auto-generated method stub
		
	}

	public void onProviderEnabled(String provider) {
		//Toast toast = Toast.makeText(getApplicationContext(), "GPS started", BIND_AUTO_CREATE);
		//toast.show();
		// TODO Auto-generated method stub
		
	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}	
	
	};;	
