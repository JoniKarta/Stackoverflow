package acs.dal;

import org.springframework.data.repository.CrudRepository;

import acs.data.ActionEntity;

public interface ActionDao extends CrudRepository<ActionEntity, String>{

}
