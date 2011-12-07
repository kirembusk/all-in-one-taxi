package com.taxiride;

import java.io.IOException;

import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Locale;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.provider.Settings.Secure;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


/*
 * This class use GPS to get the current location if the user did not 
 * specifiy a from address in the Address.java class. The address and 
 * coordinate is save into the passenger info class. There is three buttons
 *  in this class a edit prefereces, resever a taxi and cab directoyr. 
 */

public class FindBy extends LoggingActivity {
	public static PassengerInfo passengerInfo = new PassengerInfo();
	private String toAddress;
	private String fromAddress;
	private static final Intent LOCATION_CHANGED = null;
   
    private LocationManager locationManager;
    private LocationListener locationListener;
    private Geocoder geoCoder;
    private boolean isDone = false;
    private boolean isConvertToLocDone = false;
   
    private double[] coordinate = new double[2]; 
    
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        passengerInfo= new PassengerInfo();
        setContentView(R.layout.findby);
        // get passenger device ID and store it in the passengerInfo class
        String deviceID = Secure.getString(getBaseContext().getContentResolver(),
                Secure.ANDROID_ID);
	    passengerInfo.setDeviceID(deviceID);
        // GET TO AND FROM ADDRESS FROM ADDRESS.JAVA ACTIVITY.
         ImageView image = (ImageView) findViewById(R.id.test_image);
         // get to and from address in Address.java class
         toAddress = com.taxiride.Address.ToAddress; 
         fromAddress = com.taxiride.Address.FromAddress; 
         geoCoder = new Geocoder(this, Locale.getDefault());
         
         passengerInfo.setToAddress(toAddress);
        // find current location from GPS if the user specifiy current location
         
 		if(fromAddress.equalsIgnoreCase("Current Location")){
 			passengerInfo.setEnableGPS(true);
 			locationManager =(LocationManager) getSystemService(Context.LOCATION_SERVICE);
	        //if network is available then use network provider else use GPS provider
	        	locationManager.requestLocationUpdates(
	        	          LocationManager.GPS_PROVIDER, 
	        	          5000, 
	        	          500, 
	        	          new GPSLocationListener());
 			
 		}else{
 			// convert from address to coordinates and set it into the passengerinfo class
 			passengerInfo.setFromAddress(fromAddress);
 			convertToCoordinate(fromAddress); 
 			passengerInfo.setFromLat(coordinate[0]);
 			passengerInfo.setFromlog(coordinate[1]);
 		
 			isDone=true;
 		}
 		
 		//convert to address to coordinate and save it in passenger info 
 		convertToCoordinate(toAddress); 
 		passengerInfo.setToLat(coordinate[0]);
 		passengerInfo.setToLog(coordinate[1]); 
 		isConvertToLocDone =true;
 		
 		//get passenger preferences and store it to passengerInfo class
		SharedPreferences prefs =getSharedPreferences("PassengerPreference",Context.MODE_PRIVATE);
		String fName = prefs.getString("editFullName", "");
	    passengerInfo.setfullName(fName);
	    String phoneNum = prefs.getString("editPhoneNum","");
	    passengerInfo.setPhoneNum(phoneNum);
	    String payment = prefs.getString("listPref", "");
	    passengerInfo.setPaymentType(payment);
	    
	   
	  
		
    	   
        Button cabDir = (Button) findViewById(R.id.ButtonCabDir);
        
