package entitypart.parts;

import javax.xml.bind.annotation.XmlRootElement;

import entitypart.epf.Part;

/**
 * Flag for if an entity can fly or not.
 * @author David Chen
 *
 */
@XmlRootElement
public class FlyingPart extends Part {
	
	public FlyingPart() {
	}
	
}
