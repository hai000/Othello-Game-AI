package controller;

import java.awt.Point;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.Board;
import model.BoardHelp;
import model.MinMax;
import views.GamePanel;

public class BoardController {
	static GamePanel boardGameView;
	public static Board boardGame;

	public BoardController(GamePanel boardGameView) {
		this.boardGameView = boardGameView;
		boardGame = new Board(8, 8);
		init();
	}

	public static void updateMoveFromView(int player, int indexI, int indexJ) {

		boardGame.board = BoardHelp.getBoardAfterMove(boardGame.board, player, new Point(indexI, indexJ));

		if (BoardHelp.hasMoveAny(boardGame.board, (player == 1 ? 2 : 1))) {
			boardGameView.update(boardGame.board, (player == 1 ? 2 : 1), indexI, indexJ);
		} else {
			System.out.println(player == 1 ? "White" : "Black" + " được thêm lượt");
			boardGameView.update(boardGame.board, player, indexI, indexJ);
		}

	}

	public static int getPointOfPlayer(int player) {
		return BoardHelp.getToal(boardGame.board, player);
	}

	public static List<Point> getPointsCanMove(int player) {
		return BoardHelp.getPointsCanMove(boardGame.board, player);
	}

	public void init() {

	}

	public static boolean isWin() {
		return BoardHelp.isGameOver(boardGame.board);
	}

	public static void notifyWin() {
		boardGameView.updateWin(
				BoardHelp.getToal(boardGame.board, 1) > BoardHelp.getToal(boardGame.board, 2) ? "You Win" : "You Lose");
	}

	public static void AIPlay(int player) {

		Point point = MinMax.findBestMove(boardGame, false, player);
		boardGame.board = BoardHelp.getBoardAfterMove(boardGame.board, player, point);

		if (BoardHelp.hasMoveAny(boardGame.board, (player == 1 ? 2 : 1))) {
			boardGameView.update(boardGame.board, (player == 1 ? 2 : 1), point.x, point.y);
		} else {
			System.out.println(player == 1 ? "White" : "Black" + " được thêm lượt");
			boardGameView.update(boardGame.board, player, point.x, point.y);
		}

	}

	public static void reset() {
		// TODO Auto-generated method stub
		boardGame.getNewBoard(8, 8);
		boardGameView.startBoard(boardGame.board);

	}

}
