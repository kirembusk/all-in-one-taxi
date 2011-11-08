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
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class TaxiRideActivity extends Activity {
    /** Called when the activity is first created. */
  
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //select the main.xml for layout
        setContentView(R.layout.main);
        // get the RequestDriver button in main.xml
        
        Button driver = (Button) findViewById(R.id.RequestDriver);  
        //if the RequestDriver button is click direct the page to Address.class
        driver.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), Address.class);
                startActivityForResult(myIntent, 0);
            }

        });
        
        Button passenger = (Button) findViewById(R.id.RequestPassenger);  
        //if the RequestDriver button is click direct the page to Address.class
        passenger.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), LogInPage.class);
                startActivityForResult(myIntent, 0);
            }

        });
         
    }
    }
