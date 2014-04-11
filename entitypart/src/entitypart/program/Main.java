package entitypart.program;

import java.util.ArrayList;
import java.util.List;

import entitypart.epf.Entity;
import entitypart.epf.EntityManager;
import entitypart.parts.Alliance;
import entitypart.systems.BattleSystem;

public class Main {

	// main entry to the game application
	public static void main(String[] args) throws InterruptedException
	{
		EntityManager entityManager = new EntityManager();
		BattleSystem battleSystem = new BattleSystem(entityManager);
		
		List<Entity> characters = createCharacters();
		entityManager.addAll(characters);
		
		float time = 0;
		
		// very basic game loop
		while (true) {
			float delta = 1;
			System.out.println();
			System.out.println("SIMULATION TIME: " + time);
			
			for (Entity entity : entityManager.getAll()) {
				entity.update(delta);
			}
			entityManager.update();
			battleSystem.update();
			time += delta;
			Thread.sleep(1000);
		}
	}
	
	private static List<Entity> createCharacters() {
		List<Entity> characters = new ArrayList<Entity>();
		
		// create monsters
		characters.add(CharacterFactory.createMeleer("M.Meleer1", Alliance.MONSTERS));
		characters.add(CharacterFactory.createRanger("M.Ranger1", Alliance.MONSTERS));
		characters.add(CharacterFactory.createFlyingRanger("M.FlyingRanger1", Alliance.MONSTERS));
		characters.add(CharacterFactory.createSupportMage("M.SupportMage1", Alliance.MONSTERS));
		
//		// create villagers
		characters.add(CharacterFactory.createMeleer("V.Meleer1", Alliance.VILLAGERS));
		characters.add(CharacterFactory.createRanger("V.Ranger1", Alliance.VILLAGERS));
		characters.add(CharacterFactory.createFlyingRanger("V.FlyingRanger1", Alliance.VILLAGERS));
		characters.add(CharacterFactory.createSupportMage("V.SupportMage1", Alliance.VILLAGERS));
		
		return characters;
	}
}
