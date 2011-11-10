package com.taxiride;
import com.google.android.maps.MapController;

import java.util.List;
 
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
 
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class MapOfRequests extends MapActivity {

	static final String tag = "Main"; // for Log

	private MapController mapController;
	private MapView mapView; 
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
		setContentView(R.layout.main);
	/*	RelativeLayout linearLayout = (RelativeLayout)findViewById(R.id.mainlayout); 
		mapView = (MapView) findViewById(R.id.mapview); 


		//enable traffic on map
		mapView.setTraffic(true);
		//  mapView.setSatellite(true);
		//   mapView.setStreetView(false);
		// set zoom to 16
		mapView.setBuiltInZoomControls(true); 
		mapController = mapView.getController();*/
	/*	Drawable marker=getResources().getDrawable(R.drawable.cmarker);
		Drawable marker2 = getResources().getDrawable(android.R.drawable.star_big_on);
		OverLay overLay = new OverLay(marker);
		OverLay overLay2 = new OverLay(marker2);
		mapView.getOverlays().add(overLay);
		mapView.getOverlays().add(overLay2);
		  GeoPoint point = new GeoPoint(FindBy.FROM_LAT,FindBy.FROM_LNG);
		    overLay2.addItem(point, "myPoint1", "myPoint1");
		GeoPoint point2 = new GeoPoint(FindBy.TO_LAT,FindBy.TO_LNG);
		overLay2.addItem(point2, "myPoint2", "myPoint2");

		Toast toast = Toast.makeText(getApplicationContext(), "From MAP Lat: " + FindBy.FROM_LAT , BIND_AUTO_CREATE);
		toast.show();
		currentLat = FindBy.FROM_LAT;
		currentLog = FindBy.FROM_LNG;

		String lat = String.valueOf(currentLat);
		String lon = String.valueOf(currentLog);
		String action = "";

		String myURL = "http://10.0.0.6:8080/taxibest/TaxiStationController?action=json";



		StringBuilder taxiStationResponse = new StringBuilder();
		StringBuffer jb = new StringBuffer();
		Gson gson = new Gson();
		String taxiDriverData = "";
		int counter = 0;
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
	
	      Type type = new TypeToken<List<TaxiDriverInfo>>(){}.getType();
               List<TaxiDriverInfo> taxiDriverList = gson.fromJson(taxiDriverData, type);

               double latDouble = 0.0;
               double lonDouble = 0.0;

               for (TaxiDriverInfo taxiDriver : taxiDriverList) {
                       latDouble = Double.parseDouble(taxiDriver.getCurrentLatitude());
                       lonDouble = Double.parseDouble(taxiDriver.getCurrentLongitude());

                     Toast toast2 = Toast.makeText(getApplicationContext(),"point: "+ String.valueOf(latDouble), BIND_AUTO_CREATE);
                    toast2.show();

                       currentPoint = new GeoPoint((int) (latDouble * 1e6), (int) (lonDouble * 1e6));
                       overLay.addItem(currentPoint, taxiDriver.getName(), taxiDriver.getName());
                       mapController.setCenter(currentPoint); 
                       mapController.animateTo(currentPoint);
               }*/

              
}

@Override
protected boolean isRouteDisplayed() {
	// TODO Auto-generated method stub
	return false;
}

}




