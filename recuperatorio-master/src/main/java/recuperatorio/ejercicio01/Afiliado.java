package recuperatorio.ejercicio01;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Afiliado {
	protected String nombre;
	protected String email;
	protected Integer nroDocumento;
	protected LocalDate fechaNacimiento;
	protected Double porcentajePrestacionMedica;
	protected Double porcentajePrestacionOdontologica;
	protected ArrayList<Prestacion> prestaciones;
	
	
	public ArrayList<Prestacion> getPrestaciones() {
		return prestaciones;
	}
	
	/*obtenemos una lista con todos los costos de prestaciones del tipo de prestaciones que queramos*/
	protected List<Double> listarCostos(TipoPrestacion t) {
		return this.getPrestaciones().stream()
				  .filter(a->a.getTipo().equals(t))
				  .map(a -> a.getPractica())
				  .map(a->a.getCosto())
				  .collect(Collectors.toList());
	}

	protected Double calcularCostoAFacturar(List<Double> l,TipoPrestacion t){
		return 0.0;
	}
	protected double facturar() {
		return 0.0;
	}
}
