package controller;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import model.Board;
import model.BoardHelp;
import model.Player;
import model.Player1;
import model.Player2;
import view.GamePanel;

public class BoardGameController implements ActionListener {
	static GamePanel view;
	static Board board;
	static BoardGameController boardGameController;

	public static void start() {
		updateCurrentPlayer(board.currentPlayer);
		updateScore(0, 0);
		if (board.currentPlayer.isAI) {
			board.AIPLay();

		} else {
			board.updatePointCanMove();
		}
	}

	public static void updateWinner(Player player) {
		
view.updateWinner(player.isAI ? "AI win" : "People win");
	}

	public static void updateBoard(int row, int col) {
		for (int i = 0; i < board.board.length; i++) {
			for (int j = 0; j < board.board[i].length; j++) {
				view.updatePiecesView(board.board[i][j].color, i, j);
				view.boardView[i][j].resetStatus();

				if (i == row & j == col) {
					view.boardView[i][j].newTouch = true;
					view.boardView[i][j].check = true;
				}
			}
		}
		view.repaint();
	}

	public void setPiecesStartGame() {
		for (int i = 0; i < board.board.length; i++) {
			for (int j = 0; j < board.board[i].length; j++) {
				if (board.board[i][j].color != null) {
					view.setPiecesStart(board.board[i][j].color, i, j);
				}

			}
		}

	}

	public BoardGameController(GamePanel view) {
		this.view = view;
		view.getBtnReplay().addActionListener(this);
		board = new Board(8, 8);
		setPiecesStartGame();
		boardGameController = this;
		start();
	}

	public static BoardGameController getInstance() {
		return boardGameController;
	}

	public static void updatePointCanMove(List<Point> points) {
		for (Point point : points) {
			view.updatePointCanMoveView(point);
		}

	}

	public static void updateScore(int score1, int score2) {
		// TODO Auto-generated method stub
		view.getLbScore1().setText("Score " + (Player1.getInstance().isAI ? "AI" : "People") + ": " + score1);
		view.getLbScore2().setText("Score " + (Player2.getInstance().isAI ? "AI" : "People") + ": " + score2);

	}

	public static void updateCurrentPlayer(Player currentPlayer) {
		view.getLbTurnOf().setText("Turn of player: " + (currentPlayer.isAI ? "AI         " : "People "));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(view.getBtnReplay())) {
			System.out.println("dang khoi dong lai");
			boolean ai = board.currentPlayer.isAI;
			boolean isGameOver = board.isGameOver();
			board.getNewBoard(8, 8);
			board.currentPlayer = board.firstPlayer;
			view.resetStatusBoard();
			setPiecesStartGame();

			if (((!ai) && (board.currentPlayer.isAI)) || isGameOver) {

				start();
			}
			view.repaint();
		}
	}

}
