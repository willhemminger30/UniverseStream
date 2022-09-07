package universe.system;

import universe.CelestialBody;

public abstract class SystemBody extends CelestialBody{
	protected double mass;
	
	public SystemBody(CelestialBody parent, String name) {
		super(parent, name);
	}
	
	public double getMass() {
		return this.mass;
	}
	
	protected void initialize() {
		initializeMass();
	}
	
	protected abstract void initializeMass();
}
