package com.almundo.callcenter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.almundo.callcenter.model.Rol;
import com.almundo.callcenter.model.User;
import com.almundo.callcenter.service.UserService;

@SpringBootApplication
@ComponentScan(basePackages = "com.almundo.callcenter")
public class CallcenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(CallcenterApplication.class, args);
	}
	
	@Bean
	public ThreadPoolTaskExecutor taskExecutor() {
		ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
		pool.setCorePoolSize(5);
		pool.setMaxPoolSize(10);
		pool.setWaitForTasksToCompleteOnShutdown(true);
		return pool;
	}
	
	@Bean
	public UserService userService(){
		List<User> users = new ArrayList<>();
		// 5 User operators
		for(int i=0; i<5;i++) {
			users.add(new User(new Long(i), "User", true, new Rol(Rol.OPERATOR, "Operador")));
		}
		// 2 User supervisors
		for(int i=5; i<7;i++) {
			users.add(new User(new Long(i), "User", true, new Rol(Rol.SUPERVISOR, "Supervisor")));
		}
		// 1 User director
		for(int i=7; i<8;i++) {
			users.add(new User(new Long(i), "User", true, new Rol(Rol.DIRECTOR, "Director")));
		}
		return new UserService(users); 
	}
}
