package com.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.demo.entities.Users;

@Repository //Indicates that an annotated class is a "Repository"
public interface ILoginRepository extends JpaRepository<Users,Integer> {
	Users findByUsername(String username);
	public boolean existsByUsername(String username);
}
