package com.taxiride;

import java.text.DecimalFormat;

import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class YellowCab extends LoggingActivity{
	
	public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
		setContentView(R.layout.yellowcab);
	    double distance;  
	      
	    //Toast toast = Toast.makeText(getApplicationContext(),"from Address: " + Address.FROMADDRESS + " To address: "+ Address.TOADDRESS, BIND_AUTO_CREATE);
       // toast.show();
        
	   /* Location locationA = new Location("point A");  
	      
	    locationA.setLatitude(FindBy.passengerInfo.getFromLat());  
	    locationA.setLongitude(FindBy.passengerInfo.getFromlog());  
	      
	    Location locationB = new Location("point B");  
	      
	    locationB.setLatitude(FindBy.passengerInfo.getToLat());  
	    locationB.setLongitude(FindBy.passengerInfo.getToLog());*/
	    float[] results = {0};
	    Location distanceBetween = new Location ("point a to b");
	    distanceBetween.distanceBetween(FindBy.passengerInfo.getFromLat(), FindBy.passengerInfo.getFromlog(), FindBy.passengerInfo.getToLat(), FindBy.passengerInfo.getToLog(), results);
	    
	    
	    double result = (float)(results[0]);
	    result = result * 0.000621371192;
	    DecimalFormat newFormat = new DecimalFormat("#.#");
	    double convertMiles = Double.valueOf(newFormat.format(result));
	    
	    
	 //   distance = locationA.distanceTo(locationB);  
	  //  double miles = (distance * 0.000621371192) ;
	   // DecimalFormat newFormat = new DecimalFormat("#.#");
	    //double convertMiles =  Double.valueOf(newFormat.format(miles));
	    TextView output = (TextView) findViewById(R.id.output);
	    output.setText("From Address: " + FindBy.passengerInfo.getFromAddress()+ "\n" + 
	    		" To Address: " + FindBy.passengerInfo.getToAddress() + "\n" + 
	    		" From Lat and Log: " + FindBy.passengerInfo.getFromLat() + " " 
	    		+ FindBy.passengerInfo.getFromlog() + "\n" + 
	    		" To Lat and Log: " + FindBy.passengerInfo.getToLat() + " " +
	    		FindBy.passengerInfo.getToLog()
	    		+ "\n" + " Distance: " + FindBy.passengerInfo.getDistance());
	    
	    
	   // Toast toast = Toast.makeText(getApplicationContext(),"from Address:11 " + FindBy.passengerInfo.getFromAddress() + " To address: "+ FindBy.passengerInfo.getToAddress()  + " convert distance" + result, 10000000);
	     //  toast.show();
		
		Button call = (Button) findViewById(R.id.call);
		call.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View view) {
        		Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri
                        .parse("tel:4152824141"));
                startActivity(dialIntent);


            }

        });
		
	}

}
