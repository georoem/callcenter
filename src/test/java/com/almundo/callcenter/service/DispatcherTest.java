package com.almundo.callcenter.service;

import java.util.Queue;

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

/** Clase de prueba del Dispatcher
 * @author Jorge Aguirre
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DispatcherTest {
	
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
}
