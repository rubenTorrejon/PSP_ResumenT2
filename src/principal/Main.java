package principal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import algoritmos.FIFO;
import algoritmos.RoundRobin;
import algoritmos.SJF;
import algoritmos.SRT;

public class Main {

	//Indicamos qué procesos se van a ejecutar
	private static Proceso proceso1 = new Proceso("A",0,5);
	private static Proceso proceso2 = new Proceso("B",2,4);
	private static Proceso proceso3 = new Proceso("C",3,3);
	private static Proceso proceso4 = new Proceso("D",5,2);
	private static Proceso proceso5 = new Proceso("E",6,3);
	
	//Creamos una lista con todos los procesos
	static ArrayList<Proceso> listaProcesos = new ArrayList<Proceso>();
	
	//Sumamos el total de ráfagas
	private static int rafagasTotales = 0;
	
	//Indicamos el quantum que vamos a aplicar en el caso de Round Robin
	static int quantum = 2;
	
	/**
	 * Añadimos todos los procesos a la lista
	 */
	public static void addProcesos() {
		listaProcesos.add(proceso1);
		listaProcesos.add(proceso2);
		listaProcesos.add(proceso3);
		listaProcesos.add(proceso4);
		listaProcesos.add(proceso5);
	}
	
	/**
	 * Sumamos todas las rafagas para obtener la ráfaga total
	 */
	public static void sumarRafaga() {
		int misRafagas = 0;
		for (int i = 0; i < listaProcesos.size(); i++) {
			misRafagas = misRafagas + listaProcesos.get(i).getRafagaInicial();
		}
		rafagasTotales = misRafagas;
	}
	
	/**
	 * Ejecutamos el inicio de la app, en el que se nos preguntará qué algoritmo usar
	 */
	@SuppressWarnings("resource")
	public static void elegirAccion() throws IOException {
		System.out.println("Bienvenido al calculador de algoritmos. Seleccione el algoritmo que desea ejecutar:"+"\n"+"\n"+
						   "Pulse 1 para ejecutar el algoritmo FIFO" + "\n" +
						   "Pulse 2 para ejecutar el algoritmo SJF"  + "\n" +
						   "Pulse 3 para ejecutar el algoritmo SRT"  + "\n" + 
						   "Pulse 4 para ejecutar el algoritmo Round Robin" + "\n") ;
		Scanner lector = new Scanner(System.in);
		String accion = lector.nextLine();
		seleccionar(accion);
	}
	
	//Indicamos qué hacer en cada eleccion de algoritmo
	public static void seleccionar (String accion) throws IOException {
        switch (accion) {

        case "1":
    		System.out.println("\n" + "Algoritmo FIFO:" + "\n");
    		FIFO miFIFO = new FIFO(listaProcesos, rafagasTotales);
    		miFIFO.run();
            break;

        case "2":
    		System.out.println("\n" + "Algoritmo SJF:" + "\n");
    		SJF miSJF = new SJF(listaProcesos, rafagasTotales);
    		miSJF.run();
            break;

        case "3":
    		System.out.println("\n" + "Algoritmo SRT:" + "\n");
    		SRT miSRT = new SRT(listaProcesos, rafagasTotales);
    		miSRT.run();
            break;

        case "4":
    		System.out.println("\n" + "Algoritmo RR:" + "\n");
    		RoundRobin miRR = new RoundRobin(listaProcesos, rafagasTotales, quantum);
    		miRR.run();
            break;
            
        default:
        	System.out.println("\n" + "Selección incorrecta. Por favor, seleccione una acción de la lista." + "\n");
    		Scanner lector = new Scanner(System.in);
    		String accion1 = lector.nextLine();
    		seleccionar(accion1);
        	break;
        }
    }
	
	//CONSTUCTOR MAIN
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		addProcesos();
		sumarRafaga();
		elegirAccion();
	}
}
