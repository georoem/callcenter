package com.almundo.callcenter.model;

/** Call model
 * @author Jorge Aguirre
 *
 */
public class Call {
	
	private Long id;
	
	private Integer duration;
	
	private User user;
	
	public Call(Long id, Integer duration) {
		super();
		this.id = id;
		this.duration = duration;
	}

	public Long getId() {
		return id;
	}

	public Integer getDuration() {
		return duration;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
