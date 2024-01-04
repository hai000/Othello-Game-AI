package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Point;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.BoardGameController;
import model.Player1;
import model.Player2;

public class GamePanel extends JPanel {
	public BoardCell[][] boardView;
	JLabel  lbTime, lbTurnOf, lbWinner, lbScore1,lbScore2;
	JPanel panelBoard;
	int seconds = 0;
	Timer timer = new Timer();
	JPanel sideBar;
	Font font;
	JButton btnReplay = new JButton("Replay");

	public void initBoardView(int row, int col) {
		boardView = new BoardCell[row][col];
		for (int i = 0; i < boardView.length; i++) {
			for (int j = 0; j < boardView[i].length; j++) {
				boardView[i][j] = new BoardCell(i, j, this, null);
				panelBoard.add(boardView[i][j]);
			}
		}

	}

	public GamePanel(int row, int col) {

		font = new Font("Arial", Font.BOLD, 25);
		this.setLayout(new BorderLayout());
		panelBoard = new JPanel();
		panelBoard.setLayout(new GridLayout(row, col));
		panelBoard.setPreferredSize(new Dimension(600, 600));
		panelBoard.setBackground(new Color(42, 138, 89));
		initBoardView(row, col);
		sideBar = new JPanel();
		sideBar.setBackground(new Color(169,169,169));
		sideBar.setLayout(new BoxLayout(sideBar, BoxLayout.Y_AXIS));
		sideBar.setPreferredSize(new Dimension(300, 0));
		lbScore1 = new JLabel("Score Player 1: " + 0);
		lbScore2 = new JLabel("Score Player 2: " + 0);
		lbTurnOf = new JLabel("Turn of player: " );
		lbScore1.setForeground(Player1.getInstance().pieces.color);
		lbScore2.setForeground(Player2.getInstance().pieces.color);
		lbTime = new JLabel();
		lbWinner = new JLabel();
		lbScore1.setFont(font);
		lbScore2.setFont(font);
		lbTime.setFont(font);
		lbTurnOf.setFont(font);
		lbWinner.setFont(font);
		JPanel jPanel = new JPanel();
		btnReplay.setForeground(Color.YELLOW);
		btnReplay.setBackground(Color.BLACK);
		btnReplay.setOpaque(true);
		btnReplay.setFocusPainted(false);
		btnReplay.setPreferredSize(new Dimension(100, 50));
		jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS) );
		jPanel.add(lbTurnOf);
		jPanel.add(lbTime);
		jPanel.add(btnReplay);
		lbWinner.setText("Winner is: ...");

		sideBar.add(lbScore1);
		sideBar.add(lbScore2);
//		sideBar.add(lbTurnOf);
//		sideBar.add(lbTime);
		sideBar.add(lbWinner);
//		sideBar.add(btnReplay);
		sideBar.add(jPanel);

		this.add(sideBar, BorderLayout.WEST);

		this.add(panelBoard, BorderLayout.CENTER);
//		TimerTask task = new TimerTask() {
//			@Override
//			public void run() {
//				seconds++;
//				lbTime.setText(seconds / 60 + ":" + seconds % 60);
//			}
//		};
//		timer.schedule(task, 0, 1000);

	}

	public void setPiecesStart(Color color, int row, int col) {
		boardView[row][col].colorPieces = color;
		boardView[row][col].bcellcontroller.setPiecesStart();
	}

	public void updatePiecesView(Color color, int row, int col) {
		boardView[row][col].colorPieces = color;
	}

	public void resetStatusBoard() {
		for (int i = 0; i < boardView.length; i++) {
			for (int j = 0; j < boardView.length; j++) {
				boardView[i][j].check = false;
				boardView[i][j].canCheck = false;
				boardView[i][j].newTouch = false;
				boardView[i][j].colorPieces = null;
				boardView[i][j].highLight = false;
			}
		}
	}

	public void resetBoardView() {

	}

	public void updatePointCanMoveView(Point point) {
		boardView[point.x][point.y].highLight = true;

	}

	public BoardCell[][] getBoardView() {
		return boardView;
	}

	public void setBoardView(BoardCell[][] boardView) {
		this.boardView = boardView;
	}

	public JLabel getLbScore1() {
		return lbScore1;
	}

	public void setLbScore1(JLabel lbScore1) {
		this.lbScore1 = lbScore1;
	}

	public JLabel getLbScore2() {
		return lbScore2;
	}

	public void setLbScore2(JLabel lbScore2) {
		this.lbScore2 = lbScore2;
	}

	public JLabel getLbTime() {
		return lbTime;
	}

	public void setLbTime(JLabel lbTime) {
		this.lbTime = lbTime;
	}

	public JLabel getLbTurnOf() {
		return lbTurnOf;
	}

	public void setLbTurnOf(JLabel lbTurnOf) {
		this.lbTurnOf = lbTurnOf;
	}

	public JLabel getLbWinner() {
		return lbWinner;
	}

	public void setLbWinner(JLabel lbWinner) {
		this.lbWinner = lbWinner;
	}

	public JPanel getPanelBoard() {
		return panelBoard;
	}

	public void setPanelBoard(JPanel panelBoard) {
		this.panelBoard = panelBoard;
	}

	public int getSeconds() {
		return seconds;
	}

	public void setSeconds(int seconds) {
		this.seconds = seconds;
	}

	public JPanel getSideBar() {
		return sideBar;
	}

	public void setSideBar(JPanel sideBar) {
		this.sideBar = sideBar;
	}

	public JButton getBtnReplay() {
		return btnReplay;
	}

	public void setBtnReplay(JButton btnReplay) {
		this.btnReplay = btnReplay;
	}

	public void updateWinner(String player) {
		// TODO Auto-generated method stub
		lbWinner.setText("Winner is :"+player);
		
	}

}
