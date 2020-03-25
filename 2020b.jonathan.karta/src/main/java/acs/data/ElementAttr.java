package acs.data;

import java.util.HashMap;
import java.util.Map;

public class ElementAttr {

	private Map<String, Object> elementAttr;

	public ElementAttr() {
		elementAttr = new HashMap<>();
	}

	public Map<String, Object> getElementAttr() {
		return elementAttr;
	}

	public void setElementAttr(Map<String, Object> elementAttr) {
		this.elementAttr = elementAttr;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		elementAttr.forEach((u, v) -> sb.append(u + ":" + v + "\n"));
		return sb.toString();
	}
}
