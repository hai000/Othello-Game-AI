package model;

import java.awt.Color;
import java.awt.Point;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import controller.BoardGameController;

public class Board {
	public static Pieces[][] board;
	static Player player1 = Player1.getInstance();
	static Player player2 = Player2.getInstance();
	static Player playerEmpty = new Player() {
	};
	public static Player firstPlayer = player2;
	public static Player currentPlayer = firstPlayer;

	public Board(int rows, int cols) {
		getNewBoard(rows, cols);

	}
	
	public static boolean isGameOver() {
		return BoardHelp.isGameOver(getBoardValue());
	}
	public static Player getWinner() {
		int scorePlayer1 = BoardHelp.getToal(getBoardValue(), player1.pieces.player);
		int scorePlayer2 = BoardHelp.getToal(getBoardValue(), player2.pieces.player);
		return (scorePlayer1>scorePlayer2)?player1:player2;
	}

	public static boolean canSetPieces(int row, int col) {
		return BoardHelp.canMove(getBoardValue(), currentPlayer.pieces.player, row, col);
	}

	public static void setPieces(int row, int col) {
		int[][] boardValue = BoardHelp.getBoardAfterMove(getBoardValue(), currentPlayer.pieces.player,
				new Point(row, col));
		board = getBoardPieces(boardValue);
		BoardGameController.updateBoard(row, col);
		BoardGameController.updateScore(BoardHelp.getToal(boardValue, player1.pieces.player),BoardHelp.getToal(boardValue, player2.pieces.player));
		if(isGameOver()) {
			BoardGameController.updateWinner(getWinner());
			return;
		}
		if (hasSwitchPlayer()) {
			currentPlayer = (currentPlayer == player1) ? player2 : player1;
			BoardGameController.updateCurrentPlayer(currentPlayer);
		}
		
		if (currentPlayer.isAI) {
			AIPLay();
			
		}else {
			updatePointCanMove();
		}

	}
	public static void updatePointCanMove() {
		List<Point> points = BoardHelp.getPointsCanMove(getBoardValue(), currentPlayer.pieces.player);
		BoardGameController.updatePointCanMove(points);
	}
	

	private static boolean hasSwitchPlayer() {

		return BoardHelp.hasMoveAny(getBoardValue(),
				currentPlayer == player1 ? player2.pieces.player : player1.pieces.player);
	}

	public static int[][] getBoardValue() {
		int[][] res = new int[board.length][board.length];
		for (int i = 0; i < res.length; i++) {

			for (int j = 0; j < res.length; j++) {
				res[i][j] = board[i][j] != null ? board[i][j].player : 0;
			}
		}
		return res;
	}

	private static Pieces[][] getBoardPieces(int[][] data) {
		Pieces[][] res = new Pieces[data.length][data.length];
		for (int i = 0; i < res.length; i++) {

			for (int j = 0; j < res.length; j++) {
				if (data[i][j] != 0) {
					res[i][j] = (data[i][j] == 1) ? player1.pieces : player2.pieces;
				} else {
					res[i][j] = playerEmpty.pieces;
				}
			}
		}
		return res;
	}

	public static void AIPLay() {
		Timer timer = new Timer();
		TimerTask task = new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				 MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();

			        // Lấy thông tin về việc sử dụng bộ nhớ của chương trình
			        MemoryUsage heapMemoryUsage = memoryBean.getHeapMemoryUsage();
			        MemoryUsage nonHeapMemoryUsage = memoryBean.getNonHeapMemoryUsage();

			        // Tính toán lượng RAM đã sử dụng
			        long usedHeapMemory = heapMemoryUsage.getUsed();
			        long usedNonHeapMemory = nonHeapMemoryUsage.getUsed();
			        long totalUsedMemory = usedHeapMemory + usedNonHeapMemory;
				long timeNow = System.currentTimeMillis();
				Point point = currentPlayer.modelAI.findBestMove(getBoardValue(), true, currentPlayer.pieces.player);
				long timeEnd = System.currentTimeMillis();
				
				 MemoryMXBean memoryBean2 = ManagementFactory.getMemoryMXBean();

			        // Lấy thông tin về việc sử dụng bộ nhớ của chương trình
			        MemoryUsage heapMemoryUsage2 = memoryBean2.getHeapMemoryUsage();
			        MemoryUsage nonHeapMemoryUsage2 = memoryBean2.getNonHeapMemoryUsage();

			        // Tính toán lượng RAM đã sử dụng
			        long usedHeapMemory2 = heapMemoryUsage2.getUsed();
			        long usedNonHeapMemory2 = nonHeapMemoryUsage2.getUsed();
			        long totalUsedMemory2 = usedHeapMemory2 + usedNonHeapMemory2;
				System.out.println("Depth " + " " +(totalUsedMemory2-totalUsedMemory) + " bytes");
				System.out.println("Consume time :" + (timeEnd - timeNow));
				setPieces(point.x, point.y);
				
			}
		};
		timer.schedule(task, 700);
				// TODO Auto-generated method stub
		// TODO Auto-generated method stub
		
	}

	public void getNewBoard(int rows, int cols) {
		board = new Pieces[rows][cols];

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				board[i][j] = playerEmpty.pieces;
			}
		}

		board[rows / 2][cols / 2] = player1.pieces;
		board[(rows / 2) - 1][(cols / 2) - 1] = player1.pieces;
		board[rows / 2][(cols / 2) - 1] = player2.pieces;
		board[(rows / 2) - 1][cols / 2] = player2.pieces;
	}
	public void setColorPlayer2(Color color) {
		player2.setColor(color);
	}
	public void setColorPlayer1(Color color) {
		player1.setColor(color);
	}
}
