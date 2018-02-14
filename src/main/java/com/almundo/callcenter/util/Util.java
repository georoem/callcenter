package com.almundo.callcenter.util;

import java.util.concurrent.ThreadLocalRandom;

public class Util {
	
	public static Integer randomDuration(){
		int max = 10;
		int min= 5;
		return ThreadLocalRandom.current().nextInt(min, max + 1);
	}

}
