package universe;

import java.util.ArrayList;

public abstract class CelestialBody {
	protected CelestialBody parent;
	protected ArrayList<CelestialBody> bodies;
	protected String name;
	
	public CelestialBody() {
		this.bodies = new ArrayList<CelestialBody>();
		initialize();
	};
	
	public CelestialBody(CelestialBody parent, String name) {
		this.parent = parent;
		this.name = name;
		this.bodies = new ArrayList<CelestialBody>();
		initialize();
	}
	
	protected abstract void initialize();
	
	public String getName() {
		return this.name;
	}
	
	public ArrayList<CelestialBody> getBodies() {
		return this.bodies;
	}
}
