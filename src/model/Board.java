package model;

public class Board {
	public int[][] board;
	int player1 = 1, player2 = 2;

	public Board(int rows, int cols) {
		getNewBoard(rows, cols);
		init();
		

	}

	public void init() {

	}

	public void getNewBoard(int rows,int cols) {
		board = new int[rows][cols];
		board[rows/2][cols/2] =1;
		board[(rows/2)-1][(cols/2)-1] =1;
		board[rows/2][(cols/2)-1] =2;
		board[(rows/2)-1][cols/2] =2;
	}
}
