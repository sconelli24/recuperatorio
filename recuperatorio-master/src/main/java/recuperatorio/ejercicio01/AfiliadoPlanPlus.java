package recuperatorio.ejercicio01;

import java.util.List;

public class AfiliadoPlanPlus extends Afiliado {
	private String cuitEmpleador;
	
	public AfiliadoPlanPlus() {
		this.porcentajePrestacionMedica = 0.25;
		this.porcentajePrestacionOdontologica = 0.5;
	}
	@Override
	public Double calcularCostoAFacturar(List<Double> l,TipoPrestacion t){
		Double resultado=0.0;
		if(t == TipoPrestacion.MEDICA) {
			for(Double elemento: l) {
				resultado += elemento*this.porcentajePrestacionMedica+elemento;
			}
		}
		else if(t == TipoPrestacion.ODONTOLOGICA) {
			for(Double elemento: l) {
				resultado += elemento*this.porcentajePrestacionOdontologica+elemento;
			}
		}		
		return resultado;
	}
	@Override
	protected double facturar() {
		List<Double> listaCostosOdontologicos = this.listarCostos(TipoPrestacion.ODONTOLOGICA);
		List<Double> listaCostosMedicos = this.listarCostos(TipoPrestacion.MEDICA);
		
		Double resultado = 0.0;
		resultado += this.calcularCostoAFacturar(listaCostosMedicos, TipoPrestacion.MEDICA)
				  +  this.calcularCostoAFacturar(listaCostosOdontologicos, TipoPrestacion.ODONTOLOGICA);
		
		
		return resultado;
	}
}
