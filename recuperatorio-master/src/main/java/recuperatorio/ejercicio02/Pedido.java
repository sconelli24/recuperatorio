package recuperatorio.ejercicio02;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Pedido implements Comparable<Pedido>{
	private Integer nroPedido;
	private LocalDate fecha;
	private List<PedidoDetalle> detalles;
	
	public Pedido() {
		this.fecha = LocalDate.now();
		this.detalles = new ArrayList<PedidoDetalle>();
	}
	
	public Pedido(Integer nroPedido) {
		this();
		this.nroPedido = nroPedido;
	}
	
	public Integer getNroPedido() {
		return nroPedido;
	}

	public void setNroPedido(Integer nroPedido) {
		this.nroPedido = nroPedido;
	}

	public Pedido(LocalDate fecha, List<PedidoDetalle> detalles) {
		super();
		this.fecha = fecha;
		this.detalles = detalles;
	}

	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	public List<PedidoDetalle> getDetalles() {
		return detalles;
	}
	public void setDetalles(List<PedidoDetalle> detalles) {
		this.detalles = detalles;
	}

	public void addDetalle(Producto p, Integer c) {
		if(this.detalles==null) this.detalles = new ArrayList<PedidoDetalle>();
		this.detalles.add(new PedidoDetalle(c,p));
	}
	
	/*calcula el monto del pedido*/
	public Double montoPedido() {
		Double sumaPreciosTotal=0.0;
		List<Double>listaPreciosPedido = this.detalles.stream()
											.map(a->a.getProducto())
											.map(a->a.getPrecio())
											.collect(Collectors.toList());
		for(Double precio:listaPreciosPedido) {
			sumaPreciosTotal += precio;
		}
		return sumaPreciosTotal;
	}

	@Override
	public int compareTo(Pedido o) {
		return (int) (o.montoPedido()-this.montoPedido());
	}
	
}
