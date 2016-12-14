package com.stlstreetapp.models.dao;
import java.util.List;

import javax.transaction.Transactional;

import com.stlstreetapp.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface UserDao extends CrudRepository<User, Integer> {
	 User findByUid(int uid);
	 List<User> findAll();
	 User findByEmail(String email);
	 User findByNumber(String number);
	 
	 List<User> findByCarrier(String Carrier);
}
