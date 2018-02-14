package com.almundo.callcenter.util;

import java.util.concurrent.ThreadLocalRandom;

/** Clase utilitaria
 * @author Jorge
 *
 */
public class Util {
	
	/** Método para obtener una duración entre 5 a 10 segundos
	 * @return
	 */
	public static Integer randomDuration(){
		int max = 10;
		int min= 5;
		return ThreadLocalRandom.current().nextInt(min, max + 1);
	}

}
