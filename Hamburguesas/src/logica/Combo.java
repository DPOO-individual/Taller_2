package logica;

import java.util.ArrayList;

public class Combo implements I_Producto{
	
	/* atributos*/
	private String nombre;
	private double descuento;
	private ArrayList<ProductoMenu> itemsCombo = new ArrayList<ProductoMenu>();
			
	/* constructor*/
	public Combo(String elNombre, double elDescuento) 
		{this.nombre = elNombre;
		this.descuento = elDescuento;}
	
	/* getters */
	public String getNombre()
		{return nombre;}
	
	public double getPrecio()
		{double precio = 0;
		for(ProductoMenu productoActual : itemsCombo)
			{precio += productoActual.getPrecio();}
		double descontar = (precio * descuento * 0.01);
		return (precio - descontar);}
	public double getDescuento()
		{return descuento;}
	
	public ArrayList<ProductoMenu> getItemsCombo()
		{return itemsCombo;}
	
	/* funciones */
	public void agregarItemACombo(ProductoMenu item)
		{this.itemsCombo.add(item);}
	
	public String generarTextoFactura()
		{String strFactura = "";
		strFactura += "Combo: " + this.getNombre() + " - " + this.getPrecio() + "$";
		strFactura += "\nDescuento realizado: " + this.getDescuento() + "%";
		for (int i = 0; i < this.itemsCombo.size(); i++)
		{strFactura += ("\n * " + this.itemsCombo.get(i).getNombre() + " - " + this.itemsCombo.get(i).getPrecio() + "$");}
		strFactura += "\n----------------------";
		return strFactura;}
}
