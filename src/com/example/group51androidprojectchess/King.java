package com.example.group51androidprojectchess;



	public class King extends Pieces{
		
		boolean firstMove;
		
		public King(int color, int row, int column){
			super(color, row, column);
			this.firstMove = false;
		}
		
		public String toString(){
			if(color == 0){
				return "wK ";
			}else{return "bK ";}
		}
		public boolean validMove(int row, int column, int targetRow, int targetColumn){
			
			if(Board.whiteMove){	
				if(Board.gameBoard[row][column].color == 0 && Board.gameBoard[row][column].toString().equals("wK ")) {
				
					if(targetRow <= 7 && targetRow >=0 && targetColumn >= 0 && targetColumn <= 7){
						King tempKing = (King) Board.gameBoard[row][column];

						if(tempKing.firstMove == false){
							if((targetColumn > column && targetColumn == column+2) &&  (row == targetRow) && Board.gameBoard[row][column+3].toString().equals("wR ")&& Board.gameBoard[row][column+3].toString().equals("bR ")){
								Rook tempRook = (Rook) Board.gameBoard[row][column+3];
							if(Board.gameBoard[row][column+1].color != 0 && Board.gameBoard[row][column+1].color != 1
									&& Board.gameBoard[row][column+2].color != 0 && Board.gameBoard[row][column+2].color != 1
									&& Board.gameBoard[row][column+3].color == 0 && 
									Board.gameBoard[row][column+3].toString().equals("wR ")){
								if(tempRook.firstMove == false){
									return true;
								}
							}
						}else{
							if( (row == targetRow)){
								Rook tempRook = null;
								if(Board.gameBoard[row][column-4].toString().equals("bR ") ||Board.gameBoard[row][column-4].toString().equals("wR ")){
								tempRook = (Rook) Board.gameBoard[row][column-4];
								}
							if(Board.gameBoard[row][column-1].color != 0 && Board.gameBoard[row][column-1].color != 1
									&& Board.gameBoard[row][column-2].color != 0 && Board.gameBoard[row][column-2].color != 1
									&& Board.gameBoard[row][column-3].color != 0 && Board.gameBoard[row][column-3].color != 1
									&& Board.gameBoard[row][column-4].color == 0 && Board.gameBoard[row][column-4].toString().equals("wR ")
									&& targetColumn == column - 2){
								if(tempRook != null && tempRook.firstMove == false){
									return true;
								}
							}
							}
						}
						}
						if((Math.abs(targetRow - row) + Math.abs(targetColumn - column)) <= 2){
							if(Math.abs(targetRow - row) < 2 && Math.abs(targetColumn - column) < 2){
								if(Board.gameBoard[targetRow][targetColumn].color != Board.gameBoard[row][column].color){
									return true;
								}else{return false;}//else{System.out.println("Asking to move onto friendly piece");return false;}
							}else{return false;}//else{System.out.println("Invalid move");return false;}
						}else{return false;}//else{System.out.println("Invalid move");return false;}
				
				
					}else{return false;}//else{System.out.println("Asking to move to invalid board location");return false;}
			
			
			
			}else{return false;}//else{System.out.println("Asking to move incorrect piece type.");return false;}
			}else{
				if(Board.gameBoard[row][column].color == 1 && Board.gameBoard[row][column].toString().equals("bK ")) {
					
					if(targetRow <= 7 && targetRow >=0 && targetColumn >= 0 && targetColumn <= 7){
						King tempKing = (King) Board.gameBoard[row][column];

						if(tempKing.firstMove == false){
							if((targetColumn > column && targetColumn == column+2) && (row == targetRow)){
								if(Board.gameBoard[row][column-4].toString().equals("bR ")){
								Rook tempRook = (Rook) Board.gameBoard[row][column+3];
							if(Board.gameBoard[row][column+1].color != 0 && Board.gameBoard[row][column+1].color != 1
									&& Board.gameBoard[row][column+2].color != 0 && Board.gameBoard[row][column+2].color != 1
									&& Board.gameBoard[row][column+3].color == 1 && 
									Board.gameBoard[row][column+3].toString().equals("bR ")){
								if(tempRook.firstMove == false){
									return true;
								}
							}
							}
						}else{
							if( (row == targetRow)){
								Rook tempRook = null;
								if(Board.gameBoard[row][column-4].toString().equals("wR ") || Board.gameBoard[row][column-4].toString().equals("bR ")){
									 tempRook = (Rook) Board.gameBoard[row][column-4];
								}
							if(Board.gameBoard[row][column-1].color != 0 && Board.gameBoard[row][column-1].color != 1
									&& Board.gameBoard[row][column-2].color != 0 && Board.gameBoard[row][column-2].color != 1
									&& Board.gameBoard[row][column-3].color != 0 && Board.gameBoard[row][column-3].color != 1
									&& Board.gameBoard[row][column-4].color == 0 && Board.gameBoard[row][column-4].toString().equals("wR ")
									&& targetColumn == column - 2){
								if(tempRook != null && tempRook.firstMove == false){
									return true;
								}
							}
							}
						}
						}
					
						if((Math.abs(targetRow - row) + Math.abs(targetColumn - column)) <= 2){
							if(Math.abs(targetRow - row) < 2 && Math.abs(targetColumn - column) < 2){
								if(Board.gameBoard[targetRow][targetColumn].color != Board.gameBoard[row][column].color){
									return true;
								}else{return false;}//else{System.out.println("Asking to move onto friendly piece");return false;}
							}else{return false;}//else{System.out.println("Invalid move");return false;}
						}else{return false;}//else{System.out.println("Invalid move");return false;}
					
					
					}else{return false;}//else{System.out.println("Asking to move to invalid board location");return false;}
				
				
				
				}else{return false;}//else{System.out.println("Asking to move incorrect piece type.");return false;}
				
			}
			
		}
		
		public void movePiece(int row, int column, int targetrow, int targetcolumn){
			Pieces tempPiece = new Pieces(-1000, row, column);
			King tempPiece2 =(King) Board.gameBoard[row][column];
			tempPiece2.firstMove = true;
			Board.gameBoard[targetrow][targetcolumn] = Board.gameBoard[row][column];
			Board.gameBoard[targetrow][targetcolumn].row = targetrow;
			Board.gameBoard[targetrow][targetcolumn].column = targetcolumn;
			Board.gameBoard[row][column] =  tempPiece;
		}
	}
	