        cabDir.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View view) {
        		if(isDone==true && isConvertToLocDone == true){
        			setDistance();
        			
                  Intent myIntent2 = new Intent(view.getContext(),CabDirectory.class);
                  startActivityForResult(myIntent2, 0);
        		}
        		else{
        			Toast toast3 = Toast.makeText(getApplicationContext(),"-------- Please wait -----", 10);
       	            toast3.show();
        		}
            }
        });
         
        Button editPreference = (Button) findViewById(R.id.editPreference);
        editPreference.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View view) {
              Intent myIntent2 = new Intent(view.getContext(),PassengerPreference.class);
                  startActivityForResult(myIntent2, 0);
            }
        });
        
        Button driverDir = (Button) findViewById(R.id.ButtonDriverDir);
        
        driverDir.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View view) {
        		if(isDone==true && isConvertToLocDone == true){
        			setDistance();
                  Intent myIntent = new Intent(view.getContext(), DriverRequest.class);
                  startActivityForResult(myIntent, 0);
                  
        		}else{
        			Toast toast3 = Toast.makeText(getApplicationContext(),"Please wait ---- Determinding your current location", 10);
       	            toast3.show();
         	   
        		}
        			
            }

        });
        
        
	}

	// convert addresses to coordinates
	public void convertToCoordinate(String address){
		 List<Address> addresses;
		 
		 	
			try {
				addresses = geoCoder.getFromLocationName(address, 1);
				  
				 if (addresses.size() > 0) {
					coordinate[0] = addresses.get(0).getLatitude();
					coordinate[1] = addresses.get(0).getLongitude(); 
		           }
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
	}
	
	// find distance between from and to address
	 public void setDistance(){
  		 float[] results = {0};
  		 Location distanceBetween = new Location ("point a to b");
 	    distanceBetween.distanceBetween(passengerInfo.getFromLat(), passengerInfo.getFromlog(), passengerInfo.getToLat(), passengerInfo.getToLog(), results);
 	    double result = (float)(results[0]);
	    result = result * 0.000621371192;
	    DecimalFormat newFormat = new DecimalFormat("#.#");
	    double convertMiles = Double.valueOf(newFormat.format(result));
	    passengerInfo.setDistance(convertMiles); 
 	    
  		  
  	  }
 
 // Find latitude and longtitude and set it into the passengerinfo class.
 public class GPSLocationListener implements LocationListener 
  	{
  	  public void onLocationChanged(Location location) {
  		
  		  double lat = location.getLatitude();
        	double log = location.getLongitude();
        	
        	passengerInfo.setFromLat(location.getLatitude());
        	passengerInfo.setFromlog(location.getLongitude());
        	
        	
	        String addressString="";
	        Geocoder gc = new Geocoder(getApplicationContext(),Locale.getDefault());
	        StringBuilder sb =new StringBuilder();
	    	try {
	    		
	    	      List<Address> address = gc.getFromLocation(lat, log, 1);
	    	      
			        
	    	     if (address.size() > 0) {
	    	        Address addr = address.get(0);
	    	        sb.append(addr.getAddressLine(0));
	    	        sb.append(", "); 
	    	        sb.append(addr.getLocality());
	    	        sb.append(", ");
	    	        sb.append(addr.getAdminArea());
	    	      }
	    	    } catch (IOException e) {}
	    	    addressString = sb.toString();
	    	    passengerInfo.setFromAddress(addressString);
	    	//    Toast toast3 = Toast.makeText(getApplicationContext(),"from Address: " +addressString, BIND_AUTO_CREATE);
	         //   toast3.show();
	    	    
	    	   
	    	    isDone=true;
  	  }
  	  
  	 
  	  
  	
  	  public Location locationReturn(Location location){return location;}
  	  
  	public void onProviderDisabled(String arg0) {
  		Toast toast3 = Toast.makeText(getApplicationContext(), "GPS disable", BIND_AUTO_CREATE);
  		toast3.show();
  		// TODO Auto-generated method stub
  		
  	}

  	public void onProviderEnabled(String provider) {
  		Toast toast = Toast.makeText(getApplicationContext(), "GPS started", BIND_AUTO_CREATE);
  		toast.show();
  		// TODO Auto-generated method stub
  		
  	}

  	public void onStatusChanged(String provider, int status, Bundle extras) {
  		// TODO Auto-generated method stub
  		
  	}	
  	
  	};;	
	
}
   	
	

	
	

	