package interpreter;

import java.util.List;
import java.util.ListIterator;

import physic.entity.Physic_Entity;

public class Behaviour_I {
	String source;
	List<Transition_I> transitions;

	public Behaviour_I(String source, List<Transition_I> transitions) {
		this.source = source;
		this.transitions = transitions;
	}
	
	public void step(Physic_Entity j) {
		ListIterator<Transition_I> Iter = transitions.listIterator();
		boolean pass = false;
		
		while (Iter.hasNext() && !pass) {
			Transition_I t = Iter.next();
			pass = t.eval(j);
		}
	}

}
