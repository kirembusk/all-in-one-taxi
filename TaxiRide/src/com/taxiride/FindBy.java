package com.taxiride;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FindBy extends LoggingActivity {
	private String toAddress;
	private String fromAddress;
	private static final Intent LOCATION_CHANGED = null;
    private MapController mapController;
    private LocationManager locationManager;
    private LocationListener locationListener;
    public static int TO_LAT;
    public static int TO_LNG;
    public static int FROM_LAT =0;
    public static int FROM_LNG =0;
    private Geocoder geoCoder;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.findby);
        // GET TO AND FROM ADDRESS FROM ADDRESS.JAVA ACTIVITY.
        Bundle bundle = getIntent().getExtras();
         toAddress = bundle.getString("toAddress"); 
         fromAddress = bundle.getString("fromAddress");
         geoCoder = new Geocoder(this, Locale.getDefault());
       //DEBUGGING USE
        //Toast toast = Toast.makeText(getApplicationContext(),"from Address: " + fromAddress + " To address: "+ toAddress, BIND_AUTO_CREATE);
        //toast.show();
        // IF USER DID NOT ENTER ANY FROM ADDRESS THEN FINE NETWORK OR GPS PROVIDER
        if(fromAddress.length()==0){
        	
        	locationManager =(LocationManager) getSystemService(Context.LOCATION_SERVICE);
	        //if network is available then use network provider else use GPS provider
	        if(locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
	       // update location every 5 secs or when user moves 500 meter.
	        	locationManager.requestLocationUpdates(
	        			LocationManager.NETWORK_PROVIDER, 
	        			5000, 
	        			500, 
	        			new NetworkLocationListener());
	        	}else{
	        	locationManager.requestLocationUpdates(
	        	          LocationManager.GPS_PROVIDER, 
	        	          5000, 
	        	          500, 
	        	          new GPSLocationListener());}
	        
	
        
        //CONVERT "FROM ADDRESS" TO LATITUDE AND LONGTITUDE AND ASSIGN TO 
        // THE GLOBAL VARIABLE
        List<Address> addresses;
		try {
			addresses = geoCoder.getFromLocationName(toAddress, 5);
			 if (addresses.size() > 0) {
	               
	               TO_LAT=(int) (addresses.get(0).getLatitude() * 1E6); 
	               TO_LNG=(int) (addresses.get(0).getLongitude() * 1E6);
	           }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
          
          
        Button cabDir = (Button) findViewById(R.id.ButtonCabDir);
        
        cabDir.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View view) {
        	//	Bundle bundle2 = new Bundle();
        	//	bundle2.putString("toAddress", toAddress);
        	//	bundle2.putString("fromAddress", fromAddress);
                  Intent myIntent2 = new Intent(view.getContext(),CabDirectory.class);
                //  myIntent2.putExtras(bundle2);
                  startActivityForResult(myIntent2, 0);
            }
        });
        }     
        
        Button driverDir = (Button) findViewById(R.id.ButtonDriverDir);
        
        driverDir.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View view) {
        		Bundle bundle2 = new Bundle();
        		bundle2.putString("toAddress", toAddress);
        		bundle2.putString("fromAddress", fromAddress);
                  Intent myIntent = new Intent(view.getContext(), MapOfRequests.class);
                  myIntent.putExtras(bundle2);
                  startActivityForResult(myIntent, 0);
            }

        });
	}
 
	public  class NetworkLocationListener implements LocationListener {

        //  @Override
          public void onStatusChanged(String provider, int status, Bundle extras) {
              
          }

       //   @Override
          public void onProviderEnabled(String provider) {
              
          }

       //   @Override
          public void onProviderDisabled(String provider) {
            
          }

          public void onLocationChanged(Location location) {
          	
         	FindBy.FROM_LAT=(int) (location.getLatitude() * 1E6);
  	        FindBy.FROM_LNG= (int) (location.getLongitude() * 1E6);
  	      Toast toast = Toast.makeText(getApplicationContext(), "From Lat: " + FindBy.FROM_LAT + "from current LAT:  " + (int) (location.getLatitude() * 1E6) , BIND_AUTO_CREATE);
	   		toast.show();
  	     
          	
          }
      };;
	
      
 public class GPSLocationListener implements LocationListener 
  	{
  	  public void onLocationChanged(Location location) {
  		 //  updateLocation(location);
  		  	FindBy.FROM_LAT=(int) (location.getLatitude() * 1E6);
	        FindBy.FROM_LNG = (int) (location.getLongitude() * 1E6);
	        Toast toast = Toast.makeText(getApplicationContext(), "From Lat: " + FindBy.FROM_LAT + "from current LAT:  " + (int) (location.getLatitude() * 1E6) , BIND_AUTO_CREATE);
	   		toast.show();
	        
  	   
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
  	
  	}	
	
}
   	
	

	
	

	