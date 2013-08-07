package sample;

import java.util.List;

public class Body {

	public String skinColor;
	public List<Arm> arms;
	public List<Leg> legs;

	public String getSkinColor() {
		return skinColor;
	}

	public void setSkinColor(String skinColor) {
		this.skinColor = skinColor;
	}

	public List<Arm> getArms() {
		return arms;
	}

	public void setArms(List<Arm> arms) {
		this.arms = arms;
	}

	public List<Leg> getLegs() {
		return legs;
	}

	public void setLegs(List<Leg> legs) {
		this.legs = legs;
	}

}
