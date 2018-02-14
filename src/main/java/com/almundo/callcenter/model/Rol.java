package com.almundo.callcenter.model;

/** Entidad del rol del usuario
 * @author Jorge Aguirre
 *
 */
public class Rol {
	
	public static long OPERATOR = 1;
	public static long SUPERVISOR = 2;
	public static long DIRECTOR = 3;
	
	private Long id;
	
	private String description;

	/** Contructor de la entidad Rol con el identificado del rol y la descripción del mismo
	 * @param id
	 * @param description
	 */
	public Rol(Long id, String description) {
		this.id = id;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}

}
