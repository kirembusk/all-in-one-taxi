package com.taxiride;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ListOfRequest extends ListActivity {
	
	ArrayList<String> listItems=new ArrayList<String>();
	ArrayAdapter<String> adapter;
	
	public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
		// setContentView(R.layout.listofrequest);
		

		adapter=new ArrayAdapter<String>(this,
			    android.R.layout.simple_list_item_single_choice,
			    listItems);
			setListAdapter(adapter);
			
			for(int i =0; i<3;i++){
				listItems.add("Driver 1: Accept " );
				 adapter.notifyDataSetChanged();
				}
			final ListView listView = getListView();

	        listView.setItemsCanFocus(false);
	        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

		
	}
	
	protected void onListItemClick(ListView l, View v, int position, long id) {
	//	Intent myIntent = new Intent(v.getContext(), YellowCab.class);
	//	 startActivityForResult(myIntent, 0);
		
	}
}