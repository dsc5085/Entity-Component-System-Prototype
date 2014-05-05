package entitypart.items;

import javax.xml.bind.annotation.XmlEnum;

@XmlEnum
public enum AttackRange {
	/**
	 * Cannot attack flying entities.
	 */
	CLOSE,
	/**
	 * Can attack flying entities.
	 */
	FAR
}
