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
    private GeoPoint point;
    public int lng;
    public int log;
    public  Drawable drawable;
    public OverLay itemizedoverlay;
    public List<Overlay> mapOverlays ;
    
    
    
   
	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // show the google map
        setContentView(R.layout.main);
        
        //zoom map 
      //  RelativeLayout linearLayout = (RelativeLayout)findViewById(R.id.mainlayout); 
        mapView = (MapView) findViewById(R.id.mapview); 
        mapView.setBuiltInZoomControls(true); 
        //enable street view
        mapView.setStreetView(true);
        //enable sateillite view
      //  mapView.setSatellite(true);
        //enable traffic on map
       // mapView.setTraffic(true);
        // set zoom to 16
        mapController = mapView.getController();
        mapController.setZoom(14); 
        
        //zoom control
        
      
        //find current location
      
        //creating a marker
        /*List<Overlay> mapOverlays = mapView.getOverlays();
        Drawable drawable = this.getResources().getDrawable(R.drawable.cmarker);
        OverLay itemizedoverlay = new OverLay(drawable);*/
       /* mapOverlays = mapView.getOverlays();
        drawable = this.getResources().getDrawable(R.drawable.cmarker);
        itemizedoverlay = new OverLay(drawable);*/
        
      /*  GeoPoint point = new GeoPoint(19240000,-99120000);
        OverlayItem overlayitem = new OverlayItem(point, "Grace" , "current location");
        itemizedoverlay.addOverlay(overlayitem);
        mapOverlays.add(itemizedoverlay);*/
        
        locationManager =(LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener(){

			public void onLocationChanged(Location location) {
				// TODO Auto-generated method stub
				int lng= (int)(location.getLongitude());
				int lat = (int)(location.getLatitude());
				 point = new GeoPoint(lat,lng);
				 mapController.animateTo(point);   
			       
			}
			
			

			public void onProviderDisabled(String provider) {
				// TODO Auto-generated method stub
				
			}

			public void onProviderEnabled(String provider) {
				// TODO Auto-generated method stub
				
			}

			public void onStatusChanged(String provider, int status,
					Bundle extras) {
				// TODO Auto-generated method stub
				
			}
        	
        };
        
	}

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	/*public void getLatandLong(){
		LocationManager lm =(LocationManager) getSystemService(Context.LOCATION_SERVICE);
		List providers = lm.getAllProviders();
		Location loc = lm.getLastKnownLocation(LOCATION_SERVICE); 
		updatePositionOnScreen(loc); 
		Intent i = new Intent(LOCATION_CHANGED);
		LocationListener ll = new LocationListener() { 
			
			public void onLocationChanged(Location location) {   
				updatePositionOnScreen(location); } 
			
			public void onProviderDisabled(String provider) { } 
			public void onProviderEnabled(String provider) { } 
			public void onStatusChanged(String provider, int status, Bundle extras) { }};
			
		lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 30000, 100, ll);
		
		
	}

	private void updatePositionOnScreen(Location loc) {
				
	}*/
	
	
}






