package com.taxiride;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import com.google.gson.Gson;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DriverRequest extends LoggingActivity {

	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.driverrequest);    
	        
	        final EditText name = (EditText) findViewById(R.id.editName); 
	        name.setText(FindBy.passengerInfo.getfullName());
	        
	        final EditText phoneNum = (EditText) findViewById(R.id.PhoneNumber); 
	        phoneNum.setText(FindBy.passengerInfo.getPhoneNum());
	        
	        final EditText fromAddress = (EditText) findViewById(R.id.editFromAddress);
	        fromAddress.setText(FindBy.passengerInfo.getFromAddress());
	        
	        final EditText toAddress = (EditText) findViewById(R.id.editToAddress);
	        toAddress.setText(FindBy.passengerInfo.getToAddress());
	        
	        final EditText numOfPeople = (EditText) findViewById(R.id.EditNumOfPeople); 
	        FindBy.passengerInfo.setTotalPeople(numOfPeople.getText().toString());
	    
	        
	        FindBy.passengerInfo.setFromAddress(fromAddress.getText().toString());
	        FindBy.passengerInfo.setToAddress(toAddress.getText().toString());
	        
	        Button enter = (Button) findViewById(R.id.ButtonEnter);
	        
	        enter.setOnClickListener(new View.OnClickListener() {
	        	public void onClick(View view) {
	        		boolean isDone = false;
	        		String result = updateHttpRequest();
	        		if(result.equals("fail")){
	        			Toast toast = Toast.makeText(getApplicationContext(), "Cannot submit to server please try again! ", 1000);
	        			toast.show();
	        		}else{
	        			FindBy.passengerInfo.setconfirmID(result);
	        			Toast toast = Toast.makeText(getApplicationContext(), "Successfully submit to the server ", 1000);
	        			toast.show();
	        			isDone=true; 
	        		}
	        		
	        		if(isDone==true){
	                  Intent myIntent = new Intent(view.getContext(), RequestConfirmation.class);
	                  startActivityForResult(myIntent, 0);
	        		}
	            }

	        });
		}
	 
	 public String updateHttpRequest(){
  	   
  	   String myURL = "http://taxitestcenter.appspot.com/submit";
			 
			 StringBuilder taxiStationResponse = new StringBuilder();
			 StringBuffer jb = new StringBuffer();
			 Gson gson = new Gson();
				String requestResult = "";
				
				int counter = 0;
				try {
					// Construct data
					 
					 String data = URLEncoder.encode("requestName", "UTF-8") + "=" + URLEncoder.encode(FindBy.passengerInfo.getfullName(), "UTF-8");
					  data += "&" + URLEncoder.encode("requestPhoneNumber", "UTF-8") + "=" + URLEncoder.encode(FindBy.passengerInfo.getPhoneNum(), "UTF-8");
					  data += "&" + URLEncoder.encode("requestPickupLocation", "UTF-8") + "=" + URLEncoder.encode(FindBy.passengerInfo.getFromAddress(), "UTF-8");
					  data += "&" + URLEncoder.encode("requestDestination", "UTF-8") + "=" + URLEncoder.encode(FindBy.passengerInfo.getToAddress(), "UTF-8");
					  data += "&" + URLEncoder.encode("totalDistance", "UTF-8") + "=" + URLEncoder.encode(Double.toString(FindBy.passengerInfo.getDistance()), "UTF-8");
					  data += "&" + URLEncoder.encode("totalPeople", "UTF-8") + "=" + URLEncoder.encode(FindBy.passengerInfo.getTotalPeople(), "UTF-8");
					  data += "&" + URLEncoder.encode("preferredPayment", "UTF-8") + "=" + URLEncoder.encode(FindBy.passengerInfo.getPaymentType(), "UTF-8");
					  data += "&" + URLEncoder.encode("currentLatitude", "UTF-8") + "=" + URLEncoder.encode(Double.toString(FindBy.passengerInfo.getFromLat()), "UTF-8");
					  data += "&" + URLEncoder.encode("currentLongitude", "UTF-8") + "=" + URLEncoder.encode(Double.toString(FindBy.passengerInfo.getFromlog()), "UTF-8");
					  data += "&" + URLEncoder.encode("toLatitude", "UTF-8") + "=" + URLEncoder.encode(Double.toString(FindBy.passengerInfo.getToLat()), "UTF-8");
					  data += "&" + URLEncoder.encode("toLongitude", "UTF-8") + "=" + URLEncoder.encode(Double.toString(FindBy.passengerInfo.getToLog()), "UTF-8");
					  
					  
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
			 //return ID, confirmation number, that this thing is record on system
			 // if failed, please submit again.
			 requestResult = taxiStationResponse.toString();
		//	 requestResult = requestResult.substring(1);
		//	 requestResult = requestResult.substring(0, requestResult.length() - 1);
  	   return requestResult; 
  	   
  	   
     }
	        
	 }       
	
	
