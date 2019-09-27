package com.personal.ofm.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.personal.ofm.entity.Users;
import com.personal.ofm.repository.IUser;

//TODO: Auto-generated Javadoc
/**
* The Class JpaUserDetailService.
*/
@Service("jpaUserDetailService")
public class JpaUserDetailService implements UserDetailsService{
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private IUser iuser;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User userResponse = null;
		Users user = null;
		user = iuser.findByUser(username);
		List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
			if (user != null) {
				roles.add(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
				userResponse = new User(username,passwordEncoder.encode(user.getPassword()), roles);
			}
		return userResponse;
	}

}
