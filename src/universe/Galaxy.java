package universe;

import universe.system.StarSystem;

public class Galaxy extends CelestialBody{
	
	
	public Galaxy(CelestialBody parent, String name) {
		super(parent, name);
	}
	
	protected void initialize() {
		int numSystems = (int)(Math.random() * 5) + 5; // some random amount between 5 and 10 (for now)
		for(int i = 0; i < numSystems; i++) {
			this.bodies.add(new StarSystem(this, name + "-SS" + i));
		}
	}

}
