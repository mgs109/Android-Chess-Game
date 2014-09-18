package com.example.group51androidprojectchess;

	public class Bishop extends Pieces{
		public Bishop(int color, int row, int column){
			super(color, row, column);
		}
		
		public String toString(){
			if(color == 0){
				return "wB ";
			}else{return "bB ";}
		}
		
		public boolean validMove(int row, int column, int targetRow, int targetColumn){
			if(Board.whiteMove){	
				if(Board.gameBoard[row][column].color == 0 && Board.gameBoard[row][column].toString().equals("wB ")) {
					if(targetRow <= 7 && targetRow >=0 && targetColumn >= 0 && targetColumn <= 7){
						if(row == targetRow || column == targetColumn){
							return false;
						}
						if((Math.abs(row - targetRow) != Math.abs(column - targetColumn))){
							return false;
						}
						if(row > targetRow && column > targetColumn){
							int i = row-1,  j = column-1;
							while(i > targetRow && j > targetColumn){
								if(Board.gameBoard[i][j].color == 1 || Board.gameBoard[i][j].color == 0){
									return false;
								}
								i--;
								j--;
							}
							if(Board.gameBoard[i][j].color != 0){
								return true;
							}
						}
						if(row > targetRow && column < targetColumn){
							int i = row-1,  j = column+1;
							while(i > targetRow && j < targetColumn){
								if(Board.gameBoard[i][j].color == 1 || Board.gameBoard[i][j].color == 0){
									return false;
								}
								i--;
								j++;
							}
							if(Board.gameBoard[i][j].color != 0){
								return true;
							}
						}
						if(row < targetRow && column > targetColumn){
							int i = row+1,  j = column-1;
							while(i < targetRow && j > targetColumn){
								if(Board.gameBoard[i][j].color == 1 || Board.gameBoard[i][j].color == 0){
									return false;
								}
								i++;
								j--;
							}
							if(Board.gameBoard[i][j].color != 0){
								return true;
							}
						}
						if(row < targetRow && column < targetColumn){
							int i = row+1,  j = column+1;
							while(i < targetRow && j < targetColumn){
								if(Board.gameBoard[i][j].color == 1 || Board.gameBoard[i][j].color == 0){
									return false;
								}
								i++;
								j++;
							}
							if(Board.gameBoard[i][j].color != 0){
								return true;
							}
						}
						
						
					}else{return false;}//else{System.out.println("Asking to move to invalid board location");return false;}
					
				}else{return false;}//else{System.out.println("Asking to move incorrect piece type");return false;}
			}else{
				
				if(Board.gameBoard[row][column].color == 1 && Board.gameBoard[row][column].toString().equals("bB ")) {
					if(targetRow <= 7 && targetRow >=0 && targetColumn >= 0 && targetColumn <= 7){
						if(row == targetRow || column == targetColumn){
							return false;
						}
						if((Math.abs(row - targetRow) != Math.abs(column - targetColumn))){
							return false;
						}
						if(row > targetRow && column > targetColumn){
							int i = row-1,  j = column-1;
							while(i > targetRow && j > targetColumn){
								if(Board.gameBoard[i][j].color == 1 || Board.gameBoard[i][j].color == 0){
									return false;
								}
								i--;
								j--;
							}
							if(Board.gameBoard[i][j].color != 1){
								return true;
							}
						}
						if(row > targetRow && column < targetColumn){
							int i = row-1,  j = column+1;
							while(i > targetRow && j < targetColumn){
								if(Board.gameBoard[i][j].color == 1 || Board.gameBoard[i][j].color == 0){
									return false;
								}
								i--;
								j++;
							}
							if(Board.gameBoard[i][j].color != 1){
								return true;
							}
						}
						if(row < targetRow && column > targetColumn){
							int i = row+1,  j = column-1;
							while(i < targetRow && j > targetColumn){
								if(Board.gameBoard[i][j].color == 1 || Board.gameBoard[i][j].color == 0){
									return false;
								}
								i++;
								j--;
							}
							if(Board.gameBoard[i][j].color != 1){
								return true;
							}
						}
						if(row < targetRow && column < targetColumn){
							int i = row+1,  j = column+1;
							while(i < targetRow && j < targetColumn){
								if(Board.gameBoard[i][j].color == 1 || Board.gameBoard[i][j].color == 0){
									return false;
								}
								i++;
								j++;
							}
							if(Board.gameBoard[i][j].color != 1){
								return true;
							}
						}
						
						
					}else{return false;}//else{System.out.println("Asking to move to invalid board location");return false;}
					
				}else{return false;}//else{System.out.println("Asking to move incorrect piece type");return false;}
				
				
			}
			return false;
		}
		
		public void movePiece(int row, int column, int targetrow, int targetcolumn){
			Pieces tempPiece = new Pieces(-1000, row, column);
			Board.gameBoard[targetrow][targetcolumn] = Board.gameBoard[row][column];
			Board.gameBoard[targetrow][targetcolumn].row = targetrow;
			Board.gameBoard[targetrow][targetcolumn].column = targetcolumn;
			Board.gameBoard[row][column] =  tempPiece;
		}
	}