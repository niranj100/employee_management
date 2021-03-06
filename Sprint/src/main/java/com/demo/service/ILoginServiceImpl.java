package com.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

import com.demo.dao.ILoginRepository;
import com.demo.entities.Role;
import com.demo.entities.Users;

@Service  //Indicates that an annotated class is a "Service"
public class ILoginServiceImpl implements UserDetailsService {
	 
	@Autowired // Marks a constructor, field, setter method, or config method as to be autowired bySpring's dependency injection facilities
	private ILoginRepository repository;
	
	@Override
	@Transactional // provides the application the ability to declaratively control transaction boundaries 
	// UserDetails - provides core user information
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		System.out.println("In Authentication" + userName);
		Users user = repository.findByUsername(userName);
		//System.out.println("In Authentication" + user.getUserName());
		if (user != null) {
			System.out.println("In Authentication-- if " + user.getUsername());
            return new User(user.getUsername(), user.getPassword(), createSimpleGrantedAuthorities(user.getRoles()));
        } else {
        	System.out.println("In Authentication+ ELSE PART" );
            throw new UsernameNotFoundException("User with "
                    + "user name "+ userName + " not found");
        }
	}
	
	private static List<SimpleGrantedAuthority> createSimpleGrantedAuthorities(Set<Role> roles) {
		 List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		 authorities = roles.stream()
              .map(role -> new SimpleGrantedAuthority(role.getRoleName().name()))
              .collect(Collectors.toList());
		 return authorities;
	}
}
