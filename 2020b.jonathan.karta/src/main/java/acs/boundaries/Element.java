package acs.boundaries;

public class Element {
	private ElementId elementId;

	public Element() {
	}

	public Element(ElementId elementId) {
		super();
		this.elementId = elementId;
	}

	public ElementId getElementId() {
		return elementId;
	}

	public void setElementId(ElementId elementId) {
		this.elementId = elementId;
	}

	@Override
	public String toString() {
		return "Element [elementId=" + elementId + "]";
	}

}
