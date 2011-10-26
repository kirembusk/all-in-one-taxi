package com.grace.googleMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FindBy extends LoggingActivity {
	private String toAddress;
	private String fromAddress;
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.findby);

        Bundle bundle = getIntent().getExtras();
        
         toAddress = bundle.getString("toAddress"); 
         fromAddress = bundle.getString("fromAddress");
         
       
        Toast toast = Toast.makeText(getApplicationContext(),"from Address: " + fromAddress + " To address: "+ toAddress, BIND_AUTO_CREATE);
        toast.show();
       
        
        Button cabDir = (Button) findViewById(R.id.ButtonCabDir);
        
        cabDir.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View view) {
        		Bundle bundle2 = new Bundle();
        		bundle2.putString("toAddress", toAddress);
        		bundle2.putString("fromAddress", fromAddress);
                  Intent myIntent2 = new Intent(view.getContext(),CabDirectory.class);
                  myIntent2.putExtras(bundle2);
                  startActivityForResult(myIntent2, 0);
            }
        });
        
        Button driverDir = (Button) findViewById(R.id.ButtonDriverDir);
        
        driverDir.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View view) {
        		Bundle bundle2 = new Bundle();
        		bundle2.putString("toAddress", toAddress);
        		bundle2.putString("fromAddress", fromAddress);
                  Intent myIntent = new Intent(view.getContext(), Map.class);
                  myIntent.putExtras(bundle2);
                  startActivityForResult(myIntent, 0);
            }

        });
	}
}
	
