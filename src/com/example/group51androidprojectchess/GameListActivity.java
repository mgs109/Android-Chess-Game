package com.example.group51androidprojectchess;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.app.Fragment;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class GameListActivity extends Activity {

	listItem[] items; 
	listItem[] sortByName;
	listItem[] sortByDate;
	ListView thisView;
	SimpleAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game_list);
		
		thisView = (ListView)findViewById(R.id.list);
		
		//Finds saved games in the folder
		File dir = new File(getFilesDir() + "");
		File[] toList = dir.listFiles();
		
		//Array of saved games info (holds name,date, and save file)
		//Essentially, this is what needs to be sorted
		items = new listItem[toList.length];
		sortByDate = new listItem[toList.length];
		//Fills the array of saved games info
		for(int i=0; i<toList.length; i++){
			items[i] = new listItem();
			sortByDate[i]=new listItem();
			items[i].name=toList[i].getName().substring(0, toList[i].getName().length()-4);
			sortByDate[i].name=toList[i].getName().substring(0, toList[i].getName().length()-4);
			Date temp = new Date(toList[i].lastModified());
			items[i].time = temp;
			items[i].save=toList[i];
			sortByDate[i].time = temp;
			sortByDate[i].save=toList[i];
		}
		
		sortByName = nameSort(items);
		//listItem[] sortByDate = new listItem[items.length];
				
		
		//Creates and fills a List of HashMaps to use to populate the ListView that displays save games
		List<HashMap<String,String>> fillMaps = makeList(sortByDate);
		
		/*
		 * Okay, so this is where it gets tricky.
		 * this - refers to the activity, not terribly important
		 * fillMaps - the list<HashMap> from before.
		 * R.Layout.list_row - the layout used to display info, nothing to worry about
		 * new String[]{"name","date"} - Okay, notice how each HashMap in the list has 2 key-value pairs. This specifies what keys to use to get the data.
		 * new int[]... - Specifies where to put the "name" and "date" data.
		 */
		adapter = new SimpleAdapter(GameListActivity.this,fillMaps, R.layout.list_row, new String[]{"name", "date"}, new int[]{R.id.text1,R.id.text2});
		thisView.setAdapter(adapter);
		
		thisView.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
					Toast.makeText(getApplicationContext(), items[position].name, Toast.LENGTH_LONG).show();
					Intent replayIntent = new Intent(GameListActivity.this, ReplayActivity.class);
					replayIntent.putExtra("file", items[position].save);
					startActivity(replayIntent);
			}
			
		});
		
		//Exits the activity
		Button backButton = (Button)findViewById(R.id.back);
		backButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
				// TODO Auto-generated method stub
				
			}
		});
		
		Button byDate = (Button)findViewById(R.id.sortByDate);
		byDate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				items = dateSort(items);
				List<HashMap<String,String>> dateMaps = makeList(items);
				adapter = new SimpleAdapter(GameListActivity.this,dateMaps, R.layout.list_row, new String[]{"name", "date"}, new int[]{R.id.text1,R.id.text2});
				adapter.notifyDataSetChanged();
				thisView.setAdapter(adapter);
			}
		});
		
		Button byName = (Button)findViewById(R.id.sortByName);
		byName.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				items = nameSort(items);
				List<HashMap<String,String>> nameMaps = makeList(items);
				adapter = new SimpleAdapter(GameListActivity.this,nameMaps, R.layout.list_row, new String[]{"name", "date"}, new int[]{R.id.text1,R.id.text2});
				adapter.notifyDataSetChanged();
				thisView.setAdapter(adapter);
			}
		});

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		
	}

	
	public List<HashMap<String,String>> makeList(listItem[] items){
		List<HashMap<String,String>> ret = new ArrayList<HashMap<String,String>>();
		//Populates the List of HashMaps with HashMaps
		for(int i = 0; i< items.length; i++){
			HashMap<String,String> map = new HashMap<String, String>();
			map.put("name", items[i].name);
			map.put("date", items[i].time.toString());
			ret.add(map);
		}
		return ret;
		
	}
	
	listItem[] nameSort(listItem[] items){
		listItem[] sortByName = items;
		if(sortByName.length == 1){
			return sortByName;
		}
		for(int i = 0; i < items.length; i++){
			listItem tempItem = items[i];
			int pos = i;
			for(int j = i; j < items.length; j++){
				
				if(items[j].name.compareTo(tempItem.name) < 0){
					tempItem = items[j];
					pos = j;
					
				}
				
					listItem tempi = items[i];
					items[i] = items[pos];
					items[pos] = tempi;
				
				sortByName[i] = items[i];
			}
			
		}
		return sortByName;
	}
	
	listItem[] dateSort(listItem[] items){
		listItem[] tempSort = items;
		if(tempSort.length == 1){
			return tempSort;
		}
		for(int i = 0; i < items.length; i++){
			listItem tempItem = items[i];
			int pos = i;
			for(int j = i; j < items.length; j++){
				
				if(!items[j].time.before(tempItem.time)){
					tempItem = items[j];
					pos = j;
					
				}
				
					listItem tempi = items[i];
					items[i] = items[pos];
					items[pos] = tempi;
				
				tempSort[i] = items[i];
			}
			
		}
		return tempSort;
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game_list, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_game_list,
					container, false);
			return rootView;
		}
	}

}

class listItem {
	public String name;
	public Date time;
	public File save;
}