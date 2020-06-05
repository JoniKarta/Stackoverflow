package acs.dal;

import org.springframework.data.repository.PagingAndSortingRepository;

import acs.data.ActionEntity;


public interface ActionDao extends PagingAndSortingRepository<ActionEntity, Long> {

}
