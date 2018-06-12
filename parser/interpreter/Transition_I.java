package interpreter;

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
	
	public boolean eval(Physic_Entity j) {
		if(cond.eval(j)) {
			act.exec(j);
			j.setEtatCourant(target);
			return true;
		}
		return false;
	}

}
