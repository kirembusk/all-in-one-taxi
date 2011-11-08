package com.taxiride;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LuxorCab extends LoggingActivity{
	public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
		setContentView(R.layout.luxorcab);
		Button call = (Button) findViewById(R.id.call);
		
		call.setOnClickListener(new View.OnClickListener() {
			
		
       	public void onClick(View view) {
       		Intent dialIntent = new Intent(Intent.ACTION_DIAL, Uri
                       .parse("tel:4153333333"));
               startActivity(dialIntent);


           }

       });
	}
}
