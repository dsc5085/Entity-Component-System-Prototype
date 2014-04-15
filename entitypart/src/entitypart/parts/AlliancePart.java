package entitypart.parts;

import entitypart.epf.Part;

/**
 * Determines the side an entity is on for A.I. purposes.
 * @author David Chen
 *
 */
public class AlliancePart extends Part {

	private Alliance alliance;
	
	public AlliancePart(Alliance alliance) {
		this.alliance = alliance;
	}
	
	public Alliance getAlliance() {
		return alliance;
	}
	
}
