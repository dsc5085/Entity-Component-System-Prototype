package entitypart.parts;

import entitypart.epf.Part;

public class TimedDeathPart extends Part {

	private float maxLife;
	private float life;
	
	public TimedDeathPart(float maxLife) {
		this.maxLife = maxLife;
		this.life = 0;
	}
	
	@Override
	public void initialize() {
		this.life = 0;
	}
	
	public boolean isDead() {
		return life >= maxLife;
	}
	
	@Override
	public void update(float dt) {
		this.life += dt;
	}
	
}
