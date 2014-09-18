package com.example.group51androidprojectchess;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SaveGameActivity extends Activity {

	EditText fileName;
	String log;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_save_game);
		
		Bundle extras = getIntent().getExtras();
		
		log = extras.getString("log");
		String results = extras.getString("results");
		
		TextView top = (TextView)findViewById(R.id.results);
		
		top.setText(results);

		fileName = (EditText)findViewById(R.id.editText1);
		
		Button save = (Button)findViewById(R.id.Save);
		save.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				File file = new File(getFilesDir(),fileName.getText().toString()+".txt");
				if(!file.exists())
					try {
						file.createNewFile();
					} catch (IOException e1) {
						;
					}
				try {
					PrintWriter out = new PrintWriter(file);
					out.print(log);
					out.flush();out.close();
				} catch (IOException e) {
					System.out.println("Cannot write to file.");
				}
				
				Intent toMain = new Intent(SaveGameActivity.this,MainActivity.class);
				startActivity(toMain);

			}

		});
		
		Button exit = (Button)findViewById(R.id.exit);
		exit.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent toMain = new Intent(SaveGameActivity.this,MainActivity.class);
				startActivity(toMain);
				
			}
			
		});

		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.save_game, menu);
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
			View rootView = inflater.inflate(R.layout.fragment_save_game,
					container, false);
			return rootView;
		}
	}

}
