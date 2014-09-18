package com.example.group51androidprojectchess;

public class Pieces {
	
	final int color;
	int row;
	int column;
	
	public Pieces(int color, int row, int column){
		this.color = color;
		this.row = row;
		this.column = column;
		
	}
	
	public boolean validMove(int row1, int column1, int row2, int column2){
		return false;
	}
	public void movePiece(int row, int column, int row2, int column2){
		return;
	}
	
	public String toString(){
		if(color == -100){
			return "## ";
		}
		if(color == -200){
			return "   ";
		}
		
		if(color == 100){
			return "a  ";
		}
		if(color == 101){
			return "b  ";
		}
		if(color == 102){
			return "c  ";
		}
		if(color == 103){
			return "d  ";
		}
		if(color == 104){
			return "e  ";
		}
		if(color == 105){
			return "f  ";
		}
		if(color == 106){
			return "g  ";
		}
		if(color == 107){
			return "h  ";
		}
		if(color == 200){
			return "8";
		}
		if(color == 201){
			return "7";
		}
		if(color == 202){
			return "6";
		}
		if(color == 203){
			return "5";
		}
		if(color == 204){
			return "4";
		}
		if(color == 205){
			return "3";
		}
		if(color == 206){
			return "2";
		}
		if(color == 207){
			return "1";
		}
	
		return "";
	}
}
