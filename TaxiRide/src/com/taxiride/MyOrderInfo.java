package com.taxiride;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.taxiride.MyOrder;
import com.taxiride.model.TaxiRequest;

public class MyOrderInfo extends LoggingActivity{
	public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
		 
		 setContentView(R.layout.myorderinfo);
		 TextView requestName = (TextView) findViewById(R.id.RequestName);
			requestName.setText("Request Name: " + MyOrder.ORDER.getRequestName());
			
			TextView requestPhone = (TextView) findViewById(R.id.RequestPhone);  
			requestPhone.setText("Request Phone: " + MyOrder.ORDER.getRequestPhoneNumber());
			
			TextView requestLocation = (TextView) findViewById(R.id.fromAddress);
			requestLocation.setText("PickUp Location: " + MyOrder.ORDER.getRequestPickupLocation());
			
			TextView destination = (TextView) findViewById(R.id.toAddress);
			destination.setText("Destination: " + MyOrder.ORDER.getRequestDestination());
			
			TextView totalPass = (TextView) findViewById(R.id.totalPass);
			totalPass.setText("Total Passengers: " + MyOrder.ORDER.getTotalPeople());
			
			TextView status = (TextView) findViewById(R.id.status);
			status.setText("Status: Assigned"); 
			
			
			 Button cancel = (Button) findViewById(R.id.cancel);  
			 cancel.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View arg0) {
					
					String result = sendHttpRequest(); 
					if(result.equals("auth")){
	        			Toast toast1 = Toast.makeText(getApplicationContext(), "invalid authentication ",100);
	        			toast1.show(); 
	        		}else if(result.equals("success")){
	        			Toast toast = Toast.makeText(getApplicationContext(), "Your request has been successfully cancel ", 100);
	        			toast.show();
	        		}else if (result.equals("fail")){
	        			Toast toast2 = Toast.makeText(getApplicationContext(),"Your request has NOT been cancel due to server error, please select another request or try again ", 100); 
	        			toast2.show(); 
	        		}
				}
				 
			 });
			 
			 Button complete = (Button) findViewById(R.id.complete);  
			 complete.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View arg0) {
					String result = completeHttpRequest();
					if(result.equals("auth")){
	        			Toast toast1 = Toast.makeText(getApplicationContext(), "invalid authentication ",100);
	        			toast1.show(); 
	        		}else if(result.equals("success")){
	        			Toast toast = Toast.makeText(getApplicationContext(), "Your request has been successfully completed ", 100);
	        			toast.show();
	        		}else if (result.equals("fail")){
	        			Toast toast2 = Toast.makeText(getApplicationContext(),"Your request has NOT been complete due to server error, please select another request or try again ", 100); 
	        			toast2.show(); 
	        		}
					
				}
				 
			 });
			 
			 Button call  = (Button) findViewById(R.id.call);
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
		 String myURL = "http://taxitestcenter.appspot.com/cancel";
		 
		 StringBuilder taxiStationResponse = new StringBuilder();
		 StringBuffer jb = new StringBuffer();
		 Gson gson = new Gson();
			String requestResult = "";
			
			

			try {
				// Construct data
				
				 String data = URLEncoder.encode("driverLoginName", "UTF-8") + "=" + URLEncoder.encode(DriverWindow.driverInfo.getDeviceID(), "UTF-8");
				 data += "&" + URLEncoder.encode("driverLoginPin", "UTF-8") + "=" + URLEncoder.encode(DriverWindow.driverInfo.getPin(), "UTF-8");
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
	
	public String completeHttpRequest(){
		String myURL = "http://taxitestcenter.appspot.com/complete";
		 
		 StringBuilder taxiStationResponse = new StringBuilder();
		 StringBuffer jb = new StringBuffer();
		 Gson gson = new Gson();
			String requestResult = "";
			
			

			try {
				// Construct data
			//	Toast.makeText(getApplicationContext(),"Name: " + DriverWindow.driverInfo.getDeviceID() + " pin: " + DriverWindow.driverInfo.getPin(), 100).show(); 
				
				 String data = URLEncoder.encode("driverLoginName", "UTF-8") + "=" + URLEncoder.encode(DriverWindow.driverInfo.getDeviceID(), "UTF-8");
				 data += "&" + URLEncoder.encode("driverLoginPin", "UTF-8") + "=" + URLEncoder.encode(DriverWindow.driverInfo.getPin(), "UTF-8");
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
