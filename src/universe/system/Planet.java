package universe.system;

public class Planet extends SystemBody{
	
	public Planet(Star parent, String name) {
		super(parent, name);
	}
	
	public void initialize() {
		int numMoons = (int) (Math.random() * 5);
		
		for(int i = 0; i < numMoons; i++) {
			this.bodies.add(new Moon(this, name + "-M" + i));
		}
		
		initializeMass();
	}
	
	protected void initializeMass() {
		//initialize mass in Earth masses, min is 0.5, max just under 5.5
		this.mass = Math.random() * 5 + 0.5;
	}
	
}
