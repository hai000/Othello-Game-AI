package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.BorderFactory;
import javax.swing.JLabel;

import controller.BoardCellController;

public class BoardCell extends JLabel {
	public int row;
	public int col;
	public boolean check, highLight, newTouch, canCheck;
	BoardCellController bcellcontroller = new BoardCellController(this);

	public Color colorPieces;
	GamePanel view;

	public void setColorPieces(Color color) {
		this.colorPieces = color;
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		Graphics2D g2 = (Graphics2D) g;

		g2.setColor(this.colorPieces);
		if (check) {// vẽ cờ
			g2.fillOval(3, 3, this.getWidth() - 6, this.getHeight() - 6);
		}
		if (newTouch) {
			g2.setColor(Color.red);
			g2.fillOval(this.getWidth() / 2 - 5, 5, 10, 10);
		}
		if (highLight) {
			g2.setColor(Color.yellow);
			g2.drawRect(4, 4, this.getWidth() - 8, this.getHeight() - 8);
		}
		super.paint(g);
	}

	public BoardCell(int row, int col, GamePanel view, Color color) {
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.row = row;
		this.col = col;
		this.view = view;
		this.colorPieces = color;
		this.addMouseListener(bcellcontroller);
		if (colorPieces != null) {
			check = true;
			repaint();
		}

	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public boolean isCheck() {
		return check;
	}

	public void setCheck(boolean check) {
		this.check = check;
	}

	public boolean isHighLight() {
		return highLight;
	}

	public void setHighLight(boolean highLight) {
		this.highLight = highLight;
	}

	public boolean isNewTouch() {
		return newTouch;
	}

	public void setNewTouch(boolean newTouch) {
		this.newTouch = newTouch;
	}

	public boolean isCanCheck() {
		return canCheck;
	}

	public void setCanCheck(boolean canCheck) {
		this.canCheck = canCheck;
	}

	public BoardCellController getBcellcontroller() {
		return bcellcontroller;
	}

	public void setBcellcontroller(BoardCellController bcellcontroller) {
		this.bcellcontroller = bcellcontroller;
	}

	public GamePanel getView() {
		return view;
	}

	public void setView(GamePanel view) {
		this.view = view;
	}

	public Color getColorPieces() {
		return colorPieces;
	}

	public void resetStatus() {
		// TODO Auto-generated method stub
		canCheck = false;
		highLight = false;
		newTouch = false;

	}

}
