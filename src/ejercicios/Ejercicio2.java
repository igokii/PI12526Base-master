package ejercicios;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
public class Ejercicio2 {
	
	// Iterativo
	public static List<Integer> f_it(Integer a, Integer b) {
		List<Integer> res = new ArrayList<>();
		while (!(a < 2 || b < 2)) {
			if (a > b) {
				res.addFirst(a);
				a = a % b;
				b = b - 1;
			}
			else {
				res.addFirst(b);
				a = a - 2;
				b = b / 2;
			}
		}
			res.addFirst(a * b);
		return res;
	}

// recursivo no final:
	public static List<Integer> f_RNF(Integer a, Integer b) {
		List<Integer> res = null;
		if (a < 2 || b < 2) {
			res = new ArrayList<>();
			res.add(a * b);
		} else if (a > b) {
			res = f_RNF(a % b, b - 1);
			res.add(a);
		} else {
			res = f_RNF(a - 2, b / 2);
			res.add(b);
		}
		return res;
	}
//	// Recursivo Final primer intento. no me gusta porque devuelve acum (que contiene la función recursiva) en vez de actualizar acum y devolver la funcion
//	public static List<Integer> f_RF1(Integer a, Integer b) {
//		List<Integer> acum = new ArrayList<>();
//		return f_RFAux(a, b, acum);
//	}
//	private static List<Integer> f_RFAux(Integer a, Integer b, List<Integer> acum) {
//		if (a < 2 || b < 2) {
//			acum.add(a * b);
//			return acum;
//		}
//		else if ((a > b)) {
//			acum = f_RFAux(a % b, b - 1, acum);
//			acum.add(a);
//			return acum;
//		}
//		else {
//			acum = (f_RFAux(a - 2, b / 2, acum));
//			acum.add(b);
//			return acum;
//		}
//	}
 // intento 2 de recursivo final, me gusta más porque se parece más a los ejemplos
	public static List<Integer> f_RF(Integer a, Integer b) {
		return RecFinal(new ArrayList<>(), a, b);
	}
	private static List<Integer> RecFinal (List<Integer> ac, Integer a, Integer b) {
		if (a<2 || b<2) {
			ac.addFirst(a*b);
			return ac;
		}
		else if (a>b) {
			ac.addFirst(a);
			return RecFinal(ac, a%b, b-1);
		}
		else {
			ac.addFirst(b);
			return RecFinal(ac, a-2, b/2);
		}
	}

	// funcional:
	public static record Tupla(List<Integer> ac, Integer a, Integer b) {
		private static Tupla of(List<Integer> ac, Integer a, Integer b) {
			return new Tupla(ac, a, b);
		}
		private static Tupla first(Integer a, Integer b) {
			return of(new ArrayList<>(), a, b);
		}
		private Tupla nx() {
			if (a > b) {
				ac.addFirst(a);
				return of(ac, a % b, b - 1);
			} else {
				ac.addFirst(b);
				return of(ac, a - 2, b / 2);
			}
		}
	}

	public static List<Integer> f_funcional(Integer a, Integer b) {
		Tupla tupla = Stream.iterate(Tupla.first(a, b), t -> t.nx())
		        .filter(t -> (t.a() < 2 || t.b() < 2))
			    .findFirst()
			    .get();
		List<Integer> res = tupla.ac();
		res.addFirst(tupla.a()*tupla.b());
		return res;
	}	
}