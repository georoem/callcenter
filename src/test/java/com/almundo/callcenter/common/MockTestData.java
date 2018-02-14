package com.almundo.callcenter.common;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import com.almundo.callcenter.model.Call;
import com.almundo.callcenter.model.Rol;
import com.almundo.callcenter.model.User;
import com.almundo.callcenter.util.Util;

/** Clase utilitaria que provee todos los datos de prueba
 * @author Jorge Aguirre
 *
 */
public class MockTestData {
	
	private static final int CALL_DURATION_1 = 5;

	private static final long CALL_ID_1 = 1L;

	private static final String OPERATOR_USER_DESCRIPTION = "User Operator ";
	
	private static final String SUPERVISOR_USER_DESCRIPTION = "User Supervisor ";
	
	private static final String DIRECTOR_USER_DESCRIPTION = "User Director ";

	private static final long USER_ID_1 = 1L;
	private static final long USER_ID_2 = 2L;
	private static final long USER_ID_3 = 3L;

	public static List<User> getAvailableUsers() {
		Rol rolOperator = getRolOperator();
		
		List<User> users = new ArrayList<>();
		
		users.add(new User(USER_ID_1, OPERATOR_USER_DESCRIPTION, true, rolOperator));
		
		return users;
	}
	
	public static User getOperatorUserWithId(long id, boolean available) {
		Rol rolOperator = getRolOperator();
		return new User(id, OPERATOR_USER_DESCRIPTION+id, available, rolOperator);
	}
	
	public static User getSupervisorUserWithId(long id, boolean available) {
		Rol rolSupervisor = getRolSupervisor();
		return new User(id, SUPERVISOR_USER_DESCRIPTION+id, available, rolSupervisor);
	}
	
	public static User getDirectorUserWithId(long id, boolean available) {
		Rol rolDirector = getRolDirector();
		return new User(id, DIRECTOR_USER_DESCRIPTION+id, available, rolDirector);
	}

	public static Rol getRolOperator() {
		return  new Rol(Rol.OPERATOR, "Operator");
	}
	
	public static Rol getRolSupervisor() {
		return  new Rol(Rol.SUPERVISOR, "Supervisor");
	}
	
	public static Rol getRolDirector() {
		return  new Rol(Rol.DIRECTOR, "Director");
	}

	public static Call getCallTest() {
		return new Call(CALL_ID_1, CALL_DURATION_1);
	}

	public static List<User> getOneOperatorAvailable() {
		List<User> users = new ArrayList<>();
		users.add(getOperatorUserWithId(USER_ID_1, true));
		return users;
	}

	public static List<User> getOneOperatorBusyAndASupervisdorAvailable() {
		List<User> users = new ArrayList<>();
		users.add(getOperatorUserWithId(USER_ID_1, false));
		users.add(getSupervisorUserWithId(USER_ID_2, true));
		return users;
	}
	
	public static List<User> getOneOperatorAnSupervisorBusyAndADirectorAvailable() {
		List<User> users = new ArrayList<>();
		users.add(getOperatorUserWithId(USER_ID_1, false));
		users.add(getSupervisorUserWithId(USER_ID_2, false));
		users.add(getDirectorUserWithId(USER_ID_3, true));
		return users;
	}

	public static Queue<Call> getQueueCallTest() {
		Queue<Call> calls = new LinkedList<>();
		for(int i=0; i < 20; i++){
			calls.add(new Call(new Long(i), Util.randomDuration()));
		}
		return calls;
	}
	
}
