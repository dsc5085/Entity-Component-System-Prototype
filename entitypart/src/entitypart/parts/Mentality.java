package entitypart.parts;

import javax.xml.bind.annotation.XmlEnum;

/**
 * Determines A.I. approach.
 * @author David Chen
 *
 */
@XmlEnum
public enum Mentality {
	/**
	 * Only attack with weapon.
	 */
	OFFENSIVE,
	/**
	 * Try to heal.  Else, attack with weapon.
	 */
	SUPPORT,
	/**
	 * Try to summon.  Else, attack with weapon.
	 */
	SUMMON
}
