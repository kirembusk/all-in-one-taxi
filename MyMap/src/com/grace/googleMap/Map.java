package com.grace.googleMap;
import com.google.android.maps.MapController;

import java.util.List;
import java.util.Locale;
import java.util.Vector;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.Object; 
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;


import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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

	static final String tag = "Main"; // for Log

	private static final Intent LOCATION_CHANGED = null;
	private MapController mapController;
	private LocationManager locationManager;
	private LocationListener locationListener;
	private MapView mapView; 
	private Geocoder geoCoder;
	private Bundle bundle; 
	private String toAddress;
	private String fromAddress;
	GeoPoint currentPoint;
	private int currentLat;
	private int currentLog;

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
			currentLat = (int) (location.getLatitude() * 1E6);
			currentLog = (int) (location.getLongitude() * 1E6);

			// GeoPoint point = new GeoPoint(currentLat,currentLog);
			// overLay.addItem(point, "myPoint1", "myPoint1");

			//GeoPoint point2; 
			//  try {
			//Toast toast = Toast.makeText(getApplicationContext(),"from Address: " + fromAddress + " To address: "+ toAddress, BIND_AUTO_CREATE);
			//toast.show();

			// List<Address> addresses = geoCoder.getFromLocationName( 
			//	toAddress, 5);
			// String add = "";

			/*  if (addresses.size() > 0) {
   	        //        point2 = new GeoPoint(
   	          //              (int) (addresses.get(0).getLatitude() * 1E6), 
   	            //            (int) (addresses.get(0).getLongitude() * 1E6));
   	             //   overLay.addItem(point2, "myPoint2", "myPoint2");

   	              //  mapController.animateTo(point);
   	              //  map.animateTo(p);    
   	           //     mapView.invalidate();
   	            }    
   	        } catch (IOException e) {
   	            e.printStackTrace();
   	        }*/

		//	String temp = "";
		//	String [] points;
		//	GeoPoint point3;
			

			String lat = String.valueOf(currentLat);
			String lon = String.valueOf(currentLog);
			String action = "";

			String myURL = "http://localhost:8080/taxibest/TaxiStationController?action=json";



			StringBuilder taxiStationResponse = new StringBuilder();
			StringBuffer jb = new StringBuffer();
			Gson gson = new Gson();
			String taxiDriverData = "";

			try {
				// Construct data
				String data = URLEncoder.encode("action", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8");
				data += "&" + URLEncoder.encode("lat", "UTF-8") + "=" + URLEncoder.encode(lat, "UTF-8");
				data += "&" + URLEncoder.encode("lon", "UTF-8") + "=" + URLEncoder.encode(lon, "UTF-8");


				URL url = new URL(myURL);
				URLConnection conn =  url.openConnection();
				conn.setDoOutput(true);

				// Send data

				OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
				wr.write(data);
				wr.flush();

				// Get the response
				BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String line;


				while ((line = rd.readLine()) != null) {
					// Process line...
					taxiStationResponse.append(line);
				}
				wr.close();
				rd.close();
			} catch (Exception e) {
			}


			taxiDriverData = taxiStationResponse.toString();
			System.out.println(taxiDriverData);

			Type type = new TypeToken<List<TaxiDriver>>(){}.getType();
			List<TaxiDriver> taxiDriverList = gson.fromJson(taxiDriverData, type);

			double latDouble = 0.0;
			double lonDouble = 0.0;
			
			for (TaxiDriver taxiDriver : taxiDriverList) {
				latDouble = Double.parseDouble(taxiDriver.getCurrentLatitude());
				lonDouble = Double.parseDouble(taxiDriver.getCurrentLongitude());
				
			//	Toast toast = Toast.makeText(getApplicationContext(),"point: "+ String.valueOf(latDouble), BIND_AUTO_CREATE);
			//	toast.show();
				
				currentPoint = new GeoPoint((int) latDouble, (int) lonDouble);
				overLay.addItem(currentPoint, taxiDriver.getName(), taxiDriver.getName());
				mapController.setCenter(currentPoint); 
			}


			//for(int i = 0; i< geopoints.size();i++){
				//temp =geopoints.get(i);
				//points = temp.split(":");

				//Toast toast = Toast.makeText(getApplicationContext(),"point[0] "+ points[0], BIND_AUTO_CREATE);
				//toast.show();
				//double lat = Double.parseDouble(points[0]);
				//double lng = Double.parseDouble(points[1]);
				//  Toast toast = Toast.makeText(getApplicationContext(),"lat: " + lat + " lng: "+ lng, BIND_AUTO_CREATE);
				//	toast.show();

				//point3 = new GeoPoint((int)lat,(int)lng);
				//overLay.addItem(point3,"mypoint","mypoint");
				//	mapController.animateTo(point3);


			//}


			// mapController.setCenter(currentPoint); 
			//mapController.animateTo(point2);
			//mapController.setZoom(13); 
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
			
			Log.v(tag, "Enabled");
		//	Toast.makeText(this, "GPS Enabled", Toast.LENGTH_SHORT).show();



		}

		//   @Override
		public void onProviderDisabled(String provider) {
			/* this is called if/when the GPS is disabled in settings */
			Log.v(tag, "Disabled");

			/* bring up the GPS settings */
			Intent intent = new Intent(
					android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS);
			startActivity(intent);


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




