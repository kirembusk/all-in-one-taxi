package com.taxiride;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import com.google.gson.Gson;
import com.taxiride.model.TaxiRequest;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RequestConfirmation extends LoggingActivity {
	TextView driverName;
	TextView driverPhone;
	TextView arrivalTime;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.requestconfirmation);
		
		driverName = (TextView) findViewById(R.id.driverName);
		driverPhone = (TextView) findViewById(R.id.driverPhone);
		arrivalTime = (TextView) findViewById(R.id.arrivalTime);
		
		
		//fromAddress.setText("From: " + FindBy.passengerInfo.getFromAddress());
		
		
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
                    	  
                      }else{
                    	  driverName.setText("Driver name: " + requestInfo.getAssignedDriverName());
                    	  driverPhone.setText("Driver phone #: "+ requestInfo.getAssignedDriverPhoneNumber());
                    	  arrivalTime.setText("Estimated Arrival Time: "+ requestInfo.getEstimatedArrivalTime());
                      }
        		
            }

        });
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
