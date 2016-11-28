package com.stlstreetapp.models.dao;

import java.util.List;

import javax.transaction.Transactional;

import com.stlstreetapp.models.User;
import com.stlstreetapp.models.Zone;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public interface ZoneDao extends CrudRepository<Zone, Integer> {
	List<Zone> findAll();
	Zone findByUid(int uid);
	List<Zone> findByZoneNumber(String x);
	List<Zone> findByEmail(User user);
		
}

