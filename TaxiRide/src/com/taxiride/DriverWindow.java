package com.taxiride;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DriverWindow extends LoggingActivity {
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //select the main.xml for layout
        
        setContentView(R.layout.driverwindow);
        Button openRequest = (Button) findViewById(R.id.OpenRequest);  
        //if the RequestDriver button is click direct the page to Address.class
        
        openRequest.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), ListOfRequest.class);
                startActivityForResult(myIntent, 0);
            }

        });
        
        Button myOrder = (Button) findViewById(R.id.MyOrder);  
        //if the RequestDriver button is click direct the page to Address.class
        myOrder.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(),MyOrder.class);
                startActivityForResult(myIntent, 0);
            }
           
        });        
        
	} 

}
