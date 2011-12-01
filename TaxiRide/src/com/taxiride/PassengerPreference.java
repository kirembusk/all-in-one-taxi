package com.taxiride;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.provider.Settings.Secure;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class PassengerPreference extends PreferenceActivity  implements OnSharedPreferenceChangeListener{
	
	private SharedPreferences sp2; 
	private SharedPreferences prefs2; 
	private SharedPreferences.Editor ed2; 
	private boolean nameFlag = false; 
	private boolean phoneFlag = false;
	private boolean paymentFlag = false; 
	private boolean showPrefs = false; 
	private String showPref;

	protected void onCreate(Bundle savedInstanceState) {
         super.onCreate(savedInstanceState);
         
         addPreferencesFromResource(R.xml.passenger_preference);
         
          sp2 = PreferenceManager.getDefaultSharedPreferences(this);
          sp2.registerOnSharedPreferenceChangeListener(this);
         
          prefs2 = getSharedPreferences("PassengerPreference", Context.MODE_PRIVATE);
          
          ed2 = prefs2.edit();
         
         //ed2.putBoolean("HaveShownPrefs", true);
         //ed2.commit();
         String androidID = Secure.getString(getBaseContext().getContentResolver(),
                 Secure.ANDROID_ID); 
              showPrefs = prefs2.getBoolean("HaveShownPrefs",false);
	          showPref = new Boolean(showPrefs).toString(); 
	    	
         
	 }   
	
	  @Override
		    public boolean onCreateOptionsMenu(Menu menu) {
	  	        menu.add(Menu.NONE, 0, 0, "Save");
	  	        return super.onCreateOptionsMenu(menu);
	  	    }
	  	 
	  	    @Override
	  	    public boolean onOptionsItemSelected(MenuItem item) {
	  	    	
	  	    	
	  	    	if(showPref.equals("false")){
	  	        switch (item.getItemId()) {
	  	            case 0:
	  	            	
	  	            	 ed2.putBoolean("HaveShownPrefs", true);
	  		  	         ed2.commit();
	  	                startActivity(new Intent(this, FindBy.class));
	  	                return true;
	  	        	}
	  	        }else{
	  	        switch (item.getItemId()) {
	  	            case 0:
	  	            	
	  	            	Intent myIntent = new Intent(this,FindBy.class);
	                    startActivityForResult(myIntent, 0);
	  	                return true;
	  	                
	  	           }
	  	        
	  	    	 }
	  	    	//Toast.makeText(getApplicationContext(), "Please enter all the information", 100).show(); 
	  	       return false; 
	  	    }

			@Override
			public void onSharedPreferenceChanged(SharedPreferences sp,
					String key) {
				
				 if (key.equals("editFullName")) {
					 String value = sp.getString(key, null);
					 ed2.putString("editFullName", value);
					 ed2.commit();
					 
				 }
				 if (key.equals("editPhoneNum")) {
					 String value = sp.getString(key, null);
					 ed2.putString("editPhoneNum", value);
					 ed2.commit();
					
				 }
				 if (key.equals("listPref")) {
					 String value = sp.getString(key, null);
					 ed2.putString("listPref", value);
					 ed2.commit();
					
				 }
				 Preference pref = findPreference(key);
				    if (pref instanceof EditTextPreference) {
				        EditTextPreference etp = (EditTextPreference) pref;
				        
				        pref.setSummary(etp.getText());
				    }
					 
				// TODO Auto-generated method stub
				
			}
}
