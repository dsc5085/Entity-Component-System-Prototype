package entitypart.systems;

import java.util.List;

import entitypart.epf.Entity;
import entitypart.epf.EntityManager;
import entitypart.items.HealSpell;
import entitypart.items.Spell;
import entitypart.items.Weapon;
import entitypart.parts.Alliance;
import entitypart.parts.AlliancePart;
import entitypart.parts.DescriptionPart;
import entitypart.parts.EquipmentPart;
import entitypart.parts.HealthPart;
import entitypart.parts.ManaPart;
import entitypart.parts.Mentality;
import entitypart.parts.MentalityPart;

public class BattleSystem {
	
	private EntityManager entityManager;
	
	public BattleSystem(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public void update() {
		List<Entity> characters = entityManager.getAll();
		
		for (Entity entity : characters) {
			if (!entity.get(HealthPart.class).isAlive()) {
				entityManager.remove(entity);
				System.out.println(entity.get(DescriptionPart.class).getName() + " is dead!");
			}
			if (entity.isActive()) {
				System.out.println(entity.get(DescriptionPart.class).getName() + " - Health: "
						+ entity.get(HealthPart.class).getHealth() + " - Mana: " + 
						+ entity.get(ManaPart.class).getMana());
				act(entity, characters);
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
	}
	
	private void attemptAttack(Entity actingCharacter, List<Entity> characters) {
		Alliance alliance = actingCharacter.get(AlliancePart.class).getAlliance();
		
		for (Entity character : characters) {
			if (character.get(HealthPart.class).isAlive()) {
				Alliance characterAlliance = character.get(AlliancePart.class).getAlliance();
				if (characterAlliance != alliance) {
					Weapon weapon = actingCharacter.get(EquipmentPart.class).getWeapon();
					weapon.attack(character);
					break;
				}
			}
		}
	}
	
	private boolean attemptHeal(Entity actingCharacter, List<Entity> characters) {
		Alliance alliance = actingCharacter.get(AlliancePart.class).getAlliance();
		ManaPart manaPart = actingCharacter.get(ManaPart.class);
		Spell healSpell = actingCharacter.get(EquipmentPart.class).getSpell(HealSpell.class);
		boolean healed = false;
		Entity target = null;
		
		if (healSpell != null && manaPart.getMana() >= healSpell.getCost()) {
			for (Entity character : characters) {
				if (character.get(HealthPart.class).isAlive()) {
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
	
}
