package acs.validations;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import acs.boundaries.ActionBoundary;
import acs.boundaries.ElementBoundary;
import acs.boundaries.UserBoundary;
import acs.data.ElementEntity;
import acs.data.UserEntity;
import acs.data.UserRoleEntity;

@Component
public class Validator {

	public boolean validateUsername(UserBoundary user) {
		return user.getUsername() != null && !user.getUsername().isEmpty() ;
	}

	public boolean validateUserBoundary(UserBoundary user) {
		return this.validateUserEmail(user.getEmail());
	}

	public boolean validateUserEmail(String userEmail) {
		if (userEmail == null || userEmail.isEmpty()) {
			return false;
		}
		Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
				Pattern.CASE_INSENSITIVE);
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(userEmail);
		return matcher.matches();
	}

	public boolean validateActionElement(ActionBoundary action) {
		return action != null && action.getElement() != null &&
				action.getElement().getElementId() != null &&
				!action.getElement().getElementId().isEmpty();
	}

	public boolean validateActionInvoker(ActionBoundary action) {
		return action.getInvokedBy() != null && validateUserEmail(action.getInvokedBy().getEmail());
	}

	public boolean isManager(UserEntity user) {
		return user.getRole() == UserRoleEntity.MANAGER;

	}

	public boolean isPlayer(UserEntity user) {
		return user.getRole() == UserRoleEntity.PLAYER;
	}

	public boolean isAdmin(UserEntity user) {
		return user.getRole() == UserRoleEntity.ADMIN;
	}

	public boolean isRoleExist(UserBoundary user) {
		Pattern p = Pattern.compile("PLAYER|ADMIN|MANAGER");
		if (user.getRole() == null)
			return false;
		Matcher matcher = p.matcher(user.getRole().toString());
		if (user.getRole() == null || !matcher.matches()) {
			return false;
		} else {
			return true;
		}
	}

	public boolean validateAvatar(UserBoundary user) {
		return user.getAvatar() != null && !user.getAvatar().isEmpty();
	}

	public boolean validateElementName(ElementBoundary element) {
		return element.getName() != null;
	}

	public boolean validateElementType(ElementBoundary element) {
		return element.getType() != null;
	}

	public boolean validateActionType(ActionBoundary action) {
		return action.getType() != null;
	}
	
	public boolean validateElementActive(ElementBoundary element) {
		return element.getActive() != null;
	}
	
	public boolean validateElementLocation(ElementBoundary element) {
		return element.getLocation() != null;
	}
	
	public boolean validateElementAttr(ElementBoundary element) {
		return element.getElementAttribute() != null;
	}

	public boolean isActive(ElementEntity elementEntity) {
		return elementEntity != null && elementEntity.getActive() != null && elementEntity.getActive().booleanValue();
	}
}
