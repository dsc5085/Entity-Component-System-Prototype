package entitypart.epf;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class EntityAdapter extends XmlAdapter<EntityAdapted, Entity> {

	@Override
	public EntityAdapted marshal(Entity entity) throws Exception {
		EntityAdapted entityAdapted = new EntityAdapted(entity.getAll());
		return entityAdapted;
	}

	@Override
	public Entity unmarshal(EntityAdapted entityAdapted) throws Exception {
		Entity entity = new Entity();
		for (Part part : entityAdapted.getParts()) {
			entity.attach(part);
		}
		return entity;
	}

}
