package com.taxiride;

import java.io.BufferedReader;
import com.google.android.maps.MapController;

import java.util.List;
 
import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.BufferedReader;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
 
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.Toast;
/*
 * This is the main activity for the taxi ride app. This class include two buttons
 * name Find a passenger and Find a Driver 
 */
public class TaxiRideActivity extends Activity {
    /** Called when the activity is first created. */
  
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //select the main.xml for layout
        setContentView(R.layout.main);
        // get the RequestDriver button in main.xml
        ImageView image = (ImageView) findViewById(R.id.test_image);
        
        
        Button driver = (Button) findViewById(R.id.RequestDriver);  
        //if the RequestDriver button is click direct the page to Address.class
        driver.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	SharedPreferences prefs2 = getSharedPreferences("PassengerPreference", Context.MODE_PRIVATE);
            	boolean haveWeShownPreferences = prefs2.getBoolean("HaveShownPrefs", false);
            	// if the passenger perferences has not been shown before, go to the passenger prference class
            	// otherwise go to the address class
            	if(!haveWeShownPreferences){
                Intent myIntent = new Intent(view.getContext(),PassengerPreference.class);
                startActivityForResult(myIntent, 0);
            	}else{
            		Intent myIntent = new Intent(view.getContext(),Address.class);
                    startActivityForResult(myIntent, 0);
            	  }
            	 
            }

        });
        // this is the button to request passenger
        Button passenger = (Button) findViewById(R.id.RequestPassenger);  
        //if the RequestDriver button is click direct the page to Address.class
        passenger.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	
            	SharedPreferences prefs = getSharedPreferences("DriverPreference", Context.MODE_PRIVATE);
            	boolean haveWeShownPreferences = prefs.getBoolean("HaveShownPrefs", false);
            	// if driver preferences has not been shown before go to driver preferences class
            	if(!haveWeShownPreferences){
                Intent myIntent = new Intent(view.getContext(),DriverPreference.class);
                startActivityForResult(myIntent, 0);
            	}else{
            		// if driver preference is shown before then go to driver window
            		Intent myIntent = new Intent(view.getContext(),DriverWindow.class);
                    startActivityForResult(myIntent, 0);
            	  }
            	} 
        });
         
    }
    }
