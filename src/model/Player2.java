package model;

import java.awt.Color;

public class Player2 extends Player{
	
	private Player2() {
		// TODO Auto-generated constructor stub
		pieces = new Pieces(2, Color.white);
		isAI=true;
	}
	static Player2 player2 = new Player2();

	public static Player getInstance() {
		return player2;
	}
	

	
}
