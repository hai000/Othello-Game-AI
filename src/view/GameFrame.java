package view;

import javax.swing.JFrame;

import controller.BoardGameController;

public class GameFrame extends JFrame {
	public GameFrame() {
		GamePanel view = new GamePanel(8, 8);
		BoardGameController boardController = new BoardGameController(view);
		this.setContentPane(view);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new GameFrame();
	}
}
