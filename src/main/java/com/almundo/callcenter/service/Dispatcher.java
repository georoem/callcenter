package com.almundo.callcenter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.almundo.callcenter.model.Call;
import com.almundo.callcenter.model.User;

/** Clase que administra las llamadas de manera asíncrona
 * @author Jorge Aguirre
 *
 */
@Component
@Scope("prototype")
public class Dispatcher implements Runnable{

	@Autowired
	private UserService userService;
	
	@Autowired
	private CallCenter callCenter;
	
	private Call call;
	
	/** Setea la llamada que va a atender
	 * @param call
	 */
	public void setCall(Call call) {
		this.call = call;
	}
	
	/** Método que asigna la llamada a un usuario disponible
	 * @param call
	 * @return
	 */
	private User dispatchCall(Call call) {
		User user =  userService.getAvailableUser();
		if(user == null) {
			return null;
		}
		setUserToCall(user, call);
		return user;
	}

	/** Método que asigna la llamada a un usuario
	 * @param user
	 * @param call
	 */
	private void setUserToCall(User user, Call call) {
		call.setUser(user);
	}

	/* (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() { 
		Call actualCall = new Call(call.getId(), call.getDuration());
		User user = dispatchCall(actualCall);
		
		if(user == null){
			System.out.println("No users avaivable for call "+ actualCall.getId() + ", the call is queue for a next available user");
			callCenter.addCall(actualCall);
			return;
		}
		
		System.out.println("Call " + actualCall.getId() + 
				" with duration " + actualCall.getDuration() + 
				" is attending by user "+ user.getId() +
				" with the rol " + user.getRol().getDescription());
		
		try {
			Thread.sleep(actualCall.getDuration()*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		userService.setAvailableUser(user);

		System.out.println("Call " + actualCall.getId() + " ends");
		
	}

}
