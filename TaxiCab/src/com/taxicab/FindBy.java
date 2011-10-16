package com.taxicab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FindBy extends LoggingActivity {
	
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.findby);

        Bundle bundle = getIntent().getExtras();
         String toAddress = bundle.getString("toAddress"); 
         String fromAddress = bundle.getString("fromAddress");
         
       
        Toast toast = Toast.makeText(getApplicationContext(), fromAddress, BIND_AUTO_CREATE);
        toast.show();
        
     //   Button enter = (Button) findViewById(R.id.ButtonEnter);
        
      /*  enter.setOnClickListener(new View.OnClickListener() {
        	public void onClick(View view) {
                Intent intent = new Intent();
                setResult(RESULT_OK, intent);
                finish();
            }

        });*/
	}
}
	
