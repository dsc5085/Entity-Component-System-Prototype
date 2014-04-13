package entitypart.systems;

import java.util.List;

import entitypart.epf.Entity;
import entitypart.epf.EntityManager;
import entitypart.items.HealSpell;
import entitypart.items.SummonSpell;
import entitypart.items.Weapon;
import entitypart.parts.Alliance;
import entitypart.parts.AlliancePart;
import entitypart.parts.DescriptionPart;
import entitypart.parts.EquipmentPart;
import entitypart.parts.HealthPart;
import entitypart.parts.ManaPart;
import entitypart.parts.Mentality;
import entitypart.parts.MentalityPart;
import entitypart.parts.TimedDeathPart;
import entitypart.util.EventManager;

public class BattleSystem {
	
	private EventManager eventManager;
	private EntityManager entityManager;
	
	public BattleSystem(EventManager eventManager, EntityManager entityManager) {
		this.eventManager = eventManager;
		this.entityManager = entityManager;
	}
	
	public void update() {
		List<Entity> characters = entityManager.getAll();
		
		for (Entity character : characters) {
			if (!isAlive(character)) {
				entityManager.remove(character);
				System.out.println(character.get(DescriptionPart.class).getName() + " is dead!");
			}
			if (character.isActive()) {
				System.out.println(character.get(DescriptionPart.class).getName() + " - Health: "
						+ character.get(HealthPart.class).getHealth() + " - Mana: " + 
						+ character.get(ManaPart.class).getMana());
				act(character, characters);
			}
		}
	}
	
	public void act(Entity actingCharacter, List<Entity> characters) {
		Mentality mentality = actingCharacter.get(MentalityPart.class).getMentality();
		
		if (mentality == Mentality.OFFENSIVE) {
			attemptAttack(actingCharacter, characters);
		}
		else if (mentality == Mentality.SUPPORT) {
			boolean healed = attemptHeal(actingCharacter, characters);
			if (!healed) {
				attemptAttack(actingCharacter, characters);
			}
		}
		else if (mentality == Mentality.SUMMON) {
			boolean summoned = attemptSummon(actingCharacter);
			if (!summoned) {
				attemptAttack(actingCharacter, characters);
			}
		}
	}
	
	private void attemptAttack(Entity actingCharacter, List<Entity> characters) {
		Alliance alliance = actingCharacter.get(AlliancePart.class).getAlliance();
		
		for (Entity character : characters) {
			if (isAlive(character)) {
				Alliance characterAlliance = character.get(AlliancePart.class).getAlliance();
				if (characterAlliance != alliance) {
					Weapon weapon = actingCharacter.get(EquipmentPart.class).getWeapon();
					if (weapon.canAttack(character)) {
						weapon.attack(character);
						break;
					}
				}
			}
		}
	}
	
	private boolean attemptHeal(Entity actingCharacter, List<Entity> characters) {
		Alliance alliance = actingCharacter.get(AlliancePart.class).getAlliance();
		ManaPart manaPart = actingCharacter.get(ManaPart.class);
		HealSpell healSpell = actingCharacter.get(EquipmentPart.class).getSpell(HealSpell.class);
		boolean healed = false;
		Entity target = null;
		
		if (healSpell != null && manaPart.getMana() >= healSpell.getCost()) {
			for (Entity character : characters) {
				if (isAlive(character)) {
					Alliance characterAlliance = character.get(AlliancePart.class).getAlliance();
					if (characterAlliance == alliance) {
						if (isPotentialHealTargetBetter(target, character)) {
							target = character;
						}
					}
				}
			}
		}
		
		if (target != null) {
			healSpell.use(target);
			manaPart.setMana(manaPart.getMana() - healSpell.getCost());
			healed = true;
		}
		
		return healed;
	}
	
	private boolean isPotentialHealTargetBetter(Entity target, Entity potentialTarget) {
		HealthPart potentialTargetHealthPart = potentialTarget.get(HealthPart.class);
		boolean isPotentialTargetBetter = potentialTargetHealthPart.getHealth() < potentialTargetHealthPart.getMaxHealth()
				&& (target == null || potentialTarget.get(HealthPart.class).getHealth()
				< target.get(HealthPart.class).getHealth());
		return isPotentialTargetBetter;
	}
	
	private boolean attemptSummon(Entity actingCharacter) {
		ManaPart manaPart = actingCharacter.get(ManaPart.class);
		SummonSpell summonSpell = actingCharacter.get(EquipmentPart.class).getSpell(SummonSpell.class);
		boolean summoned = false;
		
		if (summonSpell != null && manaPart.getMana() >= summonSpell.getCost()) {
			summonSpell.use(eventManager);
			manaPart.setMana(manaPart.getMana() - summonSpell.getCost());
			summoned = true;
		}
		
		return summoned;
	}
	
	private boolean isAlive(Entity character) {
		boolean timedDeath = character.has(TimedDeathPart.class) && character.get(TimedDeathPart.class).isDead();
		if (character.isActive() && character.get(HealthPart.class).isAlive() && !timedDeath) {
			return true;
		}
		return false;
	}
	
}
