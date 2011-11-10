package com.taxiride;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LogInPage extends Activity  {
	
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        //select the log in page for layout
	        setContentView(R.layout.loginpage);
	        
	        
	        Button enter = (Button) findViewById(R.id.ButtonEnter); 
	       enter.setOnClickListener(new View.OnClickListener() {
	            public void onClick(View view) {
	                 Intent myIntent = new Intent(view.getContext(),ListOfRequest.class);
	                startActivityForResult(myIntent, 0);
	            	
	            }
	           
	        });
	        
	        
	        
	 }
	

}
