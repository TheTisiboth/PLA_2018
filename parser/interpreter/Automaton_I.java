package interpreter;

import java.util.List;
import java.util.ListIterator;
import java.util.Random;

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
		
		if(etat_courant.equals("X")) {
			Random rand = new Random();
			int i = rand.nextInt(behaviours.size());
			b = behaviours.get(i);
		}
		
		while (Iter.hasNext() && b == null) {
			tmp = Iter.next();
			if(tmp.source.equals(etat_courant)) {
				b = tmp;
			}
		}
		
		if(b == null) {
			j.destroy();
			return;
		}
		
		b.step(j, plateau);
	}
}
