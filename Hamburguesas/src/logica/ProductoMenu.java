package logica;

public class ProductoMenu implements I_Producto{
	
	/* atributos*/
	private String nombre;
	private double precioBase;
	
	/* constructor*/
	public ProductoMenu(String elNombre, double elPrecio) 
		{this.nombre = elNombre;
		this.precioBase = elPrecio;}
	
	/* getters */
	public String getNombre()
		{return nombre;}
	
	public double getPrecio()
		{return precioBase;}

	public String generarTextoFactura()
		{String strFactura = "";
		strFactura += "\nProducto: " + this.getNombre() + " - " + this.getPrecio() + "$\n";
		strFactura += "-----------------------";
		return strFactura;}

}
