package com.taxiride;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class DriverRequest extends LoggingActivity {

	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.driverrequest);    
	        
	      Toast toast = Toast.makeText(getApplicationContext(),"to Address in driver request" + FindBy.passengerInfo.getToAddress() +" from address:" +FindBy.passengerInfo.getFromAddress(), BIND_AUTO_CREATE);
	        toast.show();
	        
	        Button enter = (Button) findViewById(R.id.ButtonEnter);
	        
	        enter.setOnClickListener(new View.OnClickListener() {
	        	public void onClick(View view) {
	                  Intent myIntent = new Intent(view.getContext(), RequestConfirmation.class);
	                  startActivityForResult(myIntent, 0);
	                  
	            }

	        });
		}
	        
	 }       
	
	
