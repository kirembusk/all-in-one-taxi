package com.grace.googleMap;
import com.google.android.maps.MapController;

import java.util.List;
import java.util.Locale;
import java.io.IOException;
import java.lang.Object; 
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager; 
import android.location.LocationProvider;



public class Map extends MapActivity {
	
    private static final Intent LOCATION_CHANGED = null;
    private MapController mapController;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private MapView mapView; 
    private Geocoder geoCoder;
    private Bundle bundle; 
    private String toAddress;
    private String fromAddress;
   
	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
		
        super.onCreate(savedInstanceState);
        
        bundle = getIntent().getExtras();
        toAddress = bundle.getString("toAddress"); 
        fromAddress = bundle.getString("fromAddress");
        
       
        // show the google map
        setContentView(R.layout.map);
        RelativeLayout linearLayout = (RelativeLayout)findViewById(R.id.mainlayout); 
        mapView = (MapView) findViewById(R.id.mapview); 
        
        
        //enable traffic on map
          mapView.setTraffic(true);
        //  mapView.setSatellite(true);
       //   mapView.setStreetView(false);
        // set zoom to 16
        mapView.setBuiltInZoomControls(true); 
        mapController = mapView.getController();
        
        
        geoCoder = new Geocoder(this, Locale.getDefault()); 
        
        
        locationManager =(LocationManager) getSystemService(Context.LOCATION_SERVICE);
        //if network is available then use network provider else use GPS provider
        if(locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
        //update location every 5 secs or when user moves 500 meter.
        	locationManager.requestLocationUpdates(
        			LocationManager.NETWORK_PROVIDER, 
        			5000, 
        			500, 
        			new NetworkLocationListner());
        	}else{
        	locationManager.requestLocationUpdates(
        	          LocationManager.GPS_PROVIDER, 
        	          5000, 
        	          500, 
        	          new GPSLocationListener());	
			}
        
		  
	}

        @Override
    	protected boolean isRouteDisplayed() {
    		// TODO Auto-generated method stub
    		return false;
    	}
        
        
       public void updateLocation(Location location){
    	   if (location != null) {
   	    	Drawable marker=getResources().getDrawable(R.drawable.cmarker);
   	    //	Drawable marker=getResources().getDrawable(android.R.drawable.star_big_on);
   	      //  int markerWidth = marker.getIntrinsicWidth();
   	      //  int markerHeight = marker.getIntrinsicHeight();
   	      //  marker.setBounds(0, markerHeight, markerWidth, 0);
   	    	
   	        OverLay overLay = new OverLay(marker);
   	        
   	        mapView.getOverlays().add(overLay);
   	        int currentLat = (int) (location.getLatitude() * 1E6);
   	        int currentLog = (int) (location.getLongitude() * 1E6);
   	        
   	       GeoPoint point = new GeoPoint(currentLat,currentLog);
   	       overLay.addItem(point, "myPoint1", "myPoint1");
   	     
   	       GeoPoint point2; 
   	        try {
   	        	Toast toast = Toast.makeText(getApplicationContext(),"from Address: " + fromAddress + " To address: "+ toAddress, BIND_AUTO_CREATE);
   	         toast.show();
   	        	
   	            List<Address> addresses = geoCoder.getFromLocationName( 
   	            		toAddress, 5);
   	           // String add = "";
   	            
   	            if (addresses.size() > 0) {
   	                point2 = new GeoPoint(
   	                        (int) (addresses.get(0).getLatitude() * 1E6), 
   	                        (int) (addresses.get(0).getLongitude() * 1E6));
   	                overLay.addItem(point2, "myPoint2", "myPoint2");
   	                
   	                mapController.animateTo(point);
   	              //  map.animateTo(p);    
   	           //     mapView.invalidate();
   	            }    
   	        } catch (IOException e) {
   	            e.printStackTrace();
   	        }
   	    
   	       //  mapController.setCenter(point); 
   	         //mapController.animateTo(point2);
   	        // mapController.setZoom(13); 
   	      // mapController.zoomToSpan(overLay.getLatSpanE6(), overLay.getLonSpanE6());
   		       mapView.invalidate();
            
   	      
   	    }
             
       }
    	   
    	   
       

      public class NetworkLocationListner implements LocationListener {

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
            	
            	updateLocation(location);
            	
            }
        };;
        

	public class GPSLocationListener implements LocationListener 
	{
	  public void onLocationChanged(Location location) {
		  updateLocation(location);
	   
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
		switch (status){
		case LocationProvider.AVAILABLE:
			Toast toast = Toast.makeText(getApplicationContext(), "available", BIND_AUTO_CREATE);
	        toast.show();
	        break;
		case LocationProvider.OUT_OF_SERVICE:
				Toast toast1 = Toast.makeText(getApplicationContext(), "unavilable", BIND_AUTO_CREATE);
		        toast1.show();
		        break;

		
		}
	}	
	
	}
	
}




