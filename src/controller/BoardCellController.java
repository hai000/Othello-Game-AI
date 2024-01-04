package controller;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import model.Board;
import view.BoardCell;

public class BoardCellController extends MouseAdapter{
	BoardCell boardCell;

	public BoardCellController(BoardCell boardCell) {
		this.boardCell = boardCell;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		boardCell.colorPieces = Board.currentPlayer.pieces.color;
		touch();

	}

	public void setPiecesStart() {
		boardCell.check = true;
		boardCell.repaint();
		boardCell.canCheck = false;
	}

	public void touch() {
		if (Board.canSetPieces(boardCell.row, boardCell.col)) {
			boardCell.check = true;
			boardCell.newTouch = true;
			boardCell.repaint();
			Board.setPieces(boardCell.row, boardCell.col);
		}

	}
}
