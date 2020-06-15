package recuperatorio.ejercicio01;

import java.time.LocalDate;

public class Prestacion {
	private Practica practica;
	private LocalDate fecha;
	private Boolean preAprobada;
	private Afiliado afiliado;
	private TipoPrestacion tipo;
	
	public TipoPrestacion getTipo() {
		return tipo;
	}

	public Practica getPractica() {
		return practica;
	}

}
