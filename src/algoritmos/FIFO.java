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
			//Compruebo si alg�n elemento coincide su llegada con el ciclo
			//Si coincide, lo a�ado a la cola
			for (int i = 0; i<miListaFIFO.size(); i++) {
				if(miListaFIFO.get(i).getLlegada() == cicloFIFO) {
					colaProcesosFIFO.add(miListaFIFO.get(i));
				}
			}
			
			//Sumamos 1 al ciclo
			cicloFIFO++;
			
			//Restamos 1 a la rafaga pendiente
			colaProcesosFIFO.peek().setRafaga(colaProcesosFIFO.peek().getRafaga()-1);
			
			//Escribimos la l�nea con la informaci�n del proceso
			//Si al proceso le quedan 0 r�fagas, ha terminado, as� que lo sacamos de la cola
			if (colaProcesosFIFO.peek().getRafaga() == 0) {
				System.out.println(	"Ciclo " + cicloFIFO + 
									". Proceso " + colaProcesosFIFO.peek().getNombre() + 
									". R�fagas pendientes: " + colaProcesosFIFO.peek().getRafaga() +
									" FIN DEL PROCESO " + colaProcesosFIFO.peek().getNombre());
				//Obtenemos el ciclo en el que termina el proceso para el c�lculo del indice de penalizaci�n
				colaProcesosFIFO.peek().setSalida(cicloFIFO);
				//Eliminamos el proceso de la cola
				colaProcesosFIFO.poll();
			}
			//Si le quedan r�fagas pendientes, mostramos la informaci�n y volvemos al principio
			else {
				System.out.println(	"Ciclo " + cicloFIFO + 
									". Proceso " + colaProcesosFIFO.peek().getNombre() + 
									". R�fagas pendientes: " + colaProcesosFIFO.peek().getRafaga());
			}
		}
		
		System.out.println("\n" + "�ndices de penalizaci�n:" + "\n");
		
		//Para cada proceso, calculamos la penalizaci�n y la vamos a�adiendo a un total
		for (int i = 0; i<miListaFIFO.size() ; i++) {
			penalizacion = (float) (miListaFIFO.get(i).getSalida()-miListaFIFO.get(i).getLlegada()) / miListaFIFO.get(i).getRafagaInicial();
			indicePenalizacionPorProceso = penalizacion / miListaFIFO.size();
			indicePenalizacion = indicePenalizacion + indicePenalizacionPorProceso;
			System.out.println("�ndice de penalizaci�n del proceso " + miListaFIFO.get(i).getNombre() + ": " + penalizacion);
		}
		
		//Finalmente, mostramos la penalizaci�n total
        System.out.println("\n" + "�ndice de Penalizaci�n total: " + indicePenalizacion);
		
	}
}