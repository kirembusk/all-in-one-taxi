package com.taxiride;

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
	        
	    //  Toast toast = Toast.makeText(getApplicationContext(),"to Address in driver request" + FindBy.passengerInfo.getToAddress() +" from address:" +FindBy.passengerInfo.getFromAddress(), BIND_AUTO_CREATE);
	      //  toast.show();
	        
	        final EditText fromAddress = (EditText) findViewById(R.id.editFromAddress);
	        fromAddress.setText(FindBy.passengerInfo.getFromAddress());
	        final EditText toAddress = (EditText) findViewById(R.id.editToAddress);
	        toAddress.setText(FindBy.passengerInfo.getToAddress());
	        
	        FindBy.passengerInfo.setFromAddress(fromAddress.getText().toString());
	        FindBy.passengerInfo.setToAddress(toAddress.getText().toString());
	        
	        Button enter = (Button) findViewById(R.id.ButtonEnter);
	        
	        enter.setOnClickListener(new View.OnClickListener() {
	        	public void onClick(View view) {
	                  Intent myIntent = new Intent(view.getContext(), RequestConfirmation.class);
	                  startActivityForResult(myIntent, 0);
	                  
	            }

	        });
		}
	        
	 }       
	
	
