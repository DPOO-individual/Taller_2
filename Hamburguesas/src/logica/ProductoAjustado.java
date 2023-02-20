package logica;

import java.util.ArrayList;

public class ProductoAjustado implements I_Producto{
	
	/* atributos */
	private ProductoMenu productoBase = null;
	private ArrayList<Ingrediente> arrayAgregados = new ArrayList<Ingrediente>();
	private ArrayList<Ingrediente> arrayEliminados = new ArrayList<Ingrediente>();
	
	/* constructor */
	public ProductoAjustado(ProductoMenu producto) 
		{productoBase = producto;}

	/* getters */
	public String getNombre()
		{return productoBase.getNombre();}
	
	public ArrayList<Ingrediente> getAgregados()
		{return arrayAgregados;}
	
	public ArrayList<Ingrediente> getEliminados()
		{return arrayEliminados;}
	
	public double getPrecio()
		{double precio = productoBase.getPrecio();
		for(Ingrediente ingrediente : arrayAgregados)
			{precio += ingrediente.getCostoAdicional();}
		return precio;}
	
	/* funciones */
	public String generarTextoFactura()
		{String strFactura = "";
		strFactura += "\nProducto Base: " + this.getNombre() + " - " + getPrecio() + "$";
		if (arrayAgregados.size() > 0)
			{strFactura += "	\n	Ingredientes Agregados\n";
			for (Ingrediente ing : arrayAgregados)
				{strFactura += "	 * " + ing.getNombre() + " - " + ing.getCostoAdicional() + "\n";}}		
		if (arrayEliminados.size() > 0)
			{strFactura += "\n	Ingredientes Eliminados\n";
			for (Ingrediente ing : arrayEliminados)
				{strFactura += "	 * " + ing.getNombre() + "\n";}}
		strFactura += "-----------------------";
		return strFactura;}
	
	public void agregarIngrediente(Ingrediente ingrediente)
		{arrayAgregados.add(ingrediente);}
	
	public void eliminarIngrediente(Ingrediente ingrediente)
		{arrayEliminados.add(ingrediente);}
}
