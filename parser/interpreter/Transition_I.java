package interpreter;

import mvc.Case;
import physic.entity.Physic_Entity;

public class Transition_I {
	Condition_I cond;
	Action_I act;
	String target;

	public Transition_I(Condition_I condition, Action_I action, String target) {
		this.target = target;
		cond = condition;
		act = action;
	}
	
	public boolean eval(Physic_Entity j, Case[][] plateau) {
		if(cond.eval(j, plateau)) {
			act.exec(j, plateau);
			j.setEtatCourant(target);
			return true;
		}
		return false;
	}

}
