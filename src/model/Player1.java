package model;

import java.awt.Color;

public class Player1 extends Player{

	private Player1() {
		// TODO Auto-generated constructor stub
		pieces = new Pieces(1, Color.black);
		isAI=false;
	}
	static Player player1 = new Player1();
	public static Player getInstance() {
		return player1;
	
	}
}
