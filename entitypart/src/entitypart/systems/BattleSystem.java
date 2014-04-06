package entitypart.systems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import entitypart.epf.Entity;
import entitypart.epf.EntityManager;
import entitypart.items.Item;
import entitypart.items.ItemType;
import entitypart.parts.Alliance;
import entitypart.parts.AlliancePart;
import entitypart.parts.DescriptionPart;
import entitypart.parts.EquipmentPart;
import entitypart.parts.HealthPart;

public class BattleSystem {
	
	private Random random = new Random();
	private EntityManager entityManager;
	
	public BattleSystem(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public void update() {
		List<Entity> entities = entityManager.getAll();
		Map<Alliance, List<Entity>> entitiesByAlliance = new HashMap<Alliance, List<Entity>>();
		entitiesByAlliance.put(Alliance.MONSTERS, filter(entities, Alliance.MONSTERS));
		entitiesByAlliance.put(Alliance.VILLAGERS, filter(entities, Alliance.VILLAGERS));
		
		for (Entity entity : entities) {
			if (entity.isActive()) {
				if (entity.has(HealthPart.class) && entity.has(DescriptionPart.class)) {
					if (entity.get(HealthPart.class).getHealth() <= 0) {
						entityManager.remove(entity);
						System.out.println(entity.get(DescriptionPart.class).getName() + " is dead!");
					}
				}
				
				if (entity.has(DescriptionPart.class) && entity.has(AlliancePart.class)
						&& entity.has(EquipmentPart.class)) {
					Item heldItem = entity.get(EquipmentPart.class).getHeldItem();
					List<Entity> targetList = getTargets(entitiesByAlliance, 
							entity.get(AlliancePart.class).getAlliance(), 
							heldItem.getItemType());
					if (targetList.size() > 0) {
						Entity target = targetList.get(random.nextInt(targetList.size()));
						System.out.println(entity.get(DescriptionPart.class).getName()
								+ " used item " + heldItem.getName());
						heldItem.use(target);
					}
				}
			}
		}
	}
	
	private List<Entity> filter(List<Entity> entities, Alliance alliance) {
		List<Entity> filteredEntities = new ArrayList<Entity>();
		for (Entity entity : entities) {
			if (entity.has(AlliancePart.class) && entity.get(AlliancePart.class).getAlliance() == alliance) {
				filteredEntities.add(entity);
			}
		}
		return filteredEntities;
	}
	
	private List<Entity> getTargets(Map<Alliance, List<Entity>> entitiesByAlliance, Alliance targeterAlliance, 
			ItemType itemType) {
		List<Entity> targets = new ArrayList<Entity>();
		
		if (itemType == ItemType.OFFENSIVE) {
			for (Map.Entry<Alliance, List<Entity>> characters : entitiesByAlliance.entrySet()) {
				if (characters.getKey() != targeterAlliance) {
					targets = characters.getValue();
				}
			}
		}
		else if (itemType == ItemType.SUPPORT) {
			for (Map.Entry<Alliance, List<Entity>> characters : entitiesByAlliance.entrySet()) {
				if (characters.getKey() == targeterAlliance) {
					targets = characters.getValue();
				}
			}
		}
		
		return targets;
	}
	
}
