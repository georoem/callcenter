package com.almundo.callcenter.service;

import java.util.LinkedList;
import java.util.Queue;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import com.almundo.callcenter.CallcenterApplication;
import com.almundo.callcenter.model.Call;

/** Classe que administra las llamadas
 * @author Jorge Aguirre
 *
 */
@Service
public class CallCenter {
	
	private Queue<Call> calls;
	
	public CallCenter(Queue<Call> calls) {
		super();
		this.calls = calls;
	}

	public Queue<Call> getCalls() {
		return this.calls;
	}
	
	public void addCall(Call call) {
		if(this.calls==null) {
			this.calls = new LinkedList<>();
		}
		this.calls.offer(call);
	}
	
	public void start(){
		ApplicationContext context = new AnnotationConfigApplicationContext(CallcenterApplication.class);
		ThreadPoolTaskExecutor taskExecutor = (ThreadPoolTaskExecutor) context.getBean("taskExecutor");
		
		while (getCalls().size() > 0){
			Call call = getCalls().poll();
			Dispatcher dispatcher = (Dispatcher) context.getBean("dispatcher");
			dispatcher.setCall(call);
			taskExecutor.execute(dispatcher);
		}
		
		if (this.calls.size() == 0) {
			taskExecutor.shutdown();
		}
		
	}

}
