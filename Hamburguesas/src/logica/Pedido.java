package logica;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Pedido 
	{/* atributos */
	private String nombreCliente = "default";
	private String direccionCliente = "default";
	private int idPedido = 0;
	private int numeroDePedido = 1;
	private ArrayList<I_Producto> itemsPedido = new ArrayList<>();
	
	/* constructor */
	public Pedido(String nombre, String direccion)
		{nombreCliente = nombre;
		direccionCliente = direccion;
		idPedido = numeroDePedido;
		numeroDePedido += 1;}

	/* getters */
	public String getNombreCliente() 
		{return nombreCliente;}
	public String getDireccionCliente() 
		{return direccionCliente;}
	public int getIdPedido() 
		{return idPedido;}
	public ArrayList<I_Producto> getItemsPedido()
		{return itemsPedido;}
	public double getPrecioNetoPedido()
		{double precio = 0;
		for(I_Producto producto : itemsPedido)
			{precio += producto.getPrecio();}
		return precio;}
	public double getPrecioIvaPedido()
		{return (getPrecioNetoPedido()*0.19);}
	public double getPrecioTotalPedido()
		{return (getPrecioNetoPedido() + getPrecioIvaPedido());}
	
	/* funciones */
	public void agregarProducto(I_Producto producto)
		{itemsPedido.add(producto);}
	
	public String generarTextoFactura()
		{String strFactura = "----- El Corral ----- \n	Pedido #" + this.getIdPedido() + ".";
		strFactura += "\nNombre del cliente: " + this.getNombreCliente() + ".";
		strFactura += "\nDireccion del cliente: " + this.getDireccionCliente() + ".";
		strFactura += "\nCompra: ";
		for(I_Producto item : itemsPedido)
			{strFactura += "\n" + item.generarTextoFactura();}
		strFactura += "\nPrecio de la factura\n----------";
		strFactura += "\nPrecio neto del pedido: " + this.getPrecioNetoPedido() + "$\n";
		strFactura += "\nPrecio del IVA: " + this.getPrecioIvaPedido() + "$\n";
		strFactura += "\nTotal a pagar: " + this.getPrecioTotalPedido() + "$"; 
		return strFactura;}
	
	public void guardarFactura(Pedido pedido)
		{String nombreFactura = "Factura " + pedido.getIdPedido();
		new File("./Facturas generadas/" + nombreFactura + ".txt");
		try 
			{FileWriter myWriter = new FileWriter("./Facturas generadas/" + nombreFactura + ".txt");
			myWriter.write(pedido.generarTextoFactura());
			myWriter.close();} 
		catch (IOException e) 
			{e.printStackTrace();}
		}
}
