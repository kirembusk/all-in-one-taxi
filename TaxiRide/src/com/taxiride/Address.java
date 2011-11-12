package com.taxiride;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import com.taxiride.FindBy.GPSLocationListener;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.location.Geocoder;
import android.widget.LinearLayout;
import android.widget.Toast;
//firstnameandlastname+ date and time+mms


public class Address extends LoggingActivity{
	public static PassengerInfo passengerInfo = new PassengerInfo();
	private static int calls = 0;
	public static String TOADDRESS;
	public static String FROMADDRESS;
	private GPSLocationListner gpsLocation= new GPSLocationListner();
	private double lat;
	private double log; 
	private String addressString = ""; 
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set the content view to address.xml
        setContentView(R.layout.address);
        
        // get the button called ButtonEnter. it's a enter button after the users 
        // has enter the address.
        Button enter = (Button) findViewById(R.id.ButtonEnter);
        final EditText toAddress = (EditText) findViewById(R.id.editToAddress);
        final EditText fromAddress = (EditText) findViewById(R.id.editFromAddress);
        
        fromAddress.setText("Current Location"); 
        
   /*     LocationManager locationManager =(LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(
  	          LocationManager.GPS_PROVIDER, 
  	          5000, 
  	          500, 
  	         gpsLocation);
        
        	lat = gpsLocation.getLatitude();
        	log = gpsLocation.getLongtitude();
        	
        	 Toast toast = Toast.makeText(getApplicationContext(),"from lat: " + passengerInfo.getFromLat() + "from log: " + log, BIND_AUTO_CREATE);
             toast.show();
        	
        	Geocoder gc = new Geocoder(this,Locale.getDefault());
        	try {
        	      List<android.location.Address> addresses = gc.getFromLocation(lat, log, 1);
        	      StringBuilder sb = new StringBuilder();
        	      if (addresses.size() > 0) {
        	        android.location.Address address = addresses.get(0);
        	        
        	        sb.append(address.getLocality()).append("\n");
        	        sb.append(address.getCountryName());
        	      }
        	      addressString = sb.toString();
        	    } catch (IOException e) {}
        	    
        	    fromAddress.setText(addressString);
        	  //  Toast toast = Toast.makeText(getApplicationContext(),"from Address: " + addressString, BIND_AUTO_CREATE);
              //  toast.show();*/
        	    
        	    
        	
        	
      //if the button called enter is click direct to page FindBy.class
        enter.setOnClickListener(new View.OnClickListener() {
        	
        	public void onClick(View view) {
        		
        	/*	passengerInfo.setToAddress(toAddress.getText().toString());
        		if(fromAddress.getText().toString()=="Current Location"){
        			passengerInfo.setEnableGPS(true);
        		}else{
        			passengerInfo.setFromAddress(fromAddress.getText().toString());
        		}*/
        		Bundle bundle = new Bundle();
                TOADDRESS = toAddress.getText().toString();
                FROMADDRESS = fromAddress.getText().toString();
                bundle.putString("toAddress", toAddress.getText().toString());
                bundle.putString("fromAddress", fromAddress.getText().toString());
        		 TOADDRESS = toAddress.getText().toString();
        		 FROMADDRESS = fromAddress.getText().toString();
        		 Intent myIntent = new Intent(view.getContext(), FindBy.class);
        		 myIntent.putExtras(bundle);
        		 startActivityForResult(myIntent, 0);
            }

        });
    }
 /*
    /**
     * You need to handle the BACK button explicitly. Pressing the BACK button
     * finishes the Activity and results have to be set BEFORE finishing the
     * Activity. So using lifecycle methods like onPause or onStop will not
     * work.
     */
 //   @Override
    public void onBackPressed() {
        storeNumberOfCalls();
        super.onBackPressed();
    }
 
    /**
     * Method onClick() will be called whenever the user presses the button in
     * the YellowActivity.
     */
    public void onClick(View v) {
        storeNumberOfCalls();
        /**
         * A call to finish() ends the calling activity and returns to the
         * previous one on the activity stack (in this case, BlueActivity).
         * Calling finish will trigger the calling of onActivityResult() in
         * BlueActivity returning the result that was set with setResult().
         */
        finish();
    }
 
    protected void storeNumberOfCalls() {
        /**
         * Use an Intent to store the result to be returned to the calling
         * activity.
         */
        Intent intent = new Intent();
        /**
         * The result (number of times YellowActivity has been called) will be
         * stored as "CALLS".
         */
        intent.putExtra("CALLS", ++calls);
        /**
         * A call to setResult() will not yet terminate YellowActivity nor call
         * BlueActivity.onActivityResult(). This only happens when calling
         * finish().
         */
        setResult(RESULT_OK, intent);
    }
	
	

}
