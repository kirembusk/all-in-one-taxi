package com.taxiride;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.taxiride.model.TaxiDriver;
import com.taxiride.model.TaxiRequest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LogInPage extends Activity  {
	private String loginName;
	private String loginPin;
	private TaxiDriver taxiDriver;
	
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        //select the log in page for layout
	        setContentView(R.layout.loginpage);
	        final EditText login = (EditText) findViewById(R.id.Username);
            final EditText pin = (EditText) findViewById(R.id.Text_Password);
            loginName = login.getText().toString();
            loginPin = pin.getText().toString();
	        Button enter = (Button) findViewById(R.id.ButtonEnter); 
	        
	        enter.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View view) {
	            	
	            /*	String myURL = "http://taxitestcenter.appspot.com/login";
	       		 StringBuilder taxiStationResponse = new StringBuilder();
	       			StringBuffer jb = new StringBuffer();
	       			Gson gson = new Gson();
	       			String taxiRequestData = "";
	       			int counter = 0;
	       			try {
	       				// Construct data
	       				 String data = URLEncoder.encode(loginName, "UTF-8") + "=" + URLEncoder.encode(loginName, "UTF-8");
	       				data += "&" + URLEncoder.encode(loginPin, "UTF-8") + "=" + URLEncoder.encode(loginPin, "UTF-8");
	       				 
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
	       		   taxiRequestData = taxiStationResponse.toString();
	       		 
	       		    
	       		    taxiDriver = gson.fromJson(taxiRequestData, TaxiDriver.class);*/
	             
	            //  if( taxiDriver.isAuth()){
	                Intent myIntent = new Intent(view.getContext(),DriverWindow.class);
	                startActivityForResult(myIntent, 0);
	                
	               //}else{
	            	  // Toast toast2 = Toast.makeText(getApplicationContext(),"LOG IN FAILED", BIND_AUTO_CREATE);
	                   // toast2.show();
	              // }
	               
	               
	               
	            }
	           
	        });
	        
	        
	        
	 }
	

}
