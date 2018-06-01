package no.physic.entity;

public class Portal extends No_Physic_Entity{
	
	private int x_exit;
	private int y_exit;
	
	public Portal(int x, int y, int xe, int ye) {
		super(x, y);
		y_exit =ye;
		x_exit =xe;
	}

}
