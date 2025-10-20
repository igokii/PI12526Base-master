package ejercicios;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
public class Ejercicio2 {
// Recursivo No Final
	public static List<Integer> f_RNF(Integer a, Integer b) {
		if ((a < 2 || b < 2)) {
			List<Integer> res = new ArrayList<>();
			res.add(a * b);
			return res;
		}
		else if ((a > b)) {
			List<Integer> res = f_RNF(a % b, b - 1);
			res.add(a);
			return res;
		}
		else {
			List<Integer> res = f_RNF(a - 2, b / 2);
			res.add(b);
			return res;
		}
	}
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
		if (a < 2 || b < 2) {
			res.addFirst(a * b);
		}
		return res;
	}
// Recursivo Final
	public static List<Integer> f_RF(Integer a, Integer b) {
		List<Integer> acum = new ArrayList<>();
		return f_RFAux(a, b, acum);
	}
	private static List<Integer> f_RFAux(Integer a, Integer b, List<Integer> acum) {
		if (a < 2 || b < 2) {
			acum.add(a * b);
			return acum;
		}
		else if ((a > b)) {
			acum = f_RFAux(a % b, b - 1, acum);
			acum.add(a);
			return acum;
		}
		else {
			acum = (f_RFAux(a - 2, b / 2, acum));
			acum.add(b);
			return acum;
		}
	}
// funcional
	private static record Tupla(List<Integer> ac, Integer a, Integer b) { // nuestro ac va aser una lista
		public static Tupla of(List<Integer> ba, Integer a, Integer b) {
			return new Tupla(ba, a, b);
		}
		public static Tupla first(Integer a, Integer b) {
			return of(new ArrayList<>(), a, b);
		}
		public Tupla next() {
			return of(Stream.concat(ac.stream(), (new ArrayList<>(a)).stream()).toList(), a / 2, b - 2);
		}
	} // HACER FUNCIONAL
	public static List<Integer> f_funcional(Integer a, Integer b) {
		return Stream
				.iterate(Tupla.of(a, b), e -> (a < 2 || b < 2), e -> e.next())
				.get();
	}

}