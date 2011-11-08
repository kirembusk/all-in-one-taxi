package com.taxiride;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DriverRequest extends LoggingActivity {

	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.driverrequest);    
	        
	        Button enter = (Button) findViewById(R.id.ButtonEnter);
	        
	        enter.setOnClickListener(new View.OnClickListener() {
	        	public void onClick(View view) {
	                  Intent myIntent = new Intent(view.getContext(), RequestConfirmation.class);
	                  startActivityForResult(myIntent, 0);
	                  
	            }

	        });
		}
	        
	 }       
	
	
