package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.BoardController;
import model.BoardHelp;

public class GamePanel extends JPanel {
	public BoardCell[][] cells;
	public int player1 = 1, player2 = 2, curentPlayer = player2;
	// đen là 2 trắng là 1
	JLabel lbScore1, lbScore2, lbTime, lbTurnOf;
	int score1 = 2, score2 = 2, seconds = 0;
	Timer timer = new Timer();
	Font font;

	public GamePanel(int rows, int cols) {
		this.setBackground(Color.white);
		font = new Font("Arial", Font.BOLD, 25);
		this.setLayout(new BorderLayout());
		JPanel panelBoard = new JPanel();
		panelBoard.setLayout(new GridLayout(rows, cols));
		panelBoard.setPreferredSize(new Dimension(600, 600));
		panelBoard.setBackground(new Color(42, 138, 89));
		cells = new BoardCell[rows][cols];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				cells[i][j] = new BoardCell(i, j, this, 0);
				panelBoard.add(cells[i][j]);
			}
		}

		JPanel sideBar = new JPanel();
		sideBar.setLayout(new BoxLayout(sideBar, BoxLayout.Y_AXIS));
		sideBar.setPreferredSize(new Dimension(300, 0));
		lbScore1 = new JLabel();
		lbScore2 = new JLabel();
		lbTurnOf = new JLabel("Turn of player: " + (curentPlayer == 1 ? "White" : "Black"));
		lbScore1.setText("Score Player White: " + score1);
		lbScore1.setForeground(Color.BLUE);
		lbScore2.setText("Score Player Black: " + score2);
		lbScore2.setForeground(Color.red);
		lbTime = new JLabel();
		lbScore1.setFont(font);
		lbScore2.setFont(font);
		lbTime.setFont(font);
		lbTurnOf.setFont(font);
		sideBar.add(lbScore1);
		sideBar.add(lbScore2);
		sideBar.add(lbTurnOf);
		sideBar.add(lbTime);

		this.add(sideBar, BorderLayout.WEST);

		this.add(panelBoard, BorderLayout.CENTER);
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				seconds++;
				lbTime.setText("" + seconds);
			}
		};
		timer.schedule(task, 0, 1000);
	}

	public void update(int[][] board, int playerNext, int indexI, int indexJ) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				cells[i][j].player = board[i][j];
				cells[i][j].canCheck = false;
				cells[i][j].highLight = false;

				if (i == indexI && j == indexJ) {
					cells[i][j].check = true;
					cells[i][j].newTouch = true;
				}

				else {
					cells[i][j].newTouch = false;
				}
			}
		}
		curentPlayer = playerNext;
		if(curentPlayer!=2) {
			for (Point point : BoardController.getPointsCanMove(playerNext)) {
				
				cells[point.x][point.y].highLight = true;
				cells[point.x][point.y].canCheck = true;
			}
			;
		}
	
		
		lbTurnOf.setText("Turn of player: " + ((curentPlayer == 1) ? "White" : "Black"));
		lbScore1.setText("Score Player White: " + BoardController.getPointOfPlayer(player1));
		lbScore2.setText("Score Player Black: " + BoardController.getPointOfPlayer(player2));

		this.repaint();
		if (curentPlayer == 2) {
			Timer timerBot = new Timer();
			TimerTask timerTask = new TimerTask() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					BoardController.AIPlay(curentPlayer);

				}
			};
			timer.schedule(timerTask, 1000);
		}

	}

	public void draw(Graphics2D g) {

	}

	public void updatePlayer(int player, int indexI, int indexJ) {

		BoardController.updateMoveFromView(player, indexI, indexJ);
		this.curentPlayer = (player == 1) ? 2 : 1;
	}

	public void startBoard(int[][] board) {// tạo bàn đầu game
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] != 0) {
					cells[i][j].check = true;
					cells[i][j].player = board[i][j];

				}
			}
		}
		for (Point point : BoardController.getPointsCanMove(curentPlayer)) {
			cells[point.x][point.y].highLight = true;
			cells[point.x][point.y].canCheck = true;
		}
		;

		this.repaint();
		Timer timerT = new Timer();
		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				BoardController.AIPlay(curentPlayer);

			}
		};
		timerT.schedule(task, 1000);

	}
	public void updateWin() {
		System.out.println("Win");
	}

}
