package entitypart.parts;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import entitypart.epf.Part;

/**
 * Keeps track if entity's lifetime is greater than it's max life.  Then, it should die.
 * @author David Chen
 *
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class TimedDeathPart extends Part {

	@XmlElement
	private float maxLife;
	@XmlTransient
	private float life = 0;
	
	public TimedDeathPart() {
	}
	
	public TimedDeathPart(float maxLife) {
		this.maxLife = maxLife;
	}
	
	public boolean isDead() {
		return life >= maxLife;
	}
	
	@Override
	public void initialize() {
		this.life = 0;
	}
	
	@Override
	public void update(float dt) {
		this.life += dt;
	}
	
}
