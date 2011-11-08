package com.taxiride;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.Drawable;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;
import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class OverLay extends ItemizedOverlay<OverlayItem> {
	private GeoPoint pointToDraw;
	private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();
	Context mContext;
	
	public OverLay(Drawable defaultMarker, Context context) {  
		super(boundCenterBottom(defaultMarker));
		mContext = context;
		populate(); 
		}
	public void addItem(GeoPoint p, String title, String snippet){
		OverlayItem newItem = new OverlayItem(p, title, snippet);
		mOverlays.add(newItem);
		   populate();
		}
	public void draw(Canvas canvas, MapView mapView, boolean shadow) {
		// TODO Auto-generated method stub
		super.draw(canvas, mapView, shadow);
		//boundCenterBottom(marker);
		}
	
	public void setPointToDraw(GeoPoint point) {
	    pointToDraw = point;
	  }
	
	public GeoPoint getPointToDraw() {
	    return pointToDraw;
	  }
	
	
	
	

	protected boolean onTap(int index) {  
		OverlayItem item = mOverlays.get(index);  
		AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);  
		dialog.setTitle(item.getTitle());  
		dialog.setMessage(item.getSnippet());  
		dialog.show();  
		return true;
	}
	
	
	public OverLay(Drawable drawable) {
		super(boundCenterBottom(drawable));
		populate();
		// = mapView.getContext();
		// TODO Auto-generated constructor stub
	}
	
	
	public void addOverlay(OverlayItem overlay) {    
		mOverlays.add(overlay);    
		populate();
	}
	

	@Override
	protected OverlayItem createItem(int arg0) {
		// TODO Auto-generated method stub
		return mOverlays.get(arg0);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return mOverlays.size();
	}

}
