package model;

import java.awt.Color;

public abstract class Player {
public Pieces pieces = new Pieces(0, null);
public static IModelAI modelAI = new MiniMaxAI();
public boolean isAI=false;
public  void setColor(Color color) {
	pieces.color=color;
}
}
