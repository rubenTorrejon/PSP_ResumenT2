package algoritmos;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import principal.Proceso;


public class FIFO {

	//ESTADOS
	private int cicloFIFO = 0;
	private ArrayList<Proceso> miListaFIFO = new ArrayList<Proceso>();
	private Queue<Proceso> colaProcesosFIFO = new LinkedList<Proceso>();
	int rafagasFIFO = 0;
	
	
	//CONSTRUCTOR
	public FIFO(ArrayList<Proceso> listaProcesosFIFO, int rafagasTotales) {
		miListaFIFO = listaProcesosFIFO;
		rafagasFIFO = rafagasTotales;
	}
	
		
	//COMPORTAMIENTOS
	/**
	 * Ejecutar el algoritmo FIFO
	 * FIRST IN, FIRST OUT
	 * Primero que entra, primero que sale
	 */
	public void run() {
		
		int miCiclo = cicloFIFO;
		int misRafagas = rafagasFIFO;
		
		float indicePenalizacion = (float) 0.00;
		float penalizacion = (float) 0.00;
		float indicePenalizacionPorProceso =(float) 0.00;
		
		for (miCiclo = 0; miCiclo < misRafagas; miCiclo++) {
			//Compruebo si algún elemento coincide su llegada con el ciclo
			//Si coincide, lo añado a la cola
			for (int i = 0; i<miListaFIFO.size(); i++) {
				if(miListaFIFO.get(i).getLlegada() == cicloFIFO) {
					colaProcesosFIFO.add(miListaFIFO.get(i));
				}
			}
			
			//Sumamos 1 al ciclo
			cicloFIFO++;
			
			//Restamos 1 a la rafaga pendiente
			colaProcesosFIFO.peek().setRafaga(colaProcesosFIFO.peek().getRafaga()-1);
			
			//Escribimos la línea con la información del proceso
			//Si al proceso le quedan 0 ráfagas, ha terminado, así que lo sacamos de la cola
			if (colaProcesosFIFO.peek().getRafaga() == 0) {
				System.out.println(	"Ciclo " + cicloFIFO + 
									". Proceso " + colaProcesosFIFO.peek().getNombre() + 
									". Ráfagas pendientes: " + colaProcesosFIFO.peek().getRafaga() +
									" FIN DEL PROCESO " + colaProcesosFIFO.peek().getNombre());
				//Obtenemos el ciclo en el que termina el proceso para el cálculo del indice de penalización
				colaProcesosFIFO.peek().setSalida(cicloFIFO);
				//Eliminamos el proceso de la cola
				colaProcesosFIFO.poll();
			}
			//Si le quedan ráfagas pendientes, mostramos la información y volvemos al principio
			else {
				System.out.println(	"Ciclo " + cicloFIFO + 
									". Proceso " + colaProcesosFIFO.peek().getNombre() + 
									". Ráfagas pendientes: " + colaProcesosFIFO.peek().getRafaga());
			}
		}
		
		System.out.println("\n" + "Índices de penalización:" + "\n");
		
		//Para cada proceso, calculamos la penalización y la vamos añadiendo a un total
		for (int i = 0; i<miListaFIFO.size() ; i++) {
			penalizacion = (float) (miListaFIFO.get(i).getSalida()-miListaFIFO.get(i).getLlegada()) / miListaFIFO.get(i).getRafagaInicial();
			indicePenalizacionPorProceso = penalizacion / miListaFIFO.size();
			indicePenalizacion = indicePenalizacion + indicePenalizacionPorProceso;
			System.out.println("Índice de penalización del proceso " + miListaFIFO.get(i).getNombre() + ": " + penalizacion);
		}
		
		//Finalmente, mostramos la penalización total
        System.out.println("\n" + "Índice de Penalización total: " + indicePenalizacion);
		
	}
}