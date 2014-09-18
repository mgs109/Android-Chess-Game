package com.example.group51androidprojectchess;

public class Rook extends Pieces{
	boolean firstMove;
	public Rook(int color, int row, int column){
		super(color,  row,  column);
		this.firstMove = false;
	}

	
	public String toString(){
		if(color == 0){
			return "wR ";
		}else{return "bR ";}
	}
	
	public boolean validMove(int row, int column, int targetrow, int targetcolumn){
		if(Board.whiteMove){
			if(Board.gameBoard[row][column].color == 0 && Board.gameBoard[row][column].toString().equals("wR ")){
				if(targetrow <= 7 && targetrow >=0 && targetcolumn >= 0 && targetcolumn <= 7){
					if((row == targetrow || column == targetcolumn) && !(row == targetrow && column == targetcolumn)){
						if(row != targetrow){
							
							if(row < targetrow){
								int i = row+1;
								while(i < targetrow){
									if(Board.gameBoard[i][column].color == 1 ||Board.gameBoard[i][column].color == 0){
										return false;
									}
									i++;
								}
								if(Board.gameBoard[i][column].color != 0){
									return true;
								}
							}else{
								int i = row -1;
								while(i > targetrow){
									if(Board.gameBoard[i][column].color == 1 || Board.gameBoard[i][column].color == 0){
										return false;
									}
									i--;
								}
								if(Board.gameBoard[i][column].color != 0){
									return true;
								}
								
							}
						}else{
							if(column < targetcolumn){
							int i = column+1;
							while(i < targetcolumn){
								if(Board.gameBoard[row][i].color == 1 ||Board.gameBoard[row][i].color == 0){
									return false;
								}
								i++;
							}
							if(Board.gameBoard[row][i].color != 0){
								return true;
							}
							}else{
								int i = column -1;
								while(i > targetcolumn){
									if(Board.gameBoard[row][i].color == 1 ||Board.gameBoard[row][i].color == 0){
										return false;
									}
									i--;
								}
								if(Board.gameBoard[row][i].color != 0){
									return true;
								}
								
							}
						}
					}
				}else{return false;}//else{System.out.println("Asking to move to invalid board location");return false;}
			}else{return false;}//else{System.out.println("Asking to move incorrect piece type.");return false;}
		}else{
			if(Board.gameBoard[row][column].color == 1 && Board.gameBoard[row][column].toString().equals("bR ")){
				if(targetrow <= 7 && targetrow >=0 && targetcolumn >= 0 && targetcolumn <= 7){
					if((row == targetrow || column == targetcolumn) && !(row == targetrow && column == targetcolumn)){
						if(row != targetrow){
							if(row < targetrow){
								int i = row+1;
								while(i < targetrow){
									if(Board.gameBoard[i][column].color == 1 ||Board.gameBoard[i][column].color == 0){
										return false;
									}
									i++;
								}
								if(Board.gameBoard[i][column].color != 1){
									return true;
								}
							}else{
								int i = row -1;
								while(i > targetrow){
									if(Board.gameBoard[i][column].color == 1 || Board.gameBoard[i][column].color == 0){
										return false;
									}
									i--;
								}
								if(Board.gameBoard[i][column].color != 1){
									return true;
								}
								
							}
						}else{
							if(column < targetcolumn){
							int i = column+1;
							while(i < targetcolumn){
								if(Board.gameBoard[row][i].color == 1 ||Board.gameBoard[row][i].color == 0){
									return false;
								}
								i++;
							}
							if(Board.gameBoard[row][i].color != 1){
								return true;
							}
							}else{
								int i = column -1;
								while(i > targetcolumn){
									if(Board.gameBoard[row][i].color == 1 ||Board.gameBoard[row][i].color == 0){
										return false;
									}
									i--;
								}
								if(Board.gameBoard[row][i].color != 1){
									return true;
								}
								
							}
						}
					}
				}else{return false;}//else{System.out.println("Asking to move to invalid board location");return false;}
			}else{return false;}//else{System.out.println("Asking to move incorrect piece type.");return false;}
			
		}
		return false;
	}
	public void movePiece(int row, int column, int targetrow, int targetcolumn){
		Pieces tempPiece = new Pieces(-1000, row, column);
		Rook tempPiece2 =(Rook) Board.gameBoard[row][column];
		tempPiece2.firstMove = true;
		Board.gameBoard[targetrow][targetcolumn] = Board.gameBoard[row][column];
		Board.gameBoard[targetrow][targetcolumn].row = targetrow;
		Board.gameBoard[targetrow][targetcolumn].column = targetcolumn;
		Board.gameBoard[row][column] =  tempPiece;
	}
}
