package com.grace.googleMap;

import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

public class YellowCab extends LoggingActivity{
	
	public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
		setContentView(R.layout.yellowcab);
	    double distance;  
	      
	    Toast toast = Toast.makeText(getApplicationContext(),"from Address: " + Address.FROMADDRESS + " To address: "+ Address.TOADDRESS, BIND_AUTO_CREATE);
        toast.show();
        
	   /* Location locationA = new Location("point A");  
	      
	    locationA.setLatitude(latA);  
	    locationA.setLongitude(lngA);  
	      
	    Location locationB = new Location("point B");  
	      
	    locationB.setLatitude(latB);  
	    LocationB.setLongitude(lngB);*/
	    
	      
	   // distance = locationA.distanceTo(locationB);  
	    
		
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
