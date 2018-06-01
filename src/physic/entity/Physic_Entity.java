package physic.entity;

import mvc.Entity;

public abstract class Physic_Entity extends Entity{
	
		public Physic_Entity(int x,int y){
			// le boolean true correspond à la colision (activé)
			super(x,y,true);
		}
		
}
