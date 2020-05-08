package acs.logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import acs.boundaries.ActionBoundary;
import acs.dal.ActionDao;
import acs.data.ActionConverter;
import acs.data.ActionEntity;

@Service
public class ActionServiceWithDB implements ActionService {
	private ActionDao actionDao;
	private ActionConverter actionConverter;

	@Autowired
	public void setActionDao(ActionDao actionDao) {
		this.actionDao = actionDao;
	}

	@Autowired
	public void setActionConverter(ActionConverter actionConverter) {
		this.actionConverter = actionConverter;
	}

	@Override
	@Transactional
	public Object invokeAction(ActionBoundary action) {
		String newId = UUID.randomUUID().toString();
		ActionEntity entity = actionConverter.convertToEntity(action);
		entity.setActionId(newId);
		entity.setCreation(new Date());
		entity = actionDao.save(entity);
		return actionConverter.convertFromEntity(entity);
	}

	@Override
	@Transactional(readOnly = true)
	public List<ActionBoundary> getAllActions(String adminEmail) {
		List<ActionBoundary> rv = new ArrayList<>();

		Iterable<ActionEntity> entityList = actionDao.findAll();
		for (ActionEntity actionEntity : entityList) {
			rv.add((ActionBoundary) actionConverter.convertFromEntity(actionEntity));
		}

		return rv;

	}

	@Override
	@Transactional
	public void deleteAllActions(String adminEmail) {
		actionDao.deleteAll();
	}
}
