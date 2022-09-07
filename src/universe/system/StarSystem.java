package universe.system;
import universe.CelestialBody;
import universe.Galaxy;

public class StarSystem extends CelestialBody{
	
	public StarSystem(Galaxy parent, String name) {
		super(parent, name);
	}
	
	protected void initialize() {
		int numStars = (int) (Math.random() * 3) + 1; //can achieve trinary star systems
		
		for(int i = 0; i < numStars; i++) {
			this.bodies.add(new Star(this, name + "-S" + i));
		}
		
	}

}
