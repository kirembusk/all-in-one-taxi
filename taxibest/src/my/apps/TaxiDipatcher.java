package my.apps;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import joptsimple.OptionParser;
import joptsimple.OptionSet;
import share.utils.StringUtils;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;






import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.core.client.JsArray;


import com.google.gwt.http.client.*;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import my.beans.TaxiDriver;


public class TaxiDipatcher {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String lat = "";
		String lon = "";
		String id = "";
		String action = "";

		String usage = "USAGE: java -jar TaxiDriverLocationUpdate\n";
		usage += "a - [action]\n";
		usage += "i - [id]\n";
		usage += "x - [latitude]\n";
		usage += "y - [longtitude]\n";


		OptionParser parser = new OptionParser( "a:i:x:y:?" );

		OptionSet options = parser.parse( args );

		if (options.has("?"))
		{
			System.out.println(usage);
			System.exit(0);
		}


		if (options.has("a"))
		{
			action = StringUtils.getTrimWhiteSpaces((String) options.valueOf("a")); 
		}

		if (options.has("i"))
		{
			id = StringUtils.getTrimWhiteSpaces((String) options.valueOf("i")); 
		}

		if (options.has("x"))
		{
			lat = StringUtils.getTrimWhiteSpaces((String) options.valueOf("x")); 
		}

		if (options.has("y"))
		{
			lon = StringUtils.getTrimWhiteSpaces((String) options.valueOf("y")); 
		}




		String myURL = "http://localhost:8080/taxibest/TaxiStationController?action=json";


		
		StringBuilder taxiStationResponse = new StringBuilder();
		StringBuffer jb = new StringBuffer();
		Gson gson = new Gson();
		String taxiDriverData = "";

		try {
			// Construct data
			String data = URLEncoder.encode("action", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8");
			data += "&" + URLEncoder.encode("lat", "UTF-8") + "=" + URLEncoder.encode(lat, "UTF-8");
			data += "&" + URLEncoder.encode("lon", "UTF-8") + "=" + URLEncoder.encode(lon, "UTF-8");
			data += "&" + URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8");

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

	
		taxiDriverData = taxiStationResponse.toString();
		System.out.println(taxiDriverData);
		
		Type type = new TypeToken<List<TaxiDriver>>(){}.getType();
		List<TaxiDriver> taxiDriverList = gson.fromJson(taxiDriverData, type);

		for (TaxiDriver taxiDriver : taxiDriverList) {
			System.out.println("taxiDriver.getName() = " + taxiDriver.getName());
		}

		System.exit(0);
	}




}

