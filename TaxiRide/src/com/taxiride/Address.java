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
/*
 * This class is for user to enter the address. If the user decides to turn
 * on the GPS coordinate to find their current location, the user does
 * not need to change the text at the edittext "current location".
 * The addresses are save as a global varaible to pass by to the FindBy.java
 */


public class Address extends LoggingActivity{
	private static int calls = 0;
	public static String ToAddress;
	public static String FromAddress;
	private double lat;
	private double log; 
	private String addressString =""; 

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
        
  
        	
        	
      //if the button called enter is click direct to page FindBy.class
        enter.setOnClickListener(new View.OnClickListener() {
        	
        	public void onClick(View view) {
        	
        		Bundle bundle = new Bundle();
                ToAddress = toAddress.getText().toString();
                FromAddress = fromAddress.getText().toString();
               
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
