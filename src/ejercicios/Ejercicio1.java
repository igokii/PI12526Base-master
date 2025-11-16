package ejercicios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Ejercicio1 {

    // Del enunciado:
    public static Map<Integer, List<String>> solucionFuncional(Integer varA, String varB, Integer varC, String varD, Integer varE) {
        UnaryOperator<EnteroCadena> nx = elem -> {
            return EnteroCadena.of(
                elem.a() + 2,
                elem.a() % 3 == 0 ?
                    elem.s() + elem.a().toString() :
                    elem.s().substring(elem.a() % elem.s().length())
            );
        };

        return Stream.iterate(EnteroCadena.of(varA, varB), elem -> elem.a() < varC, nx)
            .map(elem -> elem.s() + varD)
            .filter(nom -> nom.length() < varE)
            .collect(Collectors.groupingBy(String::length));
    }
    
	public static Map<Integer, List<String>> solucionIterativa(Integer varA, String varB, Integer varC, String varD, Integer varE) {
		Map<Integer, List<String>> res = new HashMap<>();
		EnteroCadena elem = EnteroCadena.of(varA, varB);
		while (elem.a() < varC) {
			String nom = elem.s() + varD;
			if (nom.length() < varE) {
				if (!res.containsKey(nom.length())) {
					res.put(nom.length(), new ArrayList<String>());
				}
				res.get(nom.length()).add(nom);
			}
			if (elem.a() % 3 == 0) {
				elem = EnteroCadena.of(elem.a() + 2, elem.s() + elem.a().toString());
			} else {
				elem = EnteroCadena.of(elem.a() + 2, elem.s().substring(elem.a() % elem.s().length()));
			}
		}
		return res;
	}    

	public static Map<Integer, List<String>> solucionRecursivaFinal(Integer varA, String varB, Integer varC, String varD, Integer varE) {
		return RecFinal(new HashMap<>(), varA, varB, varC, varD, varE);
	}

	private static Map<Integer, List<String>> RecFinal(HashMap<Integer, List<String>> ac, Integer varA, String varB, Integer varC, String varD, Integer varE) {
		if (varA < varC) {
			String nom = (varB + varD);
			if (nom.length() < varE) {
				if (!ac.containsKey(nom.length())) {
					ac.put(nom.length(), new ArrayList<>());
				}
				ac.get(nom.length()).add(nom);
			}
			if (varA % 3 == 0) {
				return RecFinal(ac, varA + 2, varB + String.valueOf(varA), varC, varD, varE);
			} else {
				return RecFinal(ac, varA + 2, varB.substring(varA % varB.length()), varC, varD, varE);
			}
		}
		return ac;
	}

//// #### primer intento de solución recursiva final:
//    public static Map<Integer, List<String>> solucionRecursivaFinal(Integer varA, String varB, Integer varC, String varD, Integer varE) {
//        return solucionRecursivaFinalAux(EnteroCadena.of(varA, varB), varC, varD, varE, new HashMap<Integer, List<String>>());
//    }
//
//    private static Map<Integer, List<String>> solucionRecursivaFinalAux(EnteroCadena variableAcum, Integer varC, String varD, Integer varE,
//    																	 HashMap<Integer, List<String>> res) {
//    	if (variableAcum.a() < varC) {
//    		if (variableAcum.a() % 3 == 0) {
//                variableAcum = EnteroCadena.of(variableAcum.a() + 2, variableAcum.s() + variableAcum.a());
//            } else {
//                variableAcum = EnteroCadena.of(variableAcum.a() + 2, variableAcum.s().substring(variableAcum.a() % variableAcum.s().length()));
//            }
//    		
//    		String string = variableAcum.s() + varD;
//    		if (string.length() < varE) {
//    			if (!res.containsKey(string.length())) {
//        			List<String> lista = new ArrayList<String>();
//        			lista.add(string);
//        			res.put(string.length(), lista);}
//        		else {
//        			res.get(string.length()).add(string);
//        		}
//    		}  		 		
//    		return solucionRecursivaFinalAux(variableAcum, varC, varD, varE, res);
//    	}
//    	
//        return res;
//    }
    
//// #### primer intento de solución iterativa:
//
// public static Map<Integer, List<String>> solucionIterativa(Integer varA, String varB, Integer varC, String varD, Integer varE) {
//     Map<Integer, List<String>> res = new HashMap<>();
//     EnteroCadena acum = EnteroCadena.of(varA, varB);
//     List<EnteroCadena> lista1 = new ArrayList<>();
//     List<String> lista2 = new ArrayList<>();
//
//     while (acum.a() < varC) {
//         lista1.add(acum);
//         if (acum.a() % 3 == 0) {
//             acum = EnteroCadena.of(acum.a() + 2, acum.s() + acum.a());
//         } else {
//             acum = EnteroCadena.of(acum.a() + 2, acum.s().substring(acum.a() % acum.s().length()));
//         }
//     }
//
//     for (EnteroCadena e : lista1) {
//         String elem = e.s() + varD;
//         if (elem.length() < varE) {
//             lista2.add(e.s().concat(varD));
//         }
//     }
//
//     for (String s : lista2) {
//         if (!res.containsKey(s.length())) {
//             List<String> l = new ArrayList<>();
//             l.add(s);
//             res.put(s.length(), l);
//         } else {
//             if (!res.get(s.length()).contains(s)) {
//                 res.get(s.length()).add(s);
//             }
//         }
//     }
//
//     return res;
// }
    
}