package com.taxiride;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RequestConfirmation extends LoggingActivity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.requestconfirmation);
		
		TextView fromAddress = (TextView) findViewById(R.id.fromAddress);
		fromAddress.setText("From: " + Address.FROMADDRESS);
		
		TextView toAddress = (TextView) findViewById(R.id.toAddress);
		toAddress.setText("To: " + Address.TOADDRESS);
		
		Button refresh = (Button) findViewById(R.id.refresh);
		
		refresh.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View view) {
                    
        		
        		
            }

        });
	}
		
	
}
