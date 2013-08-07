package sample;

public class Leg {
	public Float diameter;
	public Foot foot;

	public Float getDiameter() {
		return diameter;
	}

	public void setDiameter(Float diameter) {
		this.diameter = diameter;
	}

	public Foot getFoot() {
		return foot;
	}

	public void setFoot(Foot foot) {
		this.foot = foot;
	}

	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}

	public Body body;
}
