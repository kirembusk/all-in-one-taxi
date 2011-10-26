package com.grace.googleMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class Address extends LoggingActivity{
	
	private static int calls = 0;
	public static String TOADDRESS;
	public static String FROMADDRESS;
	
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
        
        
      //if the button called enter is click direct to page FindBy.class
        enter.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View view) {
        		Bundle bundle = new Bundle();
        		TOADDRESS = toAddress.getText().toString();
        		FROMADDRESS = fromAddress.getText().toString();
        		bundle.putString("toAddress", toAddress.getText().toString());
        		bundle.putString("fromAddress", fromAddress.getText().toString());
        	
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
