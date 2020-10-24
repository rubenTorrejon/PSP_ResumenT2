package principal;

public class Proceso{

	//ESTADO
	private String nombre;
	private int llegada;
	int rafaga;
	private int rafagaInicial;
	int salida;
	
	
	//CONSTRUCTOR
	public Proceso(String miNombre, int miLlegada, int miRafaga) {
		nombre = miNombre;
		llegada = miLlegada;
		rafagaInicial = miRafaga;
		rafaga = miRafaga;
		salida = 0;
	}


	//COMPORTAMIENTOS
	//Getters
	public String getNombre() {
		return nombre;
	}


	public int getLlegada() {
		return llegada;
	}


	public int getRafaga() {
		return rafaga;
	}


	public int getRafagaInicial() {
		return rafagaInicial;
	}
	
	public int getSalida() {
		return salida;
	}

	//Setters
	public void setRafaga(int rafaga) {
		this.rafaga = rafaga;
	}

	public void setSalida(int salida) {
		this.salida = salida;
	}
}
