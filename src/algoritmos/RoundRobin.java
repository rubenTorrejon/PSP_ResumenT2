package algoritmos;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import principal.Proceso;

public class RoundRobin {

	//ESTADOS
	private int cicloRR = 0;
	private ArrayList<Proceso> miListaRR = new ArrayList<Proceso>();
	private Queue<Proceso> colaProcesosRR = new LinkedList<Proceso>();
	int rafagasRR = 0;
	int quantumRR = 0;

	
	
	//CONSTRUCTOR
	public RoundRobin(ArrayList<Proceso> listaProcesos, int rafagasTotales, int quantum) {
		miListaRR = listaProcesos;
		rafagasRR = rafagasTotales;
		quantumRR = quantum;
	}
	
		
	//COMPORTAMIENTOS
	/**
	 * Ejecutar el algoritmo RR Q=n
	 * Round Robin, con Quantum igual a n
	 * Al acabarse el quantum (contador de ciclos) el proceso en ejecucion,
	 * haya acabado o no, se va al final de la cola.
	 */
	public void run() {
		int miCiclo = cicloRR;
		int misRafagas = rafagasRR;
		
		float indicePenalizacion = (float) 0.00;
		float penalizacion = (float) 0.00;
		float indicePenalizacionPorProceso =(float) 0.00;
		
		//Como vamos a reducir el valor del quantum antes del principio de ciclo
		//Sumamos 1 al quantum original, as� compensamos esa reducci�n en el primer
		//ciclo de ejecuci�n y realizamos un ciclo de quantum completo
		int miQuantum = quantumRR + 1;
		
		for (miCiclo = 0; miCiclo < misRafagas; miCiclo++) {
			//Compruebo si alg�n elemento coincide su llegada con el ciclo
			//Si coincide, lo a�ado a la cola
			for (int i = 0; i<miListaRR.size(); i++) {
				if(miListaRR.get(i).getLlegada() == cicloRR) {
					colaProcesosRR.add(miListaRR.get(i));
				}
			}
				
			//Sumamos 1 al ciclo
			cicloRR++;
				
			//Restamos 1 al quantum restante
			miQuantum--;
			//Si el quantum es 0, lo reiniciamos y enviamos el 1er elemento de la cola al final
			if (miQuantum == 0) {
				miQuantum = quantumRR;
				colaProcesosRR.add(colaProcesosRR.peek());
				colaProcesosRR.poll();
			}
			
			//Restamos 1 a la rafaga pendiente
			colaProcesosRR.peek().setRafaga(colaProcesosRR.peek().getRafaga()-1);
				
			//Escribimos la l�nea con la informaci�n del proceso
			//Si al proceso le quedan 0 r�fagas, ha terminado, as� que lo sacamos de la cola
			if (colaProcesosRR.peek().getRafaga() == 0) {

				System.out.println(	"Ciclo " + cicloRR + 
									". Proceso " + colaProcesosRR.peek().getNombre() + 
									". R�fagas pendientes: " + colaProcesosRR.peek().getRafaga() +
									" FIN DEL PROCESO " + colaProcesosRR.peek().getNombre());  
				colaProcesosRR.peek().setSalida(cicloRR);
				colaProcesosRR.poll();
				miQuantum = quantumRR+1;
			}				
			//Si le quedan r�fagas pendientes, mostramos la informaci�n y volvemos al principio
			else {
				System.out.println(	"Ciclo " + cicloRR + 
									". Proceso " + colaProcesosRR.peek().getNombre() + 
									". R�fagas pendientes: " + colaProcesosRR.peek().getRafaga());
			}
		}
		
		System.out.println("\n" + "�ndices de penalizaci�n:" + "\n");
		
		//Para cada proceso, calculamos la penalizaci�n y la vamos a�adiendo a un total
		for (int i = 0; i<miListaRR.size() ; i++) {
			penalizacion = (float) (miListaRR.get(i).getSalida()-miListaRR.get(i).getLlegada()) / miListaRR.get(i).getRafagaInicial();
			indicePenalizacionPorProceso = penalizacion / miListaRR.size();
			indicePenalizacion = indicePenalizacion + indicePenalizacionPorProceso;
			System.out.println("�ndice de penalizaci�n del proceso " + miListaRR.get(i).getNombre() + ": " + penalizacion);
		}
		
		//Finalmente, mostramos la penalizaci�n total
        System.out.println("\n" + "�ndice de Penalizaci�n total: " + indicePenalizacion);
        
	}
}		

