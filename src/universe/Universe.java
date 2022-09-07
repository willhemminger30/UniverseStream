package universe;

public class Universe extends CelestialBody{
	
	private int numGalaxies;

	public Universe(int numGalaxies) {
		this.name = "";
		this.parent = null;
		this.numGalaxies = numGalaxies;
		
		initialize();
	}
	protected void initialize() {
		for(int i = 0; i < numGalaxies; i++) {
			this.bodies.add(new Galaxy(this, name + "G" + i));
		}
	};
	
}
