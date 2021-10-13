package com.demo.dao;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.demo.entities.*;

public interface IRolesRepository extends JpaRepository<Role, Integer>{
	Optional<Role> findByRoleName(Roles role); //Optional - A container object which may or may not contain a non-null value. 
}
