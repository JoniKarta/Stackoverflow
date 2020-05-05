package acs.logic;

import java.util.List;

import acs.boundaries.ElementBoundary;

public interface ElementService {

	public ElementBoundary createNewElement(String managerEmail, ElementBoundary input);

	public ElementBoundary updateElement(String managerEmail, String elementId, ElementBoundary update);

	public ElementBoundary getSpecificElement(String userEmail, String elementId);

	public List<ElementBoundary> getAllElements(String userEmail);

	public void deleteAllElements(String adminEmail);
}
