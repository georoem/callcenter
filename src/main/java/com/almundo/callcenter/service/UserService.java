package com.almundo.callcenter.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.almundo.callcenter.model.Rol;
import com.almundo.callcenter.model.User;

@Service
public class UserService {
	
	private List<User> users;
	
	public UserService(List<User> users) {
		this.users = users;
	}

	/** Función para obtener un usuario disponible
	 * @return
	 */
	public User getAvailableUser() {
		 List<User> resultOperators = this.users.stream()
                .filter(user -> user.getRol().getId().equals(Rol.OPERATOR) && user.isAvailable())
                .collect(Collectors.toList());   
		 if(resultOperators.size()>0) {
			 return resultOperators.get(0);
		 }
		 
		 List<User> resultSupervisor = this.users.stream()
                .filter(user -> user.getRol().getId().equals(Rol.SUPERVISOR) && user.isAvailable())
                .collect(Collectors.toList()); 
		 
		 if(resultSupervisor.size()>0) {
			 return resultSupervisor.get(0);
		 }
		 
		 List<User> resultDirector = this.users.stream()
	                .filter(user -> user.getRol().getId().equals(Rol.DIRECTOR) && user.isAvailable())
	                .collect(Collectors.toList()); 
			 
		 if(resultDirector.size()>0) {
			 return resultDirector.get(0);
		 }
		 return null;
	}
	
}
