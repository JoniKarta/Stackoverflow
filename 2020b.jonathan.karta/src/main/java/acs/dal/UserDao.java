package acs.dal;


import org.springframework.data.repository.PagingAndSortingRepository;

import acs.data.UserEntity;

public interface UserDao extends PagingAndSortingRepository<UserEntity, String> {

}
