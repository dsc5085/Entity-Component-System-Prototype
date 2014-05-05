package entitypart.epf;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class EntityAdapter extends XmlAdapter<Entity, EntityAdapted> {

	@Override
	public Entity marshal(EntityAdapted entityAdapted) throws Exception {
		Entity entity = new Entity();
		for (Part part : entityAdapted.getParts()) {
			entity.attach(part);
		}
		return entity;
	}

	@Override
	public EntityAdapted unmarshal(Entity entity) throws Exception {
		EntityAdapted entityAdapted = new EntityAdapted(entity.getAll());
		return entityAdapted;
	}

}
