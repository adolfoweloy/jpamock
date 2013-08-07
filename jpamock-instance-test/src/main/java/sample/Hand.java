package sample;

import java.util.List;

public class Hand {

	public Boolean hasSkills;
	public List<Finger> fingers;
	public Arm arm;

	public Boolean getHasSkills() {
		return hasSkills;
	}

	public void setHasSkills(Boolean hasSkills) {
		this.hasSkills = hasSkills;
	}

	public List<Finger> getFingers() {
		return fingers;
	}

	public void setFingers(List<Finger> fingers) {
		this.fingers = fingers;
	}

	public Arm getArm() {
		return arm;
	}

	public void setArm(Arm arm) {
		this.arm = arm;
	}

}
