package com.taxiride;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.DecimalFormat;

import com.google.gson.Gson;
import com.taxiride.model.TaxiRequest;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class RequestConfirmation extends LoggingActivity {
	TextView driverName;
	TextView driverPhone;
	TextView arrivalTime;
	TextView cost; 
	double distance; 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.requestconfirmation);
		
		driverName = (TextView) findViewById(R.id.driverName);
		driverPhone = (TextView) findViewById(R.id.driverPhone);
		arrivalTime = (TextView) findViewById(R.id.arrivalTime);
		cost = (TextView) findViewById(R.id.cost);
		
	
		
		
		TextView fromAddress = (TextView) findViewById(R.id.fromAddress);
		fromAddress.setText("From: " + FindBy.passengerInfo.getFromAddress());
		
		TextView toAddress = (TextView) findViewById(R.id.toAddress);
		toAddress.setText("To: " + FindBy.passengerInfo.getToAddress());
		
		Button refresh = (Button) findViewById(R.id.refresh);
		
		refresh.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View view) {
        		
                      TaxiRequest requestInfo =  updateHttpRequest();
                    
                      if(requestInfo.getAssignedDriverName().equals("")){
                    	  driverName.setText("Driver name:");
                          driverPhone.setText("Driver phone #:");
                          arrivalTime.setText("Estimated Arrival Time:");
                          cost.setText("Estimated Cost:");
                    	  
                      }else{
                    	  String driverLat = requestInfo.getAssignedDriverLatitude();
                    	  String driverLog = requestInfo.getAssignedDriverLongitude();
                    	  double passLat = FindBy.passengerInfo.getFromLat();
                    	  double passLog = FindBy.passengerInfo.getFromlog();
                    	  
                    	  double time = getDistance(driverLat,driverLog,passLat,passLog); 
                    	  double miles = (time * 0.5);
                    	  double tripCost = FindBy.passengerInfo.getDistance() * 2; 
                    	 Toast toast1=  Toast.makeText(getApplicationContext(), "Driver Lat: " + driverLat + " Driver Log: " + driverLog 
                    			  + " passenger lat: " + passLat + " passenger log: " + passLog, BIND_AUTO_CREATE);
                    	 toast1.show(); 
                    	  driverName.setText("Driver name: " + requestInfo.getAssignedDriverName());
                    	  driverPhone.setText("Driver phone #: "+ requestInfo.getAssignedDriverPhoneNumber());
                    	  arrivalTime.setText("Estimated Arrival Time: "+ time + "min");
                    	  cost.setText("Estimated Cost: $" + tripCost + 3);
                    	  
                      }
        		
            }

        });
	}
	public double getDistance(String lat_A, String log_A, double lat_B, double log_B){
		float[] results = {0};
		double latA = Double.parseDouble(lat_A);
		double logA = Double.parseDouble(log_A);
		
	    Location distanceBetween = new Location ("point a to b");
	
		distanceBetween.distanceBetween(latA, logA,lat_B, log_B, results);
	    
	    
	    double result = (float)(results[0]);
	    result = result * 0.000621371192;
	    DecimalFormat newFormat = new DecimalFormat("#.#");
	    double convertMiles = Double.valueOf(newFormat.format(result));
	       
	    return convertMiles;
		
	}
	 public TaxiRequest updateHttpRequest(){
	  	   
	  	   String myURL = "http://taxitestcenter.appspot.com/update";
				 
				 StringBuilder taxiStationResponse = new StringBuilder();
				 StringBuffer jb = new StringBuffer();
				 Gson gson = new Gson();
					String requestResult = "";
					
					int counter = 0;
					
	
					try {
						// Construct data
						 
						 String data = URLEncoder.encode("requestID", "UTF-8") + "=" + URLEncoder.encode(FindBy.passengerInfo.getconfirmID(), "UTF-8");
					
						  
						  
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
				 
				 requestResult = taxiStationResponse.toString();
				 TaxiRequest taxiDriver = gson.fromJson(requestResult, TaxiRequest.class);
				 
				
			
	  	   return taxiDriver; 
	     	   
	     }
	 
	 
	
		
	
}
