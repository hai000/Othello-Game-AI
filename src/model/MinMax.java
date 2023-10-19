package model;

import java.awt.Point;
import java.util.List;

public class MinMax {
	private static final int MAX_DEPTH = 6;

	public static Point findBestMove(Board board, boolean max, int player) {
		Point bestMove = null;
		int bestScore = Integer.MIN_VALUE;

		List<Point> pointCanMove = BoardHelp.getPointsCanMove(board.board, player);
		bestMove=pointCanMove.get(0);
		for (Point point : pointCanMove) {
			int[][] coppyBoard = coppyBoard(board.board);
			coppyBoard = BoardHelp.getBoardAfterMove(coppyBoard, player, point);
			int score = minimax(coppyBoard, MAX_DEPTH - 1, Integer.MIN_VALUE, Integer.MAX_VALUE, false,
					player == 1 ? 2 : 1);
			if (score > bestScore) {
				bestScore = score;
				bestMove = point;
			}

		}
	

		return bestMove;
	}

	private static int minimax(int[][] board, int depth, int minValue, int maxValue, boolean maximizingPlayer,
			int player) {
		if (depth == 0 || BoardHelp.isGameOver(board)) {
			return evaluate(board, player);
		}
		if (maximizingPlayer) {
			int maxEval = Integer.MIN_VALUE;
			List<Point> pointCanMove = BoardHelp.getPointsCanMove(board, player);
			for (Point point : pointCanMove) {
				int[][] coppyBoard = coppyBoard(board);
				coppyBoard = BoardHelp.getBoardAfterMove(coppyBoard, player, point);
				int eval = minimax(coppyBoard, depth - 1, minValue, maxValue, false, (player == 1) ? 2 : 1);
				maxEval = Math.max(maxEval, eval);
				minValue = Math.max(minValue, eval);
				if (maxValue <= minValue) {
					break;
				}

			}
			return maxEval;
		} else {
			int minEval = Integer.MAX_VALUE;
			List<Point> pointCanMove = BoardHelp.getPointsCanMove(board, player);
			for (Point point : pointCanMove) {
				int[][] coppyBoard = coppyBoard(board); // Sao chép bảng hiện tại
				coppyBoard = BoardHelp.getBoardAfterMove(coppyBoard, player, point);
				int eval = minimax(coppyBoard, depth - 1, minValue, maxValue, true, (player == 1) ? 2 : 1);
				minEval = Math.min(minEval, eval);
				maxValue = Math.min(maxValue, eval);
				if (maxValue <= minValue) {
					break; // Cắt Alpha-Beta
				}
			}
			return minEval;
		}
	}

	private static int evaluate(int[][] board, int player) {
		// Đánh giá bảng hiện tại dựa trên một số heuristics
		// và trạng thái của các ô trong bảng

		// TODO: Thêm mã đánh giá tùy chỉnh cho game Othello
		int score = 0;

		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				int playerCur = board[row][col];

				if (playerCur == player) {
					score += BoardHelp.BOARDEVALUATE[row][col];
				} else if (playerCur == (player == 1 ? 2 : 1)) {
					
					score -= BoardHelp.BOARDEVALUATE[row][col];
				}
			}
		}
		score+=7*(countPieces(board, player)-countPieces(board, (player==1?2:1)));
		return score;
	}
public static int countPieces(int[][] board , int player) {
	int res=0;
	for(int i =0;i<board.length;i++) {
		for (int j = 0; j < board[i].length; j++) {
			if(board[i][j]==player) {
				res++;
			}
		}
	}
	return res;
}
	public static int[][] coppyBoard(int[][] board) {
		int[][] coppyBoard = new int[board.length][board[0].length];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				coppyBoard[i][j] = board[i][j];
			}
		}
		return coppyBoard;
	}

}
