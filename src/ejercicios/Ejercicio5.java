package ejercicios;

import java.math.BigInteger;

public class Ejercicio5 {
	
	public static Double ejercicio5ItDouble(Integer n) {
		Double r = 1.;
		if (n > 6) {
			for (int i=7; i<=n; i++) {
				r = 1 + r*log2(i-1);
			}
		}
		return r;
	}
	
	public static Double ejercicio5RecDouble(Integer n) {
		Double r = null;
		if (n<=6) {
			r = 1.;
		}
		else {
			r = 1 + ejercicio5RecDouble(n-1)*log2(n-1);
		}
		return r;
	}
	
	public static BigInteger ejercicio5RecBigInteger(Integer n) {
		BigInteger r = null;
		if (n<=6) {
			r = BigInteger.ONE;
		}
		else {
			r = ejercicio5RecBigInteger(n-1).multiply(BigInteger.valueOf(log2(n-1))).add(BigInteger.ONE);
		}
		return r;
	}
	
	public static BigInteger ejercicio5ItBigInteger(Integer n) {
		BigInteger r = BigInteger.ONE;
		if (n > 6) {
			for (int i=7; i<=n; i++) {
				r = r.multiply(BigInteger.valueOf(log2(i-1))).add(BigInteger.ONE);
			}
		}
		return r;
	}
	

	public static int log2(int n){
	    if(n <= 0) throw new IllegalArgumentException();
	    return 31 - Integer.numberOfLeadingZeros(n);
	}
}