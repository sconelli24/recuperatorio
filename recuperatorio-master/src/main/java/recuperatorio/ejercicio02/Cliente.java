package recuperatorio.ejercicio02;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cliente {

	private Integer id;
	private List<Pedido> pedidos;
	/* par que contiene en el primer elemento el id del producto y el el segundo elemento la cantidad de ese producto que puede comprar como maximo */
	private ArrayList<Pair<Integer, Integer>> maximoPorProductosAComprar;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}
	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	
	public void crearPedido(Integer nroPedido) {
		if(this.pedidos==null)this.pedidos = new ArrayList<Pedido>();
		this.pedidos.add(new Pedido(nroPedido));
	}
	
	public Integer cantidadDeProductosDisponiblesParaUnProducto(Integer idProducto, Integer cantidad) {
		Integer maximo=0;
		for(Pair<Integer, Integer> aux: this.maximoPorProductosAComprar) {
			if(aux.getFirst()==idProducto) {
				maximo = aux.getSecond();
			}
		}
		return maximo-cantidad;
	}
	
	public void agregarProducto(Integer nroPedido, Integer idProducto,Integer cantidad) throws StockINsuficienteException, CantidadProductosInsuficienteException, DatabaseException {

					
		try{
			Producto p = Database.buscarProducto(idProducto);
			// verificar si el stock existente alcanza para agregarlo al pedido	
			if(p.getStock()>cantidad) {
				throw new StockINsuficienteException("No se pudo agregar la cantidad de productos solicitada debido a la falta de stock");
			}
			else if(this.cantidadDeProductosDisponiblesParaUnProducto(idProducto, cantidad)<0) {
				throw new CantidadProductosInsuficienteException("No hay suficientes productos");
			}
			else if(p == null)
				throw new BusquedaProductoException();
			// verificar si el cliente cumple la condicion pedida para agregar el producto
			Pedido pedido = this.buscarPorNro(nroPedido);
			pedido.addDetalle(p, cantidad);
		}
		catch(BusquedaProductoException e){
			System.out.println(e.getMessage());
		}
		catch(StockINsuficienteException e){
			System.out.println(e.getMessage());
		}
		catch(CantidadProductosInsuficienteException e){
			System.out.println(e.getMessage());
		}
		
	}
	
	public Pedido buscarPorNro(Integer nroPedido) {
		for(Pedido p : this.pedidos) {
			if(p.getNroPedido().equals(nroPedido)) return p;
		}
		return null;
	}
	
//	public List<Producto> productosMontoMayor(Double monto){};
	
	public List<Pedido> buscarNPedidosCostoMinimo(Double precMin,Integer n){
		return pedidos.stream()
						.filter((a)-> (a.montoPedido()>precMin))	/* Me quedo con los pedidos que itenen un precio mayor al precio minimo */
						.sorted((a,b) -> (a.compareTo(b)))			/* Ordeno de menor a mayor la lista de pedidos */
						.limit(n)									/* Tomo los primeros n que estan ordenados de menor a mayor */
						.collect(Collectors.toList());				/* los retorno en una lista */
	}
	public Double compraPromedio() {
		Double resultado=0.0;
		List<Double>costoPedidos = pedidos.stream()
								.map(a->a.montoPedido())
								.collect(Collectors.toList());
		for(Double aux:costoPedidos) {
			resultado+=aux;
		}
		resultado/=pedidos.stream().count();
		return resultado; 
	};

	
}
