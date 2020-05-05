package acs.logic;


import org.springframework.data.repository.CrudRepository;

import acs.data.ElementEntity;

public interface ElementDao extends CrudRepository<ElementEntity, String>{

}
