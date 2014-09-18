package com.example.group51androidprojectchess;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

public class ReplayActivity extends Activity {
	Board BackBoard;
	int rank;
	int file;
	boolean move = false, whiteMove = true;
	int selected = Color.YELLOW;
	int white = R.color.whiteTile;
	int black = R.color.blackTile;
	int prev = 0;
	Pieces held=null, tempPiece = null;
	FrameLayout[][] board ;
	boolean canUndo = false;
	Pieces[][] undoBoard;
	int undoRow1, undoRow2, undoCol1, undoCol2;
	String log;
	int moveCount = -1;
	String[] moves;
	Button Next;
	TextView output;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_replay);
		
		Bundle extras = getIntent().getExtras();
		File file = (File) extras.get("file");

		String line = "";
		
		try {
			FileInputStream fin = new FileInputStream(file);
			line = convertStreamtoString(fin).trim();
			fin.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		moves = line.split(" ");
		
		TableLayout tv = (TableLayout)findViewById(R.id.rboard);
		
		FrameLayout[][] temp = {{(FrameLayout)findViewById(R.id.ra8),(FrameLayout)findViewById(R.id.rb8),(FrameLayout)findViewById(R.id.rc8),(FrameLayout)findViewById(R.id.rd8),(FrameLayout)findViewById(R.id.re8),(FrameLayout)findViewById(R.id.rf8),(FrameLayout)findViewById(R.id.rg8),(FrameLayout)findViewById(R.id.rh8)},
				{(FrameLayout)findViewById(R.id.ra7),(FrameLayout)findViewById(R.id.rb7),(FrameLayout)findViewById(R.id.rc7),(FrameLayout)findViewById(R.id.rd7),(FrameLayout)findViewById(R.id.re7),(FrameLayout)findViewById(R.id.rf7),(FrameLayout)findViewById(R.id.rg7),(FrameLayout)findViewById(R.id.rh7)},
				{(FrameLayout)findViewById(R.id.ra6),(FrameLayout)findViewById(R.id.rb6),(FrameLayout)findViewById(R.id.rc6),(FrameLayout)findViewById(R.id.rd6),(FrameLayout)findViewById(R.id.re6),(FrameLayout)findViewById(R.id.rf6),(FrameLayout)findViewById(R.id.rg6),(FrameLayout)findViewById(R.id.rh6)},
				{(FrameLayout)findViewById(R.id.ra5),(FrameLayout)findViewById(R.id.rb5),(FrameLayout)findViewById(R.id.rc5),(FrameLayout)findViewById(R.id.rd5),(FrameLayout)findViewById(R.id.re5),(FrameLayout)findViewById(R.id.rf5),(FrameLayout)findViewById(R.id.rg5),(FrameLayout)findViewById(R.id.rh5)},
				{(FrameLayout)findViewById(R.id.ra4),(FrameLayout)findViewById(R.id.rb4),(FrameLayout)findViewById(R.id.rc4),(FrameLayout)findViewById(R.id.rd4),(FrameLayout)findViewById(R.id.re4),(FrameLayout)findViewById(R.id.rf4),(FrameLayout)findViewById(R.id.rg4),(FrameLayout)findViewById(R.id.rh4)},
				{(FrameLayout)findViewById(R.id.ra3),(FrameLayout)findViewById(R.id.rb3),(FrameLayout)findViewById(R.id.rc3),(FrameLayout)findViewById(R.id.rd3),(FrameLayout)findViewById(R.id.re3),(FrameLayout)findViewById(R.id.rf3),(FrameLayout)findViewById(R.id.rg3),(FrameLayout)findViewById(R.id.rh3)},
				{(FrameLayout)findViewById(R.id.ra2),(FrameLayout)findViewById(R.id.rb2),(FrameLayout)findViewById(R.id.rc2),(FrameLayout)findViewById(R.id.rd2),(FrameLayout)findViewById(R.id.re2),(FrameLayout)findViewById(R.id.rf2),(FrameLayout)findViewById(R.id.rg2),(FrameLayout)findViewById(R.id.rh2)},
				{(FrameLayout)findViewById(R.id.ra1),(FrameLayout)findViewById(R.id.rb1),(FrameLayout)findViewById(R.id.rc1),(FrameLayout)findViewById(R.id.rd1),(FrameLayout)findViewById(R.id.re1),(FrameLayout)findViewById(R.id.rf1),(FrameLayout)findViewById(R.id.rg1),(FrameLayout)findViewById(R.id.rh1)}
		};
		
		board = temp;
		BackBoard = new Board(); BackBoard.makeBoard(); updateBoard();
		
		Button End = (Button)findViewById(R.id.toReplayList);
		End.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				finish();
			}
		});

		output = (TextView)findViewById(R.id.moveReadOut);
		output.setText("");
		
		Next = (Button)findViewById(R.id.NextMove);
		Next.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(moveCount==moves.length-1)
					Toast.makeText(getApplicationContext(), "End of game.", Toast.LENGTH_LONG).show();
				else{
					moveCount++;
					String move = moves[moveCount];
					int fromRow = Integer.parseInt(move.substring(0, 1));
					int fromCol = Integer.parseInt(move.substring(1, 2));
					int toRow = Integer.parseInt(move.substring(2, 3));
					int toCol = Integer.parseInt(move.substring(3, 4));
					BackBoard.gameBoard[fromRow][fromCol].movePiece(fromRow, fromCol, toRow, toCol);
					String out = convertCol(fromCol)+""+(8-fromRow)+" to "+convertCol(toCol)+""+(8-toRow);
					output.setText(out);
					
					updateBoard();
				}
			}
		});
		
		
	/*	if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}*/
	}
	
	public static String convertStreamtoString(InputStream is) throws Exception{
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = null;
		while((line = reader.readLine()) != null){
			sb.append(line).append("\n");
		}
		reader.close();
		return sb.toString();
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.replay, menu);
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
			View rootView = inflater.inflate(R.layout.fragment_replay,
					container, false);
			return rootView;
		}
	}
	
	public void updateBoard(){
		
		
		BackBoard.fillBoard();
		
		for(int i=0; i<8; i++){
			for(int j=0; j<8; j++){
				board[i][j].setForegroundGravity(Gravity.CENTER);
				if (BackBoard.gameBoard[i][j].toString().equalsIgnoreCase("   "))
					board[i][j].setForeground(getResources().getDrawable(R.drawable.trasnsparent));
				if (BackBoard.gameBoard[i][j].toString().equalsIgnoreCase("## "))
					board[i][j].setForeground(getResources().getDrawable(R.drawable.trasnsparent));
				if (BackBoard.gameBoard[i][j].toString().equalsIgnoreCase("bp ")){
					board[i][j].setForeground(getResources().getDrawable(R.drawable.bpawn));
				}
				if (BackBoard.gameBoard[i][j].toString().equalsIgnoreCase("bk ")){
					board[i][j].setForeground(getResources().getDrawable(R.drawable.bqueen));
				}
				if (BackBoard.gameBoard[i][j].toString().equalsIgnoreCase("bn ")){
					board[i][j].setForeground(getResources().getDrawable(R.drawable.bknight));
				}
				if (BackBoard.gameBoard[i][j].toString().equalsIgnoreCase("bb ")){
					board[i][j].setForeground(getResources().getDrawable(R.drawable.bbish));
				}
				if (BackBoard.gameBoard[i][j].toString().equalsIgnoreCase("bq ")){
					board[i][j].setForeground(getResources().getDrawable(R.drawable.bking));
				}
				if (BackBoard.gameBoard[i][j].toString().equalsIgnoreCase("br ")){
					board[i][j].setForeground(getResources().getDrawable(R.drawable.brook));
				}
				if (BackBoard.gameBoard[i][j].toString().equalsIgnoreCase("wp ")){
					board[i][j].setForeground(getResources().getDrawable(R.drawable.wpawn));
				}
				if (BackBoard.gameBoard[i][j].toString().equalsIgnoreCase("wk ")){
					board[i][j].setForeground(getResources().getDrawable(R.drawable.wqueen));
				}
				if (BackBoard.gameBoard[i][j].toString().equalsIgnoreCase("wn ")){
					board[i][j].setForeground(getResources().getDrawable(R.drawable.wknight));
				}
				if (BackBoard.gameBoard[i][j].toString().equalsIgnoreCase("wb ")){
					board[i][j].setForeground(getResources().getDrawable(R.drawable.wbish));
				}
				if (BackBoard.gameBoard[i][j].toString().equalsIgnoreCase("wq ")){
					board[i][j].setForeground(getResources().getDrawable(R.drawable.wking));
				}
				if (BackBoard.gameBoard[i][j].toString().equalsIgnoreCase("wr ")){
					board[i][j].setForeground(getResources().getDrawable(R.drawable.wrook));
				}
			}
		}
	}
	
	public char convertCol(int col){
		switch (col){
			case 0: return 'a';
			case 1: return 'b';
			case 2: return 'c';
			case 3: return 'd';
			case 4: return 'e';
			case 5: return 'f';
			case 6: return 'g';
			case 7: return 'h';
			default: return 'z';
		}
	}
	
}
