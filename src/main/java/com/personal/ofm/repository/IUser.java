package com.personal.ofm.repository;

import org.springframework.data.repository.CrudRepository;

import com.personal.ofm.entity.Users;
public interface IUser extends CrudRepository<Users, String>{
	Users findByUser(String userName);
}
