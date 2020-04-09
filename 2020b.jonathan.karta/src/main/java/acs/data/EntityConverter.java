package acs.data;

import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import acs.boundaries.ElementBoundary;

@Component
public class EntityConverter {
	private ObjectMapper jackson;

	@PostConstruct
	public void setup() {
		jackson = new ObjectMapper();
	}

	public ElementBoundary convertFromEntity(ElementEntity entity) {
		ElementBoundary elementBoundary = new ElementBoundary();
		elementBoundary.setElementId(this.fromEntityId((entity.getElementId())));
		elementBoundary.setType(entity.getType());
		elementBoundary.setName(entity.getName());
		elementBoundary.setActive(entity.getActive());
		elementBoundary.setCreatedTimestamp(entity.getCreatedTimestamp());
		// TODO marshaling ?
		elementBoundary.setCreatedBy(entity.getCreatedBy());

		// TODO marshaling ?
		elementBoundary.setLocation(entity.getLocation());

		// TODO unmarshaling
		elementBoundary.setElementAttribute(unMarshElementAttribute(entity.getElementAttribute()));

		return elementBoundary;
	}

	public ElementEntity convertToEntity(ElementBoundary input) {
		ElementEntity elementEntity = new ElementEntity();
		elementEntity.setElementId(this.toEntityId((input.getElementId())));
		elementEntity.setType(input.getType());
		elementEntity.setName(input.getName());
		elementEntity.setActive(input.getActive());
		elementEntity.setCreatedTimestamp(input.getCreatedTimestamp());
		// TODO marshaling ?
		elementEntity.setCreatedBy(input.getCreatedBy());

		// TODO marshaling ?
		elementEntity.setLocation(input.getLocation());

		// TODO marshaling
		elementEntity.setElementAttribute(marshElementAttribute(input.getElementAttribute()));

		return elementEntity;
	}

	public String marshElementAttribute(Map<String, Object> input) {
		String elementMarshaling = null;
		try {
			elementMarshaling = this.jackson.writeValueAsString(input);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return elementMarshaling;
	}

	public Map<String, Object> unMarshElementAttribute(String input) {
		Map<String, Object> elementAttribute;
		try {
			elementAttribute = this.jackson.readValue(input, Map.class);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return elementAttribute;
	}

	public Long toEntityId(String elementId) {
		if (elementId != null) {
			return Long.parseLong(elementId);
		} else {
			return null;
		}
	}

	public String fromEntityId(Long elementId) {
		if (elementId != null) {
			return elementId.toString();
		} else {
			return null;
		}
	}
}
