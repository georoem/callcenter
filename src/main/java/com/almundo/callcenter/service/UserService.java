package com.almundo.callcenter.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.almundo.callcenter.model.Rol;
import com.almundo.callcenter.model.User;

/** Servicio que administra los usuarios
 * @author Jorge Aguirre
 *
 */
@Service
public class UserService {
	
	private List<User> users;
	
	/** Constructor del servicio con los usuarios iniciales
	 * @param users
	 */
	public UserService(List<User> users) {
		this.users = users;
	}

	/** Función para obtener un usuario disponible
	 * @return User
	 */
	public synchronized User getAvailableUser() {
		 List<User> resultOperators = this.users.stream()
                .filter(user -> user.getRol().getId().equals(Rol.OPERATOR) && user.isAvailable())
                .collect(Collectors.toList());   
		 if(resultOperators.size()>0) {
			 User user = resultOperators.get(0);
			 user.setAvailable(User.BUSY);
			 return user;
		 }
		 
		 List<User> resultSupervisor = this.users.stream()
                .filter(user -> user.getRol().getId().equals(Rol.SUPERVISOR) && user.isAvailable())
                .collect(Collectors.toList()); 
		 
		 if(resultSupervisor.size()>0) {
			 User user = resultSupervisor.get(0);
			 user.setAvailable(User.BUSY);
			 return user;
		 }
		 
		 List<User> resultDirector = this.users.stream()
	                .filter(user -> user.getRol().getId().equals(Rol.DIRECTOR) && user.isAvailable())
	                .collect(Collectors.toList()); 
			 
		 if(resultDirector.size()>0) {
			 User user = resultDirector.get(0);
			 user.setAvailable(User.BUSY);
			 return user;
		 }
		 return null;
	}

	public void setAvailableUser(User user) {
		User userBusy = users.get(users.indexOf(user));
		userBusy.setAvailable(User.AVAILABLE);
		System.out.println("User " + userBusy.getId() + " is set available");
	}
	
}
