package com.example.group51androidprojectchess;

public class Pawn extends Pieces{
	
	boolean firstMove;
	
	public Pawn(int color, int row, int column){
		super(color, row, column);
		this.firstMove = false;
	}
	
	public String toString(){
		if(color == 0){
			return "wp ";
		}else{
			return "bp ";}
	}
	
	public boolean validMove(int row, int column, int targetRow, int targetColumn){
		//if white players turn
		if(Board.whiteMove){
			//if it is the correct piece
			if(Board.gameBoard[row][column].color == 0 && Board.gameBoard[row][column].toString().equals("wp ")) {
			//if it is the pawn's first time moving
			if(getFirstMove((Pawn) Board.gameBoard[row][column]) == false){
				if(targetColumn == column){
					if(row - targetRow == 1 && Board.gameBoard[row-1][column].color != 0){
						return true;
					}
					if(row - targetRow == 2 && Board.gameBoard[row-1][column].color != 0 && Board.gameBoard[row-2][column].color != 0){
						return true;
				}
			}
			
		}
			//if it is not the pawn's first time moving and it is not attacking
			if(targetRow <= 7 && targetRow >=0 && targetColumn >= 0 && targetColumn <= 7){
				if(column == targetColumn){
					if(Board.gameBoard[row][column].color == 0){
						if(targetRow - row < 0 && Board.gameBoard[targetRow][targetColumn].color != 0 
								&& Board.gameBoard[targetRow][targetColumn].color != 1 && targetRow - row == -1){
							return true;
						}
					}
				}
				
		  if(Board.gameBoard[row][column].color == 0){
			  if(Math.abs(targetRow - row) ==1 && Math.abs(targetColumn - column) ==1){
				  if(Board.gameBoard[targetRow][targetColumn].color == 1){
					  return true;
				  }
			  }
		  }
		
			
			
			}else{return false;}//System.out.println("Asking to move to invalid board location");return false;}
			
		
		
		
		}else{return false;}//System.out.println("Asking to move incorrect piece type.");return false;}
			return false;
		}else{
			if(Board.gameBoard[row][column].color == 1 && Board.gameBoard[row][column].toString().equals("bp ")) {
			
				if(getFirstMove((Pawn) Board.gameBoard[row][column]) == false){
					if(Board.gameBoard[row][column].color == 1 && targetColumn == column){
						if(targetRow - row == 1 && Board.gameBoard[row+1][column].color != 1){
							return true;
					}
						if(targetRow - row == 2 && Board.gameBoard[row+1][column].color != 1 && Board.gameBoard[row+2][column].color != 1){
							return true;
					}
				}
			}
			
		
				
				if(targetRow <= 7 && targetRow >=0 && targetColumn >= 0 && targetColumn <= 7){
					if(column == targetColumn){
						if(Board.gameBoard[row][column].color == 1){
							if(targetRow - row >0 && Board.gameBoard[targetRow][targetColumn].color != 1 
									&& Board.gameBoard[targetRow][targetColumn].color != 0 && targetRow - row == 1){
								return true;
							}
						}
					}
			  if(Board.gameBoard[row][column].color == 1){
				  if(Math.abs(targetRow - row) == 1 && Math.abs(targetColumn - column) ==1){
					  if(Board.gameBoard[targetRow][targetColumn].color == 0){
						  return true;
					  }
				  }
			  }
				
				
				}else{return false;}//System.out.println("Asking to move to invalid board location");return false;}
				
			
			
			
			}else{return false;}//System.out.println("Asking to move incorrect piece type.");return false;}
			return false;		

		}
	}
	
	public boolean getFirstMove(Pawn pawn1){
		return pawn1.firstMove;
	}
	public void movePiece(int row, int column, int targetrow, int targetcolumn){
		Pieces tempPiece = new Pieces(-1000, row, column);
		Pawn tempPiece2 =(Pawn) Board.gameBoard[row][column];
		tempPiece2.firstMove = true;
		Board.gameBoard[targetrow][targetcolumn] = Board.gameBoard[row][column];
		Board.gameBoard[targetrow][targetcolumn].row = targetrow;
		Board.gameBoard[targetrow][targetcolumn].column = targetcolumn;
		Board.gameBoard[row][column] =  tempPiece;
	}

}
