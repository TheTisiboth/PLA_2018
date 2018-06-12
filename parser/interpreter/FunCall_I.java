package interpreter;

import java.util.List;

import physic.entity.Physic_Entity;

public class FunCall_I extends Expression_I {
	String name;
	List<String> parameters;

	public FunCall_I(String name, List<String> parameters) {
		this.name = name;
		this.parameters = parameters;
	}

	@Override
	public boolean eval(Physic_Entity j) {
		switch (name) {
		case "True":
			return true;
		case "GotPower":
			return j.gotPower();
		case "Key":
			return j.key(parameters.get(0));
		case "MyDir":
			return j.myDir(parameters.get(0));
		case "Cell":
			if (parameters.size() == 2) {
				return j.cell(parameters.get(0), parameters.get(1));
			} else {
				return false;
			}
		case "Closest":
			if (parameters.size() == 2) {
				return j.closest(parameters.get(0), parameters.get(1));
			} else {
				return false;
			}
		case "GotStuff":
			return j.gotStuff();
		default:
			return false;
		}
	}

	@Override
	public void exec(Physic_Entity j) {
		switch (name) {
		case "Wizz":
			j.wizz();
			break;
		case "Pop":
			j.pop();
			break;
		case "Move":
			if (parameters.size() == 0) {
				j.move("F");
				break;
			} else {
				j.move(parameters.get(0));
				break;
			}
		case "Turn":
			j.turn();
			break;
		case "Jump":
			j.jump();
			break;
		case "Hit":
			j.hit();
			break;
		case "Protect":
			j.protect();
			break;
		case "Pick":
			j.pick();
			break;
		case "Throw":
			j.jeter();
			break;
		case "Store":
			j.store();
			break;
		case "Get":
			j.get();
			break;
		case "Power":
			j.power();
			break;
		case "Kamikaze":
			j.kamikaze();
			break;
		default:
			return;
		}
	}

}
