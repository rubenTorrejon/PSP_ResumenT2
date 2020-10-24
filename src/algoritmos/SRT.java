package algoritmos;

import java.util.ArrayList;
import java.util.Collections;
import principal.Comparador;
import principal.Proceso;

public class SRT{
	    	
	//ESTADOS
	private int cicloSRT = 0;
	private ArrayList<Proceso> miListaSRT = new ArrayList<Proceso>();
	private ArrayList<Proceso> listaOrdenada = new ArrayList<Proceso>();
	int rafagasSRT = 0;

	
	
	//CONSTRUCTOR
	public SRT(ArrayList<Proceso> listaProcesos, int rafagasTotales) {
		miListaSRT = listaProcesos;
		rafagasSRT = rafagasTotales;
	}
	
		
	//COMPORTAMIENTOS
	/**
	 * Ejecutar el algoritmo SRT
	 * SHORTEST REMAINING TIME
	 * Primero el trabajo más corto, cortando si es necesario
	 */
	public void run() {
		int miCiclo = cicloSRT;
		int misRafagas = rafagasSRT;
		
		float indicePenalizacion = (float) 0.00;
		float penalizacion = (float) 0.00;
		float indicePenalizacionPorProceso =(float) 0.00;
		
		for (miCiclo = 0; miCiclo < misRafagas; miCiclo++) {
			//Compruebo si algún elemento coincide su llegada con el ciclo
			//Si coincide, lo añado a la cola
			for (int i = 0; i<miListaSRT.size(); i++) {
				if(miListaSRT.get(i).getLlegada() == cicloSRT) {
					listaOrdenada.add(miListaSRT.get(i));
				}
			}
			
			//Ordenamos la lista que nos queda
			//para poner en primer lugar el elemento con menos ráfagas
			Collections.sort(listaOrdenada, new Comparador());
			
			//Sumamos 1 al ciclo
			cicloSRT++;
			
			//Restamos 1 a la rafaga pendiente
			listaOrdenada.get(0).setRafaga((listaOrdenada.get(0).getRafaga())-1);
			
			//Escribimos la línea con la información del proceso
			//Si al proceso le quedan 0 ráfagas, ha terminado, así que lo sacamos de la cola
			if (listaOrdenada.get(0).getRafaga() == 0) {
				System.out.println(	"Ciclo " + cicloSRT + 
									". Proceso " + listaOrdenada.get(0).getNombre() + 
									". Ráfagas pendientes: " + listaOrdenada.get(0).getRafaga() +
									" FIN DEL PROCESO " + listaOrdenada.get(0).getNombre());
				//Obtenemos el valor del ciclo de salida
				listaOrdenada.get(0).setSalida(cicloSRT);
				//Eliminamos el elemento que está al principio de la lista
				listaOrdenada.remove(0);
			}
			
			//Si le quedan ráfagas pendientes, mostramos la información y volvemos al principio
			else {
			System.out.println(	"Ciclo " + cicloSRT + 
					". Proceso " + listaOrdenada.get(0).getNombre() + 
					". Ráfagas pendientes: " + listaOrdenada.get(0).getRafaga());
			}
		}
		
		System.out.println("\n" + "Índices de penalización:" + "\n");
		
		//Para cada proceso, calculamos la penalización y la vamos añadiendo a un total
		for (int i = 0; i<miListaSRT.size() ; i++) {
			penalizacion = (float) (miListaSRT.get(i).getSalida()-miListaSRT.get(i).getLlegada()) / miListaSRT.get(i).getRafagaInicial();
			indicePenalizacionPorProceso = penalizacion / miListaSRT.size();
			indicePenalizacion = indicePenalizacion + indicePenalizacionPorProceso;
			System.out.println("Índice de penalización del proceso " + miListaSRT.get(i).getNombre() + ": " + penalizacion);
		}
		
		//Finalmente, mostramos la penalización total
        System.out.println("\n" + "Índice de Penalización total: " + indicePenalizacion);
		
	}
}
