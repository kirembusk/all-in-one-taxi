package com.taxiride;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.taxiride.model.TaxiRequest;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

/*
 * This is my order class. It shows the list of orders that the driver
 * has confirmed. This class used list. 
 * 
 */
public class MyOrder extends ListActivity{
	
	ArrayList<String> listItems=new ArrayList<String>();
	ArrayAdapter<String> adapter;
	private List<TaxiRequest> taxiRequestList;
	public static TaxiRequest ORDER;
	
	
	
	public void onCreate(Bundle savedInstanceState) {
		
		 super.onCreate(savedInstanceState);
		 // get list of object back from server
		 String myURL = "http://taxitestcenter.appspot.com/order";
		 
		 StringBuilder taxiStationResponse = new StringBuilder();
		 StringBuffer jb = new StringBuffer();
		 Gson gson = new Gson();
			String taxiRequestData = "";
			int counter = 0;
			try {
				// Construct data
				  String data = URLEncoder.encode("action", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8");
				   data += "&" + URLEncoder.encode("loginID", "UTF-8") + "=" + URLEncoder.encode(DriverWindow.driverInfo.getDeviceID(), "UTF-8");
				   
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
		 
		 
		if(taxiRequestData.length()!=0){
			Type type = new TypeToken<List<TaxiRequest>>(){}.getType();
	         taxiRequestList = gson.fromJson(taxiRequestData, type);
			 
	         // get the passenger name and save it into a list
	         for (TaxiRequest taxiRequest : taxiRequestList) {
	        	 
				 listItems.add(taxiRequest.getRequestName());
				
			 }	
		} 
		// display the list.
		 adapter=new ArrayAdapter<String>(this,
				    android.R.layout.simple_list_item_1,
				    listItems);
			
				setListAdapter(adapter);
			
		 
	} 
	
	// start a new order into myorderinfo class
	protected void onListItemClick(ListView l, View v, int position, long id) {
		ORDER = taxiRequestList.get(position);
		Intent myIntent = new Intent(v.getContext(), MyOrderInfo.class);
		 startActivityForResult(myIntent, 0);
		
	}

}
