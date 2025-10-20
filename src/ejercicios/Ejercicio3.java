package ejercicios;

import java.util.Map;

import us.lsi.common.Map2;
import us.lsi.common.Trio;

public class Ejercicio3 {

//	public static Long iterativo(Integer a, Integer b, Integer c) {
//		return 0L;
//	}
//
//	public static Long recursivo_sin_memoria(Integer a, Integer b, Integer c) {
//		return 0L;
//	}
//
//	public static Long recursivo_con_memoria(Integer a, Integer b, Integer c) {
//		return 0L;
//	}

	
	
	// alt + mayus + r (cambiar cosas)
	// ctrl + mayus + f -> formatear bonito código
	// ctrl + d -> eliminar una línea
	// ctrl + alt + flecha arriba/abajo -> duplicar una línea
	// alt + flecha arriba/abajo -> mover una línea
	// ctrl + mayusc + o -> importación automática

	public static Long iterativo(Integer a, Integer b, Integer c) { // siempre con memoria
		Map<Trio<Integer, Integer, Integer>, Long> mem = Map2.empty();
		Long res = null;
		// iteramos cada uno de los imputs (a,b,c)
		for (int i = 0; i <=a ; i++) {
			for (int j = 0; j <=b ; j++) {
				for (int k = 0; k <=c ; k++) {
					if (i < 3 || j < 3 || k < 3) {
						res = i + j * j + 2L * k;
					} else if (i >= j && i % j == 0) { 
						res = mem.get(Trio.of(i-1, j/2, k/2)) + mem.get(Trio.of(i-3, j/3, k/3));
					} else {
						res = mem.get(Trio.of(i/3, j-3, k-3)) + mem.get(Trio.of(i/2, j-2, k-2));
					}
					mem.put(Trio.of(i,j,k), res);
				}
			}
		}
		return mem.get(Trio.of(a,b,c));
	}

	public static Long recursivo_sin_memoria(Integer a, Integer b, Integer c) {
		Long res = null;
		if (a < 3 || b < 3 || c < 3) {
			res = a + b * b + 2L * c;
		} else if (a >= b && a % b == 0) {
			res = recursivo_sin_memoria(a - 1, b / 2, c / 2) + recursivo_sin_memoria(a - 3, b / 3, c / 3);
		} else {
			res = recursivo_sin_memoria(a / 3, b - 3, c - 3) + recursivo_sin_memoria(a / 2, b - 2, c - 2);
		}
		return res;
	}

	
	public static Long recursivo_con_memoria(Integer a, Integer b, Integer c) {
		return recursivo_con_memoria(a, b, c, Map2.empty());
	}

	private static Long recursivo_con_memoria(Integer a, Integer b, Integer c, Map<Trio<Integer, Integer, Integer>, Long> mem) {
		Long res = mem.get(Trio.of(a, b, c));
		if (res == null) {
			if (a < 3 || b < 3 || c < 3) {
				res = a + b * b + 2L * c;
			} else if (a >= b && a % b == 0) {
				res = recursivo_con_memoria(a - 1, b / 2, c / 2, mem) + recursivo_con_memoria(a - 3, b / 3, c / 3, mem);
			} else {
				res = recursivo_con_memoria(a / 3, b - 3, c - 3, mem) + recursivo_con_memoria(a / 2, b - 2, c - 2, mem);
			}
		}
		mem.put(Trio.of(a,b,c), res);
		return res;
	}

}
