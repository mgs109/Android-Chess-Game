package com.example.group51androidprojectchess;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TableLayout;
import android.widget.Toast;

public class ChessGameActivity extends ActionBarActivity {

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
	String results;
	
	
	Pieces undoPiece = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chess_game);
		
		TableLayout tv = (TableLayout)findViewById(R.id.board);
		
		FrameLayout[][] temp = {{(FrameLayout)findViewById(R.id.a8),(FrameLayout)findViewById(R.id.b8),(FrameLayout)findViewById(R.id.c8),(FrameLayout)findViewById(R.id.d8),(FrameLayout)findViewById(R.id.e8),(FrameLayout)findViewById(R.id.f8),(FrameLayout)findViewById(R.id.g8),(FrameLayout)findViewById(R.id.h8)},
				{(FrameLayout)findViewById(R.id.a7),(FrameLayout)findViewById(R.id.b7),(FrameLayout)findViewById(R.id.c7),(FrameLayout)findViewById(R.id.d7),(FrameLayout)findViewById(R.id.e7),(FrameLayout)findViewById(R.id.f7),(FrameLayout)findViewById(R.id.g7),(FrameLayout)findViewById(R.id.h7)},
				{(FrameLayout)findViewById(R.id.a6),(FrameLayout)findViewById(R.id.b6),(FrameLayout)findViewById(R.id.c6),(FrameLayout)findViewById(R.id.d6),(FrameLayout)findViewById(R.id.e6),(FrameLayout)findViewById(R.id.f6),(FrameLayout)findViewById(R.id.g6),(FrameLayout)findViewById(R.id.h6)},
				{(FrameLayout)findViewById(R.id.a5),(FrameLayout)findViewById(R.id.b5),(FrameLayout)findViewById(R.id.c5),(FrameLayout)findViewById(R.id.d5),(FrameLayout)findViewById(R.id.e5),(FrameLayout)findViewById(R.id.f5),(FrameLayout)findViewById(R.id.g5),(FrameLayout)findViewById(R.id.h5)},
				{(FrameLayout)findViewById(R.id.a4),(FrameLayout)findViewById(R.id.b4),(FrameLayout)findViewById(R.id.c4),(FrameLayout)findViewById(R.id.d4),(FrameLayout)findViewById(R.id.e4),(FrameLayout)findViewById(R.id.f4),(FrameLayout)findViewById(R.id.g4),(FrameLayout)findViewById(R.id.h4)},
				{(FrameLayout)findViewById(R.id.a3),(FrameLayout)findViewById(R.id.b3),(FrameLayout)findViewById(R.id.c3),(FrameLayout)findViewById(R.id.d3),(FrameLayout)findViewById(R.id.e3),(FrameLayout)findViewById(R.id.f3),(FrameLayout)findViewById(R.id.g3),(FrameLayout)findViewById(R.id.h3)},
				{(FrameLayout)findViewById(R.id.a2),(FrameLayout)findViewById(R.id.b2),(FrameLayout)findViewById(R.id.c2),(FrameLayout)findViewById(R.id.d2),(FrameLayout)findViewById(R.id.e2),(FrameLayout)findViewById(R.id.f2),(FrameLayout)findViewById(R.id.g2),(FrameLayout)findViewById(R.id.h2)},
				{(FrameLayout)findViewById(R.id.a1),(FrameLayout)findViewById(R.id.b1),(FrameLayout)findViewById(R.id.c1),(FrameLayout)findViewById(R.id.d1),(FrameLayout)findViewById(R.id.e1),(FrameLayout)findViewById(R.id.f1),(FrameLayout)findViewById(R.id.g1),(FrameLayout)findViewById(R.id.h1)}
		};
		
		board = temp;
		
		log = "";
		results = "";
		
		BackBoard = new Board();
		BackBoard.makeBoard();
		
		undoBoard = BackBoard.gameBoard;
		
		rank = 0; file = 0;
		for(rank=0;rank<8;rank++){
			for(file=0;file<8;file++){
				board[rank][file].setOnTouchListener(new OnTouchListener(){
					@Override
					public boolean onTouch(View v, MotionEvent event) {
						if(!move){
							if(!BackBoard.gameBoard[rank][file].toString().equalsIgnoreCase("   ")&&!BackBoard.gameBoard[rank][file].toString().equalsIgnoreCase("## ")){
								String viewCoordString = (String) v.getTag();
								int nufile = Integer.parseInt(Character.toString(viewCoordString.charAt(0)))-1;
								int nurank = Integer.parseInt(Character.toString(viewCoordString.charAt(1)));
								held = BackBoard.gameBoard[8-nurank][nufile];
								move = true;
							}}
						else{
							String viewCoordString = (String) v.getTag();
							int nufile = Integer.parseInt(Character.toString(viewCoordString.charAt(0)))-1;
							int nurank = Integer.parseInt(Character.toString(viewCoordString.charAt(1)));
							if(held.column == nufile && held.row==nurank){
								//v.setBackgroundColor(prev);
								
							}
							else{
								if(whiteMove && held.color == 0){
									if(held.validMove(held.row, held.column, 8-nurank, nufile)){
										undoRow1 = held.row;undoRow2 = nurank;undoCol1 = held.column;undoCol2 = nufile;
										tempPiece = BackBoard.gameBoard[8-undoRow2][undoCol2];
									
										held.movePiece(held.row, held.column, 8-nurank, nufile);
										log= log + Integer.toString(undoRow1)+Integer.toString(undoCol1)+Integer.toString(8-nurank)+Integer.toString(nufile)+" ";
										canUndo = true;
										//checks for white player castling, moves rook
										if(held.row == 7 && 8-nurank == 7 ){
										//	Toast.makeText(getApplicationContext(), BackBoard.gameBoard[held.row][held.column].toString(), Toast.LENGTH_LONG).show();

											if( BackBoard.gameBoard[held.row][held.column].toString().equals("wK ")){
												Toast.makeText(getApplicationContext(), BackBoard.gameBoard[held.row][held.column].toString(), Toast.LENGTH_LONG).show();
												//(nufile - file) == 2 &&
											//	King tempKing = (King) BackBoard.gameBoard[held.row][held.column];
												//if(tempKing.firstMove == false){
											//		BackBoard.gameBoard[held.row][held.column].movePiece(held.row, held.column+3, 8-held.row, held.column+1);
												//}
											}
											if( BackBoard.gameBoard[held.row][held.column].toString().equals("wK ")){
												//(held.column - file) == 2 &&
												Toast.makeText(getApplicationContext(), "Check", Toast.LENGTH_LONG).show();

											//	King tempKing = (King) BackBoard.gameBoard[rank][file];
												//if(tempKing.firstMove == false){
												//	BackBoard.gameBoard[held.row][held.column - 4].movePiece(held.row, held.column-4, 8-held.row, held.column-2);
												//}
											}
										}
										int kingrow1=0,kingcolumn1=0,kingrow2=0,kingcolumn2=0;
										boolean blackCheck=false,whiteCheck=false;
										for( int i = 0; i< 	8; i++) {
											for(int j = 0; j < 8; j++){
												if(BackBoard.gameBoard[i][j].toString().equals("wK ")){
													kingrow1 = i;
													kingcolumn1 = j;
												}
												if(BackBoard.gameBoard[i][j].toString().equals("bK ")){
													kingrow2 = i;
													kingcolumn2 = j;
												}
													
											}
										}
										for( int i = 0; i< 	8; i++) {
											for(int j = 0; j < 8; j++){
												if(!blackCheck){
												blackCheck = BackBoard.gameBoard[i][j].validMove(i, j, kingrow2, kingcolumn2);
												
												}
												if(!whiteCheck){
												whiteCheck = BackBoard.gameBoard[i][j].validMove(i, j, kingrow1, kingcolumn1);
												
												}
											}
											if(blackCheck||whiteCheck)
												Toast.makeText(getApplicationContext(), "Check", Toast.LENGTH_LONG).show();
										}
										whiteMove = false;
									}
									
								}
								if(!whiteMove && held.color == 1){
									if(held.validMove(held.row, held.column, 8-nurank, nufile)){
										undoRow1 = held.row;undoRow2 = nurank;undoCol1 = held.column;undoCol2 = nufile;
										tempPiece = BackBoard.gameBoard[8-undoRow2][undoCol2];
										held.movePiece(held.row, held.column, 8-nurank, nufile);
										log= log + Integer.toString(undoRow1)+Integer.toString(undoCol1)+Integer.toString(8-nurank)+Integer.toString(nufile)+" ";
										canUndo = true;
										//checks for black player castle, moves rook
										if(held.row == 0 && nurank == 0 ){
											if(file == 4 && nufile == 6 && BackBoard.gameBoard[rank][file].toString().equals("bK ")){
												King tempKing = (King) BackBoard.gameBoard[rank][file];
												if(tempKing.firstMove == false){
												//	BackBoard.gameBoard[rank][file +3].movePiece(rank, file+3, rank, nufile-1);
												}
											}
											if(held.row == 4 && nufile == 2 && BackBoard.gameBoard[rank][file].toString().equals("bK ")){
												King tempKing = (King) BackBoard.gameBoard[rank][file];
												if(tempKing.firstMove == false){
												//	BackBoard.gameBoard[rank][file - 4].movePiece(rank, file-4, rank, nufile+1);
												}
											}
										}
										int kingrow1=0,kingcolumn1=0,kingrow2=0,kingcolumn2=0;										
										boolean blackCheck=false,whiteCheck=false;
										for( int i = 0; i< 	8; i++) {
											for(int j = 0; j < 8; j++){
												if(BackBoard.gameBoard[i][j].toString().equals("wK ")){
													kingrow1 = i;
													kingcolumn1 = j;
												}
												if(BackBoard.gameBoard[i][j].toString().equals("bK ")){
													kingrow2 = i;
													kingcolumn2 = j;
												}
													
											}
										}
										for( int i = 0; i< 	8; i++) {
											for(int j = 0; j < 8; j++){
												if(!blackCheck){
												blackCheck = BackBoard.gameBoard[i][j].validMove(i, j, kingrow2, kingcolumn2);
												}
												if(!whiteCheck){
												whiteCheck = BackBoard.gameBoard[i][j].validMove(i, j, kingrow1, kingcolumn1);
												}
											}
										if(blackCheck||whiteCheck)
											Toast.makeText(getApplicationContext(), "Check", Toast.LENGTH_LONG).show();
										}
										
										whiteMove = true;
									}
									
								}
							}
							
							held = new Pieces(-1, -1, -1);
							held = null;
							BackBoard.whiteMove=whiteMove;
							move = false;
						}
						//v.setBackgroundColor(prev);
						
						
						updateBoard();
						return false;
					}
					
				});
			}
		}
		
		rank = 0; file = 0;
		
		updateBoard();
				
		
		
		Button undobutton = (Button)findViewById(R.id.undobutton);

		undobutton.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				if(canUndo){
					canUndo = false;
					BackBoard.gameBoard[8-undoRow2][undoCol2].movePiece(8-undoRow2, undoCol2, undoRow1, undoCol1);
					BackBoard.gameBoard[8-undoRow2][undoCol2] = tempPiece;
					BackBoard.gameBoard[8-undoRow2][undoCol2].row = undoRow2;
					BackBoard.gameBoard[8-undoRow2][undoCol2].column = undoCol2;
					whiteMove=!whiteMove;
					BackBoard.whiteMove=whiteMove;
					updateBoard();
				}
			}
		});
		
		Button resignButton = (Button)findViewById(R.id.resignButton);
		resignButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(whiteMove)
					results = "Black Wins";
				else
					results = "White Wins";
				
				Intent end = new Intent(ChessGameActivity.this,SaveGameActivity.class);
				end.putExtra("log", log);
				end.putExtra("results", results);
				startActivity(end);
			}
		});
		
		Button drawButton = (Button)findViewById(R.id.Draw);
		drawButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				results = "Draw";
				Intent end = new Intent(ChessGameActivity.this,SaveGameActivity.class);
				end.putExtra("log", log);
				end.putExtra("results", results);
				startActivity(end);
			}
			
		});
		
		Button aiButton = (Button)findViewById(R.id.ai);
		aiButton.setOnClickListener(new OnClickListener() {
			
			
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				boolean breakable = false;
				for(int i = 0; i < 8; i++){
					for(int j = 0; j < 8; j++){
						for(int k = 0; k < 8; k++){
							for(int c = 0; c< 8; c++){
						if(Board.gameBoard[i][j].validMove(i, j, 8-k, c)){
							Board.gameBoard[i][j].movePiece(i, j, 8-k, c);
							breakable = true;
							break;
						}
						
						}
							if(breakable){
								break;
							}
						}
						if(breakable){
							break;
						}
					}
					if(breakable){
						break;
					}
				}
				whiteMove=!whiteMove;
				BackBoard.whiteMove=whiteMove;
				updateBoard();
				
			}
		});
		
