package entitypart.epf;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Serializable version of {@link Entity}.
 * @author David Chen
 *
 */
@XmlRootElement
public class EntityAdapted {

	@XmlElementWrapper
	@XmlElement(name = "part")
	private List<Part> parts;
	
	public EntityAdapted() {
	}
	
	public EntityAdapted(List<Part> parts) {
		this.parts = parts;
	}

	public List<Part> getParts() {
		return new ArrayList<Part>(parts);
	}
	
}
