package com.example.group51androidprojectchess;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Button startButton = (Button)findViewById(R.id.StartButton);		//This button starts the game
		Button recordButton = (Button)findViewById(R.id.RecordButton);		//This button manages recording games
		Button playbackButton = (Button)findViewById(R.id.PlaybackButton);	//This button plays games back
		
		//Implement code to start game here
		startButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent startGameIntent = new Intent(MainActivity.this, ChessGameActivity.class);
				startActivity(startGameIntent);
			}
			
		});

		//Implement code to manage recordings of games
		recordButton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				Intent listGamesIntent = new Intent(MainActivity.this, GameListActivity.class);
				startActivity(listGamesIntent);
				//Implement code to manage recordings of games
				
			}
			
		});
		
		//Implement code to play games back
		playbackButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent listGamesIntent = new Intent(MainActivity.this, GameListActivity.class);
				startActivity(listGamesIntent);
				//Implement code to play games back
				
			}
			
		});
		/*
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}*/
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

}
