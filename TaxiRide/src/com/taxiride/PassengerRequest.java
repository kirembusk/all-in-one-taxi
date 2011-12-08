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

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
/*
 * After the user press the open request and press the name of the 
 * requestor this page would fired up. The driver is able to see
 * the information of the passenger as well as buttons to accept 
 * the request or call the passenger 
 */
public class PassengerRequest extends LoggingActivity{

		private String driverLat;
		private String driverLog;
		private String passengerLat;
		private String passengerLog;
		private double time; 
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
				
				// get the coordinate of the passenger and the driver
				passengerLat = ListOfRequest.TAXIREQUEST.getCurrentLatitude();
				passengerLog = ListOfRequest.TAXIREQUEST.getCurrentLongitude();
				driverLat = DriverWindow.driverInfo.getCurrentLatitude();
				driverLog = DriverWindow.driverInfo.getCurrentLongitude();
				
				// convert it into miles 
				time = (getDistance(driverLat, driverLog, passengerLat, passengerLog) *0.4);
				
				// accept the request
				Button accept = (Button) findViewById(R.id.accept);
				
				accept.setOnClickListener(new View.OnClickListener() {
		        	public void onClick(View view) {
		        		//send http request, if the driver has successfully
		        		// accepted the requet to the server than it would return success
		        		// otherwise it would return failed 
		        		
		        		String result = sendHttpRequest();
		        		if(result.equals("auth")){
		        			Toast toast1 = Toast.makeText(getApplicationContext(), "invalid authentication ",100);
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
				
				
				// a call button to call the passenger
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
		 
		 // get the distance between two points and return the result in double and mile
		 public double getDistance(String lat_A, String log_A, String lat_B, String log_B){
				float[] results = {0};
				double latA = Double.parseDouble(lat_A);
				double logA = Double.parseDouble(log_A);
				double latB = Double.parseDouble(lat_B);
				double logB = Double.parseDouble(log_B);
				
			    Location distanceBetween = new Location ("point a to b");
			
				distanceBetween.distanceBetween(latA, logA,latB, logB, results);
			    
			    
			    double result = (float)(results[0]);
			    result = result * 0.000621371192;
			    DecimalFormat newFormat = new DecimalFormat("#.#");
			    double convertMiles = Double.valueOf(newFormat.format(result));
			       
			    return convertMiles;
				
			}
		 
		 // send http request to accept the request. Return success if successlly or return fail
		 // if it's unsuccessfully. 
		 public String sendHttpRequest(){
		  	   
		  	   String myURL = "http://taxitestcenter.appspot.com/approve";
					 
					 StringBuilder taxiStationResponse = new StringBuilder();
					 StringBuffer jb = new StringBuffer();
					 Gson gson = new Gson();
						String requestResult = "";
						
						int counter = 0;
						
		
						try {
							// Construct data
							 
						
							
							 String data = URLEncoder.encode("driverLoginName", "UTF-8") + "=" + URLEncoder.encode(DriverWindow.driverInfo.getDeviceID(), "UTF-8");
							  data += "&" + URLEncoder.encode("driverLoginPin", "UTF-8") + "=" + URLEncoder.encode(DriverWindow.driverInfo.getPin(), "UTF-8");
							  data += "&" + URLEncoder.encode("driverLatitude", "UTF-8") + "=" + URLEncoder.encode(driverLat, "UTF-8");
							  data += "&" + URLEncoder.encode("driverLongitude", "UTF-8") + "=" + URLEncoder.encode(driverLog, "UTF-8");
							  data += "&" + URLEncoder.encode("estimatedArrivalTime", "UTF-8") + "=" + URLEncoder.encode(String.valueOf(time), "UTF-8");
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
