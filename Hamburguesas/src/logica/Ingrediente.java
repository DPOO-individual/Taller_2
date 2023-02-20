package logica;

public class Ingrediente {
	
	/* atributos*/
	private String nombre;
	private double costoAdicional;
	
	/* constructor*/
	public Ingrediente(String elNombre, double elCosto) 
		{this.nombre = elNombre;
		this.costoAdicional = elCosto;}
	
	/* getters */
	public String getNombre()
		{return nombre;}
	
	public double getCostoAdicional()
		{return costoAdicional;}
}
