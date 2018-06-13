package interpreter;

import java.util.List;

import mvc.Case;
import physic.entity.Physic_Entity;

public class FunCall_I extends Expression_I {
	String name;
	List<String> parameters;

	public FunCall_I(String name, List<String> parameters) {
		this.name = name;
		this.parameters = parameters;
	}

	@Override
	public boolean eval(Physic_Entity j, Case[][] plateau) {
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
				return j.cell(parameters.get(0), parameters.get(1), plateau);
			} else {
				return false;
			}
		case "Closest":
			if (parameters.size() == 2) {
				return j.closest(parameters.get(0), parameters.get(1), plateau);
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
	public void exec(Physic_Entity j, Case[][] plateau) {
		switch (name) {
		case "Wizz":
			j.wizz(plateau);
			break;
		case "Pop":
			j.pop(plateau);
			break;
		case "Move":
			if (parameters.size() == 0) {
				j.move("F", plateau);
				break;
			} else {
				j.move(parameters.get(0), plateau);
				break;
			}
		case "Turn":
			j.turn(plateau);
			break;
		case "Jump":
			if (parameters.size() == 0) {
				j.jump("F", plateau);
				break;
			} else {
				j.jump(parameters.get(0), plateau);
				break;
			}
		case "Hit":
			j.hit(plateau);
			break;
		case "Protect":
			j.protect(plateau);
			break;
		case "Pick":
			j.pick(plateau);
			break;
		case "Throw":
			j.jeter(plateau);
			break;
		case "Store":
			j.store(plateau);
			break;
		case "Get":
			j.get(plateau);
			break;
		case "Power":
			j.power(plateau);
			break;
		case "Kamikaze":
			j.kamikaze(plateau);
			break;
		default:
			return;
		}
	}

}
