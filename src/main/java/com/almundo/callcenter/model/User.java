package com.almundo.callcenter.model;

/** User model
 * @author Jorge Aguirre
 *
 */
public class User {
	
	public static Boolean AVAILABLE=Boolean.TRUE;
	public static Boolean BUSY=Boolean.FALSE;
	
	private Long id;
	
	private String username;
	
	private Boolean available;
	
	private Rol rol;
	
	public User(Long id, String username, Boolean available, Rol rol) {
		super();
		this.id = id;
		this.username = username;
		this.available = available;
		this.rol = rol;
	}

	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public Boolean isAvailable() {
		return available;
	}

	public void setAvailable(Boolean available) {
		this.available = available;
	}

	public Rol getRol() {
		return rol;
	}

}
