package com.almundo.callcenter.service;

import java.util.LinkedList;
import java.util.Queue;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.almundo.callcenter.CallcenterApplication;
import com.almundo.callcenter.model.Call;
import com.almundo.callcenter.model.User;

/** Classe que administra las llamadas
 * @author Jorge Aguirre
 *
 */
@Service
public class CallCenter {
	
	private Queue<Call> calls;
	
	/** Constructor del servicio de llamadas
	 * @param calls
	 */
	public CallCenter(Queue<Call> calls) {
		super();
		this.calls = calls;
	}

	private Queue<Call> getCalls() {
		return this.calls;
	}
	
	/** Adiciona una llamada a la cola
	 * @param call
	 */
	public void addCall(Call call) {
		if(this.calls==null) {
			this.calls = new LinkedList<>();
		}
		this.calls.offer(call);
	}
	
	/** Elimina la relación de la llamada con el usuario
	 * @param call
	 */
	public void endCall(Call call) {
		call.setUser(null);
	}
	
	/**
	 * Método de inicio de atención de la cola de llamadas
	 */
	public void start(){
		ApplicationContext context = new AnnotationConfigApplicationContext(CallcenterApplication.class);
		ThreadPoolTaskExecutor taskExecutor = (ThreadPoolTaskExecutor) context.getBean("taskExecutor");
		
		while (getCalls().size() > 0){
			Call call = getCalls().poll();
			if(taskExecutor.getActiveCount()<taskExecutor.getMaxPoolSize()) {
				Dispatcher dispatcher = (Dispatcher) context.getBean("dispatcher");
				dispatcher.setCall(call);
				taskExecutor.execute(dispatcher);
			} else {
				getCalls().offer(call);
			}
		}
		
		if (this.calls.size() == 0) {
			taskExecutor.shutdown();
		}
		
	}

}
