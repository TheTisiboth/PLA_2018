package physic.entity;

public class Obstacle extends Physic_Entity{
	
	private int health_point;
	
	public Obstacle(int x, int y,int hp) {
		super(x, y);
		health_point = hp;
	}

}
