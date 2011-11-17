package com.taxiride;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.provider.Settings.Secure;
import android.view.Menu;
import android.view.MenuItem;


public class PassengerPreference extends PreferenceActivity  implements OnSharedPreferenceChangeListener{
	 
	private boolean flag = false;
	
	protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         
         addPreferencesFromResource(R.xml.passenger_preference);
        /* SharedPreferences sp2 = PreferenceManager.getDefaultSharedPreferences(this);
         sp2.registerOnSharedPreferenceChangeListener(this);
         SharedPreferences prefs2 = getSharedPreferences("PassengerPreference", Context.MODE_PRIVATE);
         SharedPreferences.Editor ed2 = prefs2.edit();
         ed2.putBoolean("HaveShownPrefs", true);
         ed2.commit();*/
         String androidID = Secure.getString(getBaseContext().getContentResolver(),
                 Secure.ANDROID_ID); 
         
	 }   
	
	  @Override
		    public boolean onCreateOptionsMenu(Menu menu) {
	  	        menu.add(Menu.NONE, 0, 0, "Save");
	  	        return super.onCreateOptionsMenu(menu);
	  	    }
	  	 
	  	    @Override
	  	    public boolean onOptionsItemSelected(MenuItem item) {
	  	    
	  	    	if(flag == true){
	  	        switch (item.getItemId()) {
	  	            case 0:
	  	                startActivity(new Intent(this, Address.class));
	  	                return true;
	  	        }
	  	      }
	  	    	
	  	        return false;
	  	    }

			@Override
			public void onSharedPreferenceChanged(SharedPreferences sp,
					String key) {
				 if (key.equals("editFirstName")) {
					 String value = sp.getString(key, null);
					 if(value == "")
						 flag = false;
					 else flag = true;
				 }
				 
					 
				// TODO Auto-generated method stub
				
			}
}
