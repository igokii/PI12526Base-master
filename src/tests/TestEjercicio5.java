package tests;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.apache.commons.math3.fitting.WeightedObservedPoint;

import ejercicios.Ejercicio5;
import us.lsi.common.Pair;
import us.lsi.common.String2;
import us.lsi.curvefitting.DataFile;
import us.lsi.curvefitting.Fit;
import us.lsi.curvefitting.GenData;
import us.lsi.curvefitting.PowerLog;
import us.lsi.graphics.MatPlotLib;

public class TestEjercicio5 {
	

	private static Integer nMinExp = 5;
	private static Integer nMaxExp = 3000; 
	private static Integer razonExp = 3; 
	private static Integer nIterExp = 40; 
	private static Integer nIterWarmupExp = 800;
	
	
	private static Integer nMin = 32;
	private static Integer nMax = 10000; 
	private static Integer razon = 2; 
	private static Integer nIter = 40; 
	private static Integer nIterWarmup = 1000;
	
	

	public static void genDataAritmetico(Consumer<Integer> algorithm,String file,Integer tMin,Integer tMax,Integer razon,Integer numIter,Integer numIterWarmup) {
		Function<Integer,Long> f1 = GenData.time(algorithm);
		GenData.tiemposEjecucionAritmetica(f1,file,tMin,tMax,razon,numIter,numIterWarmup);
	}
	
	public static void genDataGeometrico(Consumer<Integer> algorithm,String file,Integer tMin,Integer tMax,Integer razon,Integer numIter,Integer numIterWarmup) {
		Function<Integer,Long> f1 = GenData.time(algorithm);
		GenData.tiemposEjecucionGeometrica(f1,file,tMin,tMax,razon,numIter,numIterWarmup);
	}
	
	public static void show(Fit pl, String file, String label) {
		List<WeightedObservedPoint> data = DataFile.points(file);
		pl.fit(data);
		MatPlotLib.show(file, pl.getFunction(), String.format("%s = %s",label,pl.getExpression()));
	}

	
	public static void showCombined() {
		MatPlotLib.showCombined("Tiempos",
				List.of("ficheros_generados/ejercicio5ItDouble.txt","ficheros_generados/ejercicio5RecDouble.txt","ficheros_generados/ejercicio5ItBigInteger.txt",
						"ficheros_generados/ejercicio5RecBigInteger.txt"), 
				List.of("Iterativo_Double","Recursivo_Double","Iterativo_BI","Recursivo_BI"));
	}
	
	

	public static void main(String[] args) {
		

		// BIG INTEGER: Iterativo, Recursivo
		genDataAritmetico(Ejercicio5::ejercicio5ItBigInteger,"ficheros_generados/ejercicio5ItBigInteger.txt",nMinExp,nMaxExp,razonExp,nIterExp,nIterWarmupExp);
		genDataGeometrico(Ejercicio5::ejercicio5RecBigInteger,"ficheros_generados/ejercicio5RecBigInteger.txt",nMin, nMax, razon, nIter, nIterWarmup);

		//DOUBLE: Iterativo, Recursivo
		genDataAritmetico(Ejercicio5::ejercicio5ItDouble,"ficheros_generados/ejercicio5ItDouble.txt", nMinExp, nMaxExp, razonExp, nIterExp, nIterWarmupExp);
		genDataGeometrico(Ejercicio5::ejercicio5RecDouble,"ficheros_generados/ejercicio5RecDouble.txt",nMin, nMax, razon, nIter, nIterWarmup);

		// BIG INTEGER: REC SIN MEMORIA, REC CON MEMORIA, ITERATIVO, CON POTENCIA DE MATRICES
		show(PowerLog.of(List.of(Pair.of(2, 0.),Pair.of(3, 0.))), "ficheros_generados/ejercicio5RecBigInteger.txt","rec_BI");
		show(PowerLog.of(List.of(Pair.of(2, 0.),Pair.of(3, 0.))), "ficheros_generados/ejercicio5ItBigInteger.txt","iter_BI");
				
		//DOUBLE: REC SIN MEMORIA, REC CON MEMORIA, ITERATIVO, CON POTENCIA DE MATRICES
		show(PowerLog.of(List.of(Pair.of(2, 0.),Pair.of(3, 0.))), "ficheros_generados/ejercicio5RecDouble.txt","rec_D");
		show(PowerLog.of(List.of(Pair.of(2, 1.),Pair.of(3, 0.))), "ficheros_generados/ejercicio5ItDouble.txt","iter_D");


		showCombined();
	}

}