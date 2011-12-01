package com.taxiride;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import com.google.gson.Gson;
import com.taxiride.model.TaxiRequest;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PassengerRequest extends LoggingActivity{

	
		 public void onCreate(Bundle savedInstanceState) {
		        super.onCreate(savedInstanceState);
		        setContentView(R.layout.passengerrequest);
		        TextView requestName = (TextView) findViewById(R.id.RequestName);
				requestName.setText("Request Name: " + ListOfRequest.TAXIREQUEST.getRequestName());
				
				TextView requestPhone = (TextView) findViewById(R.id.RequestPhone);  
				requestPhone.setText("Request Phone: " + ListOfRequest.TAXIREQUEST.getRequestPhoneNumber());
				
				TextView requestLocation = (TextView) findViewById(R.id.fromAddress);
				requestLocation.setText("PickUp Location: " + ListOfRequest.TAXIREQUEST.getRequestPickupLocation());
				
				TextView destination = (TextView) findViewById(R.id.toAddress);
				destination.setText("Destination: " + ListOfRequest.TAXIREQUEST.getRequestDestination());
				
				TextView totalPass = (TextView) findViewById(R.id.totalPass);
				totalPass.setText("Total Passengers: " + ListOfRequest.TAXIREQUEST.getTotalPeople());
				
				TextView status = (TextView) findViewById(R.id.status);
			
				
			//	if(ListOfRequest.TAXIREQUEST.getIsRequestTaken() == "Y"){
			//	status.setText("Status:  Close  ------- Assigned to Driver: " + ListOfRequest.TAXIREQUEST.getAssignedDriverLogin());// + ListOfRequest.TAXIREQUEST.getAssignedDriverLogin());
				 //Toast toast2 = Toast.makeText(getApplicationContext(),"String:  "+ ListOfRequest.TAXIREQUEST.getAssignedDriverLogin(), BIND_AUTO_CREATE);
                 //toast2.show();
			//	}
			//	else
			//	status.setText("Status:  Open  -------- Driver hasn't been assigned yet");
				//Button accept = (Button)findViewById(R.id.status);
				//accept
				
				
				
				Button accept = (Button) findViewById(R.id.accept);
				
				accept.setOnClickListener(new View.OnClickListener() {
		        	public void onClick(View view) {
		        		String result = sendHttpRequest();
		        		if(result.equals("auth")){
		        			Toast toast1 = Toast.makeText(getApplicationContext(), "SCREW YOU!!!! ",100);
		        			toast1.show(); 
		        		}else if(result.equals("success")){
		        			Toast toast = Toast.makeText(getApplicationContext(), "Your request has been successfully confirmed. Please Check your order in the 'My Order' scrren ", 100);
		        			toast.show();
		        		}else if (result.equals("fail")){
		        			Toast toast2 = Toast.makeText(getApplicationContext(),"Your request has been taken by someone else, please select another request or try again ", 100); 
		        			toast2.show(); 
		        		}
		        	}
				});
				
				Button call = (Button) findViewById(R.id.call);
				call.setOnClickListener(new View.OnClickListener() {

					@Override
					public void onClick(View arg0) {
					String tel = "tel:" + ListOfRequest.TAXIREQUEST.getRequestPhoneNumber();
							 Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri
				                     .parse(tel));
				             startActivity(dialIntent);
						
					}
					
					
				});
				
		 }
		 
		 public String sendHttpRequest(){
		  	   
		  	   String myURL = "http://taxitestcenter.appspot.com/approve";
					 
					 StringBuilder taxiStationResponse = new StringBuilder();
					 StringBuffer jb = new StringBuffer();
					 Gson gson = new Gson();
						String requestResult = "";
						
						int counter = 0;
						
		
						try {
							// Construct data
							 
							Toast.makeText(getApplicationContext(),"Name: " + DriverWindow.driverInfo.getDeviceID() + " pin: " + DriverWindow.driverInfo.getPin(), 100).show(); 
							
							 String data = URLEncoder.encode("driverLoginName", "UTF-8") + "=" + URLEncoder.encode(DriverWindow.driverInfo.getDeviceID(), "UTF-8");
							 data += "&" + URLEncoder.encode("driverLoginPin", "UTF-8") + "=" + URLEncoder.encode(DriverWindow.driverInfo.getPin(), "UTF-8");
							 data += "&" + URLEncoder.encode("driverLatitude", "UTF-8") + "=" + URLEncoder.encode(DriverWindow.driverInfo.getCurrentLatitude(), "UTF-8");
							 data += "&" + URLEncoder.encode("driverLongitude", "UTF-8") + "=" + URLEncoder.encode(DriverWindow.driverInfo.getCurrentLongitude(), "UTF-8");
							  data += "&" + URLEncoder.encode("estimatedArrivalTime", "UTF-8") + "=" + URLEncoder.encode("10:05", "UTF-8");
							  data += "&" + URLEncoder.encode("requestID", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(ListOfRequest.requestID), "UTF-8");
						 
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
