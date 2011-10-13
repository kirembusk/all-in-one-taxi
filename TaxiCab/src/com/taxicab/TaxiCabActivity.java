package com.taxicab;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class TaxiCabActivity extends LoggingActivity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //select the main.xml for layout
        setContentView(R.layout.main);
        // get the RequestDriver button in main.xml
        Button driver = (Button) findViewById(R.id.RequestDriver);
        
        //if the RequestDriver buttton is click direct the page to Address.class
        driver.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), Address.class);
                startActivityForResult(myIntent, 0);
            }

        });
        
      
    }
    
}