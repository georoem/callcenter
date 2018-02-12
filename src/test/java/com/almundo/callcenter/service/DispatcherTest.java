package com.almundo.callcenter.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.junit4.SpringRunner;

import com.almundo.callcenter.CallcenterApplication;
import com.almundo.callcenter.common.MockTestData;
import com.almundo.callcenter.model.Call;
import com.almundo.callcenter.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DispatcherTest {
	
	@Autowired
	UserService userService;

	@Autowired
	Dispatcher dispatcher; 
	
	@Test
	public void oneCallsTest() {
		Call call = MockTestData.getCallTest();
		ApplicationContext context = new AnnotationConfigApplicationContext(CallcenterApplication.class);
	    ThreadPoolTaskExecutor taskExecutor = (ThreadPoolTaskExecutor) context.getBean("taskExecutor");

	    Dispatcher dispatcher = (Dispatcher) context.getBean("dispatcher");
	    dispatcher.setCall(call);
	    taskExecutor.execute(dispatcher);
		
	}
	
	@Test
	public void tenCallsAtTheSameTimeTest() {
		List<Call> calls = MockTestData.getListCallTest();
		ApplicationContext context = new AnnotationConfigApplicationContext(CallcenterApplication.class);
	    ThreadPoolTaskExecutor taskExecutor = (ThreadPoolTaskExecutor) context.getBean("taskExecutor");

    	Dispatcher dispatcher0 = (Dispatcher) context.getBean("dispatcher");
    	dispatcher.setCall(calls.get(0));
    	taskExecutor.execute(dispatcher);
    	
    	Dispatcher dispatcher1 = (Dispatcher) context.getBean("dispatcher");
    	dispatcher.setCall(calls.get(1));
    	taskExecutor.execute(dispatcher);
    	
    	Dispatcher dispatcher2 = (Dispatcher) context.getBean("dispatcher");
    	dispatcher.setCall(calls.get(2));
    	taskExecutor.execute(dispatcher);
    	
    	Dispatcher dispatcher3 = (Dispatcher) context.getBean("dispatcher");
    	dispatcher.setCall(calls.get(3));
    	taskExecutor.execute(dispatcher);
    	
    	Dispatcher dispatcher4 = (Dispatcher) context.getBean("dispatcher");
    	dispatcher.setCall(calls.get(4));
    	taskExecutor.execute(dispatcher);
    	
    	Dispatcher dispatcher5 = (Dispatcher) context.getBean("dispatcher");
    	dispatcher.setCall(calls.get(5));
    	taskExecutor.execute(dispatcher);
    	
    	Dispatcher dispatcher6 = (Dispatcher) context.getBean("dispatcher");
    	dispatcher.setCall(calls.get(6));
    	taskExecutor.execute(dispatcher);
    	
    	Dispatcher dispatcher7 = (Dispatcher) context.getBean("dispatcher");
    	dispatcher.setCall(calls.get(7));
    	taskExecutor.execute(dispatcher);
    	
    	Dispatcher dispatcher8 = (Dispatcher) context.getBean("dispatcher");
    	dispatcher.setCall(calls.get(8));
    	taskExecutor.execute(dispatcher);
    	
    	Dispatcher dispatcher9 = (Dispatcher) context.getBean("dispatcher");
    	dispatcher.setCall(calls.get(9));
    	taskExecutor.execute(dispatcher);
    	
	    
	    for (;;) {
			int count = taskExecutor.getActiveCount();
			System.out.println("Active Threads : " + count);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (count == 0) {
				taskExecutor.shutdown();
				break;
			}
		}
	}
}