/*
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}*/
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
					board[i][j].setForeground(getResources().getDrawable(R.drawable.bking));
				}
				if (BackBoard.gameBoard[i][j].toString().equalsIgnoreCase("bn ")){
					board[i][j].setForeground(getResources().getDrawable(R.drawable.bknight));
				}
				if (BackBoard.gameBoard[i][j].toString().equalsIgnoreCase("bb ")){
					board[i][j].setForeground(getResources().getDrawable(R.drawable.bbish));
				}
				if (BackBoard.gameBoard[i][j].toString().equalsIgnoreCase("bq ")){
					board[i][j].setForeground(getResources().getDrawable(R.drawable.bqueen));
				}
				if (BackBoard.gameBoard[i][j].toString().equalsIgnoreCase("br ")){
					board[i][j].setForeground(getResources().getDrawable(R.drawable.brook));
				}
				if (BackBoard.gameBoard[i][j].toString().equalsIgnoreCase("wp ")){
					board[i][j].setForeground(getResources().getDrawable(R.drawable.wpawn));
				}
				if (BackBoard.gameBoard[i][j].toString().equalsIgnoreCase("wk ")){
					board[i][j].setForeground(getResources().getDrawable(R.drawable.wking));
				}
				if (BackBoard.gameBoard[i][j].toString().equalsIgnoreCase("wn ")){
					board[i][j].setForeground(getResources().getDrawable(R.drawable.wknight));
				}
				if (BackBoard.gameBoard[i][j].toString().equalsIgnoreCase("wb ")){
					board[i][j].setForeground(getResources().getDrawable(R.drawable.wbish));
				}
				if (BackBoard.gameBoard[i][j].toString().equalsIgnoreCase("wq ")){
					board[i][j].setForeground(getResources().getDrawable(R.drawable.wqueen));
				}
				if (BackBoard.gameBoard[i][j].toString().equalsIgnoreCase("wr ")){
					board[i][j].setForeground(getResources().getDrawable(R.drawable.wrook));
				}
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
	//	getMenuInflater().inflate(R.menu.chess_game, menu);
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
			View rootView = inflater.inflate(R.layout.fragment_chess_game,
					container, false);
			return rootView;
		}
	}

}
