package universe.system;

public class Moon extends SystemBody{
	
	public Moon(Planet parent, String name) {
		super(parent, name);
	}

	protected void initializeMass() {
		// initialize mass in earth masses, maximum roughly 1/5 size of parent body
		this.mass =  Math.random() * ((((SystemBody)parent).getMass() + 0.01) / 5.0);
	}
}
