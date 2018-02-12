package com.almundo.callcenter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.almundo.callcenter.model.Call;
import com.almundo.callcenter.model.User;

@Service
public class Dispatcher implements Runnable{

	@Autowired
	UserService userService;
	
	private Call call;
	

	public void setCall(Call call) {
		this.call = call;
	}

	private User dispatchCall(Call call) {
		User user =  userService.getAvailableUser();
		if(user == null) {
			return null;
		}
		setUserToCall(user, call);
		return user;
	}

	private void setUserToCall(User user, Call call) {
		user.setAvailable(User.BUSY);
		call.setUser(user);
	}

	@Override
	public void run() {
		User user = dispatchCall(call);
		
		if(user == null){
			System.out.println("No users avaivable for call "+ call.getId());
			return;
		}
		
		System.out.println("Call " + call.getId() + 
				" with duration " + call.getDuration() + 
				" is attending by "+ user.getUsername() +
				" with the rol " + user.getRol().getDescription());
		
		try {
			Thread.sleep(call.getDuration()*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Call " + call.getId() + " ends");
		
	}

}
