package com.taxiride;

import android.os.Bundle;
import android.widget.TextView;
import com.taxiride.MyOrder;

public class MyOrderInfo extends LoggingActivity{
	public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
		 
		 setContentView(R.layout.myorderinfo);
		 TextView requestName = (TextView) findViewById(R.id.RequestName);
			requestName.setText("Request Name: " + MyOrder.ORDER.getRequestName());
			
			TextView requestPhone = (TextView) findViewById(R.id.RequestPhone);  
			requestPhone.setText("Request Phone: " + MyOrder.ORDER.getRequestPhoneNumber());
			
			TextView requestLocation = (TextView) findViewById(R.id.fromAddress);
			requestLocation.setText("PickUp Location: " + MyOrder.ORDER.getRequestPickupLocation());
			
			TextView destination = (TextView) findViewById(R.id.toAddress);
			destination.setText("Destination: " + MyOrder.ORDER.getRequestDestination());
			
			TextView totalPass = (TextView) findViewById(R.id.totalPass);
			totalPass.setText("Total Passengers: " + MyOrder.ORDER.getTotalPeople());
			
			TextView status = (TextView) findViewById(R.id.status);
			
	}	 

}
