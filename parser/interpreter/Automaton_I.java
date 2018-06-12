package interpreter;

import java.util.List;
import java.util.ListIterator;

import mvc.Case;
import physic.entity.Physic_Entity;

public class Automaton_I {
	public String name;
	public String entry;
	List<Behaviour_I> behaviours;

	public Automaton_I(String name, String entry, List<Behaviour_I> behaviours) {
		this.name = name;
		this.entry = entry;
		this.behaviours = behaviours;
	}
	
	public void step(Physic_Entity j, String etat_courant, Case[][] plateau) {
		ListIterator<Behaviour_I> Iter = behaviours.listIterator();
		Behaviour_I b = null;
		Behaviour_I tmp;
		
		while (Iter.hasNext() && b == null) {
			tmp = Iter.next();
			if(tmp.source.equals(etat_courant)) {
				b = tmp;
			}
		}
		b.step(j, plateau);
	}
}
