package controller;

import java.awt.Point;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import model.Board;
import model.BoardHelp;
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
//		List<Point> lstPoint = 
		boardGameView.update(boardGame.board,(player==1?2:1),indexI,indexJ);
	}
	public static int getPointOfPlayer(int player) {
		return BoardHelp.getToal(boardGame.board, player);
	}
	public static List<Point> getPointsCanMove(int player) {
		return BoardHelp.getPointsCanMove(boardGame.board, player);
	}

	public void init() {
	
	}

}
