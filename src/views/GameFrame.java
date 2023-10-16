package views;

import javax.swing.JFrame;

import controller.BoardController;

public class GameFrame extends JFrame {
	public GameFrame() {
		GamePanel gamePanel = new GamePanel(8, 8);
		BoardController boardController = new BoardController(gamePanel);
		gamePanel.startBoard(boardController.boardGame.board);
		this.add(gamePanel);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new GameFrame();
	}
}
