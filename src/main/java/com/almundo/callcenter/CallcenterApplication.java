package com.almundo.callcenter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.almundo.callcenter.model.Call;
import com.almundo.callcenter.model.Rol;
import com.almundo.callcenter.model.User;
import com.almundo.callcenter.service.CallCenter;
import com.almundo.callcenter.service.UserService;
import com.almundo.callcenter.util.Util;

/** Clase principal de la aplicación
 * @author Jorge Aguirre
 *
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.almundo.callcenter")
public class CallcenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(CallcenterApplication.class, args);
		callCenter().start();
	}
	
	/** Configuración del pool de hilos disponibles
	 * @return ThreadPoolTaskExecutor
	 */
	@Bean
	public ThreadPoolTaskExecutor taskExecutor() {
		ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
		pool.setCorePoolSize(10);
		pool.setMaxPoolSize(10);
		pool.setWaitForTasksToCompleteOnShutdown(true);
		return pool;
	}
	
	/** Instancia del servicio de usuarios con los usuario iniciales
	 * @return UserService
	 */
	@Bean
	public UserService userService(){
		List<User> users = new ArrayList<>();
		// 5 Usuarios operadores
		for(int i=0; i<5;i++) {
			users.add(new User(new Long(i), "User", true, new Rol(Rol.OPERATOR, "Operador")));
		}
		// 2 Usuarios supervisores
		for(int i=5; i<7;i++) {
			users.add(new User(new Long(i), "User", true, new Rol(Rol.SUPERVISOR, "Supervisor")));
		}
		// 1 Usuario director
		for(int i=7; i<8;i++) {
			users.add(new User(new Long(i), "User", true, new Rol(Rol.DIRECTOR, "Director")));
		}
		return new UserService(users); 
	}
	
	@Bean 
	public static CallCenter callCenter() {
		Queue<Call> calls = new LinkedList<>();
		for(int i=0; i < 20; i++){
			calls.add(new Call(new Long(i), Util.randomDuration()));
		}
		return new CallCenter(calls);
	}
}
