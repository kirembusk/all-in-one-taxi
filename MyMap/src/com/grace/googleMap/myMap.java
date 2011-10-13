package com.grace.googleMap;
import com.google.android.maps.MapController;

import java.util.List;
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
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager; 



public class myMap extends MapActivity {
    private static final Intent LOCATION_CHANGED = null;
    private MapController mapController;
    private LocationManager locationManager;
    private LocationListener locationListener;
    private MapView mapView; 
  
   
	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
      
        // show the google map
        setContentView(R.layout.main);
        
        
        RelativeLayout linearLayout = (RelativeLayout)findViewById(R.id.mainlayout); 
        mapView = (MapView) findViewById(R.id.mapview); 
        
        //enable traffic on map
          mapView.setTraffic(true);
        // set zoom to 16
        mapView.setBuiltInZoomControls(true); 
        mapController = mapView.getController();
        
       
        locationManager =(LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationListener = new GPSLocationListener();
        
        locationManager.requestLocationUpdates(
          LocationManager.GPS_PROVIDER, 
          0, 
          0, 
          new GPSLocationListener());
        
        
	}

        @Override
    	protected boolean isRouteDisplayed() {
    		// TODO Auto-generated method stub
    		return false;
    	}
    	
	
	public class GPSLocationListener implements LocationListener 
	{
	  public void onLocationChanged(Location location) {
		
	    if (location != null) {
	    	
	    	Drawable marker=getResources().getDrawable(android.R.drawable.star_big_on);
	        int markerWidth = marker.getIntrinsicWidth();
	        int markerHeight = marker.getIntrinsicHeight();
	        marker.setBounds(0, markerHeight, markerWidth, 0);
	        OverLay overLay = new OverLay(marker);
	        mapView.getOverlays().add(overLay);
	        
	       GeoPoint point = new GeoPoint(
	          (int) (location.getLatitude() * 1E6), 
	          (int) (location.getLongitude() * 1E6));
	       	overLay.addItem(point, "myPoint1", "myPoint1");
	       GeoPoint point2 = new GeoPoint((int) ((location.getLatitude() * 1E6)+10000), 
	 	          (int) (location.getLongitude() * 1E6));
	       overLay.addItem(point2, "myPoint2", "myPoint2");
	       mapController.animateTo(point2);
	      //mapController.setZoom(13); 
		       mapView.invalidate();
         
	      
	    }
	  }
	
	  public Location locationReturn(Location location){return location;}
	public void onProviderDisabled(String arg0) {
		// TODO Auto-generated method stub
		
	}

	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
	
	}
	
}




