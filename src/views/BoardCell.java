package views;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BoardCell extends JLabel implements MouseListener {
	int i;// row
	int j;// col
	boolean check, highLight, newTouch,canCheck;

	GamePanel gamePanel;
	int player = 0;

	public BoardCell(int i, int j, GamePanel gamePanel, int player) {
		this.i = i;
		this.j = j;
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.addMouseListener(this);
		this.gamePanel = gamePanel;
		this.player = player;
		if (player != 0) {
			check = true;
			repaint();
		}

	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		if (player == 1) {
			g2.setColor(Color.white);
		} else if (player == 2) {
			g2.setColor(Color.black);

		}

		if (check) {//vẽ cờ
			g2.fillOval(3, 3, this.getWidth()-6, this.getHeight()-6);
		}
		if (newTouch) {
			g2.setColor(Color.red);
			g2.fillOval(this.getWidth()/2-5, 5, 10, 10);
		}
		if (highLight) {
			g2.setColor(Color.yellow);
			g2.drawRect(4, 4, this.getWidth()-8, this.getHeight()-8);
		}
		super.paint(g);
	}

	public void touch() {
		if (canCheck) {
			check = true;
			newTouch=true;
			repaint();
			gamePanel.updatePlayer(player, i, j);
			canCheck=false;
		}
	}

	public void setPlayer(int player) {
		this.player = player;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		player = gamePanel.curentPlayer;
		touch();

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public void reset() {
		// TODO Auto-generated method stub
		this.player=0;
		canCheck=false;
		check=false;
		newTouch=false;
		this.repaint();
		
	}

}
