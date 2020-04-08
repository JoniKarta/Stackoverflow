package acs.logic;

import java.util.List;

import acs.boundaries.ElementBoundary;

public interface ElementService {

	public ElementBoundary create(String managerEmail, ElementBoundary input);

	public ElementBoundary update(String managerEmail, String elementId, ElementBoundary update);

	public ElementBoundary getSpecificElement(String userEmail, String elementId);

	public List<ElementBoundary> getAll(String userEmail);

	public void deleteAllElements(String adminEmail);
}
