package com.taxiride;

import java.io.BufferedReader;
import java.lang.reflect.Type;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.taxiride.model.TaxiRequest;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ListOfRequest extends ListActivity {
	
	ArrayList<String> listItems=new ArrayList<String>();
	ArrayAdapter<String> adapter;
	private int clickCounter=0;
	public static TaxiRequest TAXIREQUEST; 
	private List<TaxiRequest> taxiRequestList;
	
	public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
		 
		 String myURL = "http://taxitestcenter.appspot.com/list";
		 StringBuilder taxiStationResponse = new StringBuilder();
			StringBuffer jb = new StringBuffer();
			Gson gson = new Gson();
			String taxiRequestData = "";
			int counter = 0;
			try {
				// Construct data
				  String data = URLEncoder.encode("action", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8");
		
				 
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
		 
		 Type type = new TypeToken<List<TaxiRequest>>(){}.getType();
         taxiRequestList = gson.fromJson(taxiRequestData, type);
		 
         for (TaxiRequest taxiRequest : taxiRequestList) {
        	 
			 listItems.add(taxiRequest.getRequestName());
			
		 }
		
		adapter=new ArrayAdapter<String>(this,
			    android.R.layout.simple_list_item_1,
			    listItems);
		
			setListAdapter(adapter);
			final ListView listView = getListView();

	        listView.setItemsCanFocus(false);
	        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

		
	}

	protected void onListItemClick(ListView l, View v, int position, long id) {
		TAXIREQUEST = taxiRequestList.get(position);
		
		Intent myIntent = new Intent(v.getContext(), PassengerRequest.class);
		 startActivityForResult(myIntent, 0);
		
	}
}