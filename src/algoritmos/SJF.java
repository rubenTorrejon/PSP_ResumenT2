package algoritmos;

import java.util.ArrayList;
import java.util.Collections;
import principal.Comparador;
import principal.Proceso;

public class SJF{
	    	
	//ESTADOS
	private int cicloSJF = 0;
	private ArrayList<Proceso> miListaSJF = new ArrayList<Proceso>();
	private ArrayList<Proceso> listaOrdenada = new ArrayList<Proceso>();
	int rafagasSJF = 0;

	
	
	//CONSTRUCTOR
	public SJF(ArrayList<Proceso> listaProcesos, int rafagasTotales) {
		miListaSJF = listaProcesos;
		rafagasSJF = rafagasTotales;
	}
	
		
	//COMPORTAMIENTOS
	/**
	 * Ejecutar el algoritmo SJF
	 * SHORTEST JOB FIRST
	 * Primero el trabajo más corto
	 */
	public void run() {
		int miCiclo = cicloSJF;
		int misRafagas = rafagasSJF;
		
		float indicePenalizacion = (float) 0.00;
		float penalizacion = (float) 0.00;
		float indicePenalizacionPorProceso =(float) 0.00;
		
		for (miCiclo = 0; miCiclo < misRafagas; miCiclo++) {
			//Compruebo si algún elemento coincide su llegada con el ciclo
			//Si coincide, lo añado a la cola
			for (int i = 0; i<miListaSJF.size(); i++) {
				if(miListaSJF.get(i).getLlegada() == cicloSJF) {
					listaOrdenada.add(miListaSJF.get(i));
				}
			}
			
			//Sumamos 1 al ciclo
			cicloSJF++;
			
			//Restamos 1 a la rafaga pendiente
			listaOrdenada.get(0).setRafaga((listaOrdenada.get(0).getRafaga())-1);
			
			//Escribimos la línea con la información del proceso
			//Si al proceso le quedan 0 ráfagas, ha terminado, así que lo sacamos de la cola
			if (listaOrdenada.get(0).getRafaga() == 0) {
				System.out.println(	"Ciclo " + cicloSJF + 
									". Proceso " + listaOrdenada.get(0).getNombre() + 
									". Ráfagas pendientes: " + listaOrdenada.get(0).getRafaga() +
									" FIN DEL PROCESO " + listaOrdenada.get(0).getNombre());
				//Indicamos el momento en el que ha abandonado la lista
				listaOrdenada.get(0).setSalida(cicloSJF);
				//Eliminamos el elemento que está al principio de la lista
				listaOrdenada.remove(0);
				//Ordenamos la lista que nos queda
				//para poner en primer lugar el elemento con menos ráfagas
				Collections.sort(listaOrdenada, new Comparador());
			}
			
			//Si le quedan ráfagas pendientes, mostramos la información y volvemos al principio
			else {
			System.out.println(	"Ciclo " + cicloSJF + 
					". Proceso " + listaOrdenada.get(0).getNombre() + 
					". Ráfagas pendientes: " + listaOrdenada.get(0).getRafaga());
			}
		}
		
		System.out.println("\n" + "Índices de penalización:" + "\n");
		
		//Para cada proceso, calculamos la penalización y la vamos añadiendo a un total
		for (int i = 0; i<miListaSJF.size() ; i++) {
			penalizacion = (float) (miListaSJF.get(i).getSalida()-miListaSJF.get(i).getLlegada()) / miListaSJF.get(i).getRafagaInicial();
			indicePenalizacionPorProceso = penalizacion / miListaSJF.size();
			indicePenalizacion = indicePenalizacion + indicePenalizacionPorProceso;
			System.out.println("Índice de penalización del proceso " + miListaSJF.get(i).getNombre() + ": " + penalizacion);
		}
		
		//Finalmente, mostramos la penalización total
        System.out.println("\n" + "Índice de Penalización total: " + indicePenalizacion);
		
	}
}