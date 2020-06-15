package recuperatorio.ejercicio01;

import java.util.List;

public class AfiliadoPlanJoven extends Afiliado {
	private String universidad;
	private String carrera;
	private Integer costoFijoPrestacionMedica;
	
	public AfiliadoPlanJoven() {
		this.porcentajePrestacionMedica = 0.2;
		this.costoFijoPrestacionMedica = 150;
		this.porcentajePrestacionOdontologica = 0.8;
	}
	@Override
	public Double calcularCostoAFacturar(List<Double> l,TipoPrestacion t){
		Double resultado=0.0;
		if(t == TipoPrestacion.MEDICA) {
			for(Double elemento: l) {
				resultado += elemento*this.porcentajePrestacionMedica+elemento+150;
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
