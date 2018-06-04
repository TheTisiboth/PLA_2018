package no.physic.entity;

import java.awt.Graphics;

public class Portal extends No_Physic_Entity{
	
	private int x_exit;
	private int y_exit;
	
	public Portal(int x, int y, int xe, int ye) {
		super(x, y);
		y_exit =ye;
		x_exit =xe;
	}

	@Override
	public void paint(Graphics g) {
		// TODO Auto-generated method stub
		
	}

}
