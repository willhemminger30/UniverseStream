package universe.system;

import universe.CelestialBody;

public class Star extends SystemBody{
	
	public Star(CelestialBody parent, String name) {
		super(parent, name);
	}

	
	protected void initialize() {
		int numPlanets = (int) (Math.random() * 21);
		for(int i = 0; i < numPlanets; i++) {
			this.bodies.add(new Planet(this, name + "-P" + i));
		}
		
		this.initializeMass();
	}
	
	protected void initializeMass() {
		//initialize mass of star, maximum about 500 solar masses
		this.mass = Math.random() * 499.76 + 0.25;
	}

}
