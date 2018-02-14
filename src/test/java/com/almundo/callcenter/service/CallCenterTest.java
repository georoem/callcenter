package com.almundo.callcenter.service;

import java.util.Queue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.almundo.callcenter.common.MockTestData;
import com.almundo.callcenter.model.Call;

import static org.mockito.Mockito.*;

/** Clase de prueba del call center
 * @author Jorge Aguirre
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CallCenterTest {
	
	@Autowired
	private CallCenter callCenter;

	@Test
	public void tenCallsAtTheSameTimeTest() {
		Queue<Call> calls = MockTestData.getQueueCallTest();
		CallCenter c = mock(CallCenter.class);
	    when(c.getCalls()).thenReturn(calls);
		callCenter.start();
	}
}
