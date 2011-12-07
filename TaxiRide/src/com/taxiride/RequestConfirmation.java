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
/*
 * This class is for the passenger side. This is the 
 * confirmation page after the passenger has 
 * reserved a taxi
 * 
 */
public class RequestConfirmation extends LoggingActivity {
	TextView driverName;
	TextView driverPhone;
	TextView arrivalTime;
	TextView cost; 
	double distance; 
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.requestconfirmation);
		// get the driver name and their information and display on screen
		
		driverName = (TextView) findViewById(R.id.driverName);
		driverPhone = (TextView) findViewById(R.id.driverPhone);
		arrivalTime = (TextView) findViewById(R.id.arrivalTime);
		cost = (TextView) findViewById(R.id.cost);
		
	
		
		// get from and to address of passenger and display on screen
		TextView fromAddress = (TextView) findViewById(R.id.fromAddress);
		fromAddress.setText("From: " + FindBy.passengerInfo.getFromAddress());
		
		TextView toAddress = (TextView) findViewById(R.id.toAddress);
		toAddress.setText("To: " + FindBy.passengerInfo.getToAddress());
		
		
		// the refresh button. Is to get request from the server and 
		//see if any driver has taken this order
		// if the taxi driver take this order, it would display 
		// the driver name and information on screen otherwise
		// it's blank
		Button refresh = (Button) findViewById(R.id.refresh);
		
		refresh.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View view) {
        		
        			//send an http request to update the information
        			// if a taxi driver has not accept this request return nothing
        		    // else return an object with the driver name phone number and etc
                      TaxiRequest requestInfo =  updateHttpRequest();
                    
                      if(requestInfo.getAssignedDriverName().equals("")){
                    	  driverName.setText("Driver name:");
                          driverPhone.setText("Driver phone #:");
                          arrivalTime.setText("Estimated Arrival Time:");
                          cost.setText("Estimated Cost:");
                    	  
                      }else{
                    	 
                    	  double tripCost = FindBy.passengerInfo.getDistance() * 2 ; 
                    	  
                    	  driverName.setText("Driver name: " + requestInfo.getAssignedDriverName());
                    	  driverPhone.setText("Driver phone #: "+ requestInfo.getAssignedDriverPhoneNumber());
                    	  arrivalTime.setText("Estimated Arrival Time: "+ requestInfo.getEstimatedArrivalTime() + "min");
                    	  cost.setText("Estimated Cost: $" + tripCost + 3);
                    	  
                      }
        		
            }

        });
		
		// this is the cancel button. Is to cacnel the request if the passenger
		// wanted to 
		Button cancel = (Button) findViewById(R.id.cancel);
		cancel.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// send an http request to cancel the request. return failed if failed otherwise it's successfully
				String result =  cancelSendHttpRequest();
				if(result.equals("fail")){
        			Toast toast = Toast.makeText(getApplicationContext(), "Cannot cancel your request! Please try again! ", 1000);
        			toast.show();
        		}else{
        			// after canceling the http request, the confirmation is reset back to empty on every field
        			FindBy.passengerInfo.setconfirmID(result);
        			 driverName.setText("Driver name:");
                     driverPhone.setText("Driver phone #:");
                     arrivalTime.setText("Estimated Arrival Time:");
                     cost.setText("Estimated Cost:");
                     Toast toast1 = Toast.makeText(getApplicationContext(), "Your order has been successfully cancel", BIND_AUTO_CREATE);
                     toast1.show(); 
        		}
				
              	  
                
				
			}
			
			
		});
	}
	// Send an http request on the refresh button to check to see if any 
	// driver has accepted this request, if it has it would return a TaxiRequest
	// object which has information of the driver
	 public TaxiRequest updateHttpRequest(){
	  	   
	  	   String myURL = "http://taxitestcenter.appspot.com/update";
				 
				 StringBuilder taxiStationResponse = new StringBuilder();
				 StringBuffer jb = new StringBuffer();
				 Gson gson = new Gson();
					String requestResult = "";
					
	
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
	 
	 //cancel the http rquest and send it to the server
	
	 public String cancelSendHttpRequest(){
	  	   
	  	   String myURL = "http://taxitestcenter.appspot.com/revoke";
				 
				 StringBuilder taxiStationResponse = new StringBuilder();
				 StringBuffer jb = new StringBuffer();
				 Gson gson = new Gson();
					String requestResult = "";
				
					try {
						// Construct data
						 
						 String data = URLEncoder.encode("requestID", "UTF-8") + "=" + URLEncoder.encode(FindBy.passengerInfo.getconfirmID(), "UTF-8");
						 data += "&" + URLEncoder.encode("deviceID", "UTF-8") + "=" + URLEncoder.encode(FindBy.passengerInfo.getDeviceID(), "UTF-8");
						  
						  
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
				 
				 
	
	  	   return requestResult; 
	     	   
	     }
	 
	 
	 
	
		
	
}
