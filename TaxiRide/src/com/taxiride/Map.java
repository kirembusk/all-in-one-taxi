package com.taxiride;

import android.os.Bundle;
import android.widget.RelativeLayout;

import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;

public class Map extends LoggingActivity{
	
	
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.map);
		
		RelativeLayout linearLayout = (RelativeLayout)findViewById(R.id.mainlayout); 
		MapView mapView = (MapView) findViewById(R.id.mapview); 
		   
		mapView.setBuiltInZoomControls(true);
		
}

//	@Override
//	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
	//	return false;
	//}
	
}