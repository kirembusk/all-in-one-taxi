package com.taxiride;
import android.preference.PreferenceManager;
import android.provider.Settings.Secure;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class DriverWindow extends LoggingActivity {
	private double lat;
	private double log; 
	public static DriverInfo driverInfo;
	private boolean isDone =false; 

	private GPSLocationListner gpsLocation = new GPSLocationListner();
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //select the main.xml for layout
        setContentView(R.layout.driverwindow);
        
        
        driverInfo = new DriverInfo(); 
        SharedPreferences pref =getSharedPreferences("DriverPreference",Context.MODE_PRIVATE);
		String cName = pref.getString("editCompanyName", "");
	    driverInfo.setCabName(cName);
	    String password = pref.getString("editPassword", "");
	    driverInfo.setPin(password);
	    String fName = pref.getString("editFullName", "");
	    driverInfo.setFullName(fName);
	  
	    
	    String deviceID = Secure.getString(getBaseContext().getContentResolver(),
                Secure.ANDROID_ID);
	    driverInfo.setDeviceID(deviceID);
       /* driverInfo.setCabName(DriverPreference.cabName);
        driverInfo.setDeviceID(DriverPreference.deviceID);
        driverInfo.setFullName(DriverPreference.fullName);
        driverInfo.setMaxDropOff(DriverPreference.maxDropOff);
        driverInfo.setMaxPickUp(DriverPreference.maxPickUp);
        driverInfo.setPaymentType(DriverPreference.paymentType);
        driverInfo.setPhoneNum(DriverPreference.phoneNum);
        driverInfo.setPin(DriverPreference.pin);*/
        
      // driverInfo.setFullName(DriverPreference.fullName);
      // Toast toast1= Toast.makeText(getApplicationContext(),DriverPreference.fullName , 100);
       // toast1.show();
        
        
        
             
        
        Button openRequest = (Button) findViewById(R.id.OpenRequest);  
       
        
        openRequest.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	isDone = true;
            	if(isDone==true){
            		driverInfo.setCurrentLatitude(String.valueOf(lat));
            		driverInfo.setCurrentLongitude(String.valueOf(log));
            		
                Intent myIntent = new Intent(view.getContext(), ListOfRequest.class);
                startActivityForResult(myIntent, 0);
            	}else{
            		Toast toast2 = Toast.makeText(getApplicationContext(),"Please Wait--- The application is finding your current location" , 10);
        		    toast2.show();
        		    
            	}
            		
            }

        });
        
        Button myOrder = (Button) findViewById(R.id.MyOrder);  
        //if the RequestDriver button is click direct the page to Address.class
        myOrder.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(),MyOrder.class);
                startActivityForResult(myIntent, 0);
            }
           
        });  
        
        Button editPrefs = (Button) findViewById(R.id.editPreference);
        editPrefs.setOnClickListener(new View.OnClickListener() {
               public void onClick(View view) {
            	   
                   Intent myIntent = new Intent(view.getContext(), DriverPreference.class);
                   startActivityForResult(myIntent, 0);
               }

       });
        
        LocationManager locationManager =(LocationManager) getSystemService(Context.LOCATION_SERVICE);
       
        locationManager.requestLocationUpdates(
  	          LocationManager.GPS_PROVIDER, 
  	          5000, 
  	          500, 
  	         gpsLocation);
        
        	lat = gpsLocation.getLatitude();
        	log = gpsLocation.getLongtitude();
        
	} 
	
	public class GPSLocationListner implements LocationListener 
	{
	    private int lat;
	    private int log;
	    private double latitude;
	    private double longtitude;
	    
	    public void onLocationChanged(Location location) {
		 //  updateLocation(location);
	    	latitude = (location.getLatitude() * 1E6);
	    	longtitude = (location.getLatitude() * 1E6);
		  	lat= (int) (location.getLatitude() * 1E6);
            log = (int)(location.getLongitude() * 1E6);
            
		    isDone = true;		    
           
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
		Toast toast3 = Toast.makeText(getApplicationContext(), "GPS disable", BIND_AUTO_CREATE);
		toast3.show();
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


}
