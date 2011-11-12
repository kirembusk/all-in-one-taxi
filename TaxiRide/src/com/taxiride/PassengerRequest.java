package com.taxiride;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PassengerRequest extends LoggingActivity{

	
		 public void onCreate(Bundle savedInstanceState) {
		        super.onCreate(savedInstanceState);
		        setContentView(R.layout.passengerrequest);
		        TextView requestName = (TextView) findViewById(R.id.RequestName);
				requestName.setText("Request Name: " + ListOfRequest.TAXIREQUEST.getRequestName());
				
				TextView requestPhone = (TextView) findViewById(R.id.RequestPhone);  
				requestPhone.setText("Request Phone: " + ListOfRequest.TAXIREQUEST.getRequestPhoneNumber());
				
				TextView requestLocation = (TextView) findViewById(R.id.fromAddress);
				requestLocation.setText("PickUp Location: " + ListOfRequest.TAXIREQUEST.getRequestPickupLocation());
				
				TextView destination = (TextView) findViewById(R.id.toAddress);
				destination.setText("Destination: " + ListOfRequest.TAXIREQUEST.getRequestDestination());
				
				TextView totalPass = (TextView) findViewById(R.id.totalPass);
				totalPass.setText("Total Passengers: " + ListOfRequest.TAXIREQUEST.getTotalPeople());
				
				TextView status = (TextView) findViewById(R.id.status);
				
			//	if(ListOfRequest.TAXIREQUEST.getIsRequestTaken() == "Y"){
			//	status.setText("Status:  Close  ------- Assigned to Driver: " + ListOfRequest.TAXIREQUEST.getAssignedDriverLogin());// + ListOfRequest.TAXIREQUEST.getAssignedDriverLogin());
				 //Toast toast2 = Toast.makeText(getApplicationContext(),"String:  "+ ListOfRequest.TAXIREQUEST.getAssignedDriverLogin(), BIND_AUTO_CREATE);
                 //toast2.show();
			//	}
			//	else
			//	status.setText("Status:  Open  -------- Driver hasn't been assigned yet");
				//Button accept = (Button)findViewById(R.id.status);
				//accept
				
				
		 }
	
}
