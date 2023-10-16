package model;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

public class BoardHelp {
	public static final int[][] BOARDEVALUATE = { { 120, -20, 20, 5, 5, 20, -20, 120 },
			{ -20, -40, -5, -5, -5, -5, -40, -20 }, { 20, -5, 15, 3, 3, 15, -5, 20 }, { 5, -5, 3, 3, 3, 3, -5, 5 },
			{ 5, -5, 3, 3, 3, 3, -5, 5 }, { 20, -5, 15, 3, 3, 15, -5, 20 }, { -20, -40, -5, -5, -5, -5, -40, -20 },
			{ 120, -20, 20, 5, 5, 20, -20, 120 } };

	public static boolean isGameOver(int[][] board) {
		return !(hasMoveAny(board, 1) || hasMoveAny(board, 2));
	}

	public static boolean hasMoveAny(int[][] board, int player) {

		return getPointsCanMove(board, player).size() > 0;
	}

	public static int[][] getBoardAfterMove(int[][] board, int player, Point move) {
		int[][] clone = new int[board.length][board[0].length];// tạo clone ván game và cập nhật
		for (int i = 0; i < clone.length; i++) {
			for (int j = 0; j < clone[i].length; j++) {
				clone[i][j] = board[i][j];
			}
		}
		clone[move.x][move.y] = player;

		List<Point> lstPointRerse = getReversePoint(clone, player, move.x, move.y);
		for (Point point : lstPointRerse) {
			clone[point.x][point.y] = player;
		}

		return clone;
	}
	public static int getToal(int[][] board,int player) {
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

	public static List<Point> getPointsCanMove(int[][] board, int player) {
		List<Point> lst = new ArrayList<>();
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (canMove(board, player, i, j)) {
					lst.add(new Point(i, j));
				}
			}
		}
		return lst;
	}

	public static List<Point> getReversePoint(int[][] board, int player, int i, int j) {
		List<Point> res = new ArrayList<>();

		int opponentPlayer = ((player == 1) ? 2 : 1);

		int indexI = i - 1;
		int indexJ = j;
		List<Point> points = new ArrayList<>();
		// check up
		while (indexI > 0 && board[indexI][indexJ] == opponentPlayer) {

			points.add(new Point(indexI, indexJ));
			indexI--;
		}
		if (points.size() > 0 && indexI >= 0 && board[indexI][indexJ] == player) {
			res.addAll(points);
		}
		points.removeAll(points);

		// check down
		indexI = i + 1;
		indexJ = j;
		while (indexI < board.length-1 && board[indexI][indexJ] == opponentPlayer) {
			points.add(new Point(indexI, indexJ));
			indexI++;
		}
		if (indexI < board.length && board[indexI][indexJ] == player && points.size() > 0) {
			res.addAll(points);
		}
		points.removeAll(points);

		// check left
		indexI = i;
		indexJ = j - 1;
		while (indexJ > 0 && board[indexI][indexJ] == opponentPlayer) {
			points.add(new Point(indexI, indexJ));
			indexJ--;
		}
		if (points.size() > 0 && indexJ >= 0 && board[indexI][indexJ] == player) {
			res.addAll(points);
		}
		points.removeAll(points);

		// check right
		indexI = i;
		indexJ = j + 1;
		while (indexJ < board[i].length - 1 && board[indexI][indexJ] == opponentPlayer) {

			points.add(new Point(indexI, indexJ));
			indexJ++;
		}
		if (indexJ < board[i].length && board[indexI][indexJ] == player) {
			res.addAll(points);
		}
		points.removeAll(points);
		// check up left
		indexI = i - 1;
		indexJ = j - 1;
		while (indexI > 0 && indexJ > 0 && board[indexI][indexJ] == opponentPlayer) {
			points.add(new Point(indexI, indexJ));
			indexI--;
			indexJ--;
		}
		if (indexI >= 0 && indexJ >= 0 && board[indexI][indexJ] == player) {
			res.addAll(points);
		}
		points.removeAll(points);
		// check up right
		indexI = i - 1;
		indexJ = j + 1;
		while (indexI > 0 && indexJ < board[i].length - 1 && board[indexI][indexJ] == opponentPlayer) {
			points.add(new Point(indexI, indexJ));
			indexI--;
			indexJ++;
		}
		if (indexI >= 0 && indexJ < board[i].length && board[indexI][indexJ] == player) {
			res.addAll(points);
		}
		points.removeAll(points);
		// check down left

		indexI = i + 1;
		indexJ = j - 1;
		while (indexI < board[i].length - 1 && indexJ > 0 && board[indexI][indexJ] == opponentPlayer) {
			points.add(new Point(indexI, indexJ));
			indexI++;
			indexJ--;
		}
		if (indexI < board.length && indexJ >= 0 && board[indexI][indexJ] == player) {
			res.addAll(points);
		}
		points.removeAll(points);
		// check down right
		indexI = i + 1;
		indexJ = j + 1;
		while (indexI < board.length - 1 && indexJ < board.length - 1 && board[indexI][indexJ] == opponentPlayer) {
			points.add(new Point(indexI, indexJ));
			indexI++;
			indexJ++;
		}
		if (indexI < board.length && indexJ < board.length && board[indexI][indexJ] == player) {
			res.addAll(points);
		}
		points.removeAll(points);
		return res;

	}

	public static boolean canMove(int[][] board, int player, int i, int j) {// kiểm tra xem vị trí này có thể chọn hay
																			// không
		if (board[i][j] != 0)
			return false;
		int opponentPlayer = (player == 1) ? 2 : 1;

		int indexI = i - 1;
		int indexJ = j;
		int count = 0;
		// check up
		while (indexI > 0 && board[indexI][indexJ] == opponentPlayer) {
			count++;
			indexI--;
		}
		if (indexI >= 0&&board[indexI][indexJ] == player && count > 0)
			return true;
		// check down
		indexI = i + 1;
		indexJ = j;
		count = 0;
		while (indexI < board.length-1 && board[indexI][indexJ] == opponentPlayer) {
			count++;
			indexI++;
		}
		if (indexI < board.length&&board[indexI][indexJ] == player && count > 0)
			return true;

		// check left
		indexI = i;
		indexJ = j - 1;
		count = 0;
		while (indexJ > 0 && board[indexI][indexJ] == opponentPlayer) {
			indexJ--;
			count++;
		}
		if (indexJ >= 0 && board[indexI][indexJ] == player && count > 0)
			return true;

		// check right
		indexI = i;
		indexJ = j + 1;
		count = 0;
		while (indexJ < board[i].length-1 && board[indexI][indexJ] == opponentPlayer) {
			indexJ++;
			count++;
		}
		if (count > 0&&indexJ < board[i].length&&board[indexI][indexJ] == player)
			return true;

		// check up left
		indexI = i - 1;
		indexJ = j - 1;
		count = 0;
		while (indexI > 0 && indexJ > 0 && board[indexI][indexJ] == opponentPlayer) {
			indexI--;
			indexJ--;
			count++;
		}
		if (indexI >= 0 && indexJ >= 0 && board[indexI][indexJ] == player && count > 0)
			return true;
		// check up right
		indexI = i - 1;
		indexJ = j + 1;
		count = 0;
		while (indexI > 0 && indexJ < board[i].length - 1 && board[indexI][indexJ] == opponentPlayer) {
			indexI--;
			indexJ++;
			count++;
		}
		if (indexI >= 0 && indexJ < board[i].length && board[indexI][indexJ] == player && count > 0)
			return true;
		// check down left

		indexI = i + 1;
		indexJ = j - 1;
		count = 0;
		while (indexI < board[i].length - 1 && indexJ > 0 && board[indexI][indexJ] == opponentPlayer) {
			indexI++;
			indexJ--;
			count++;
		}
		if ( indexJ >= 0 && indexI < board.length &&board[indexI][indexJ] == player && count > 0)
			return true;

		// check down right
		indexI = i + 1;
		indexJ = j + 1;
		count = 0;
		while (indexI < board.length - 1 && indexJ < board.length - 1 && board[indexI][indexJ] == opponentPlayer) {
			indexI++;
			indexJ++;
			count++;
		}
		if (  count > 0&&indexI < board.length&& indexJ < board.length&&board[indexI][indexJ] == player)
			return true;
		return false;

	}
}
