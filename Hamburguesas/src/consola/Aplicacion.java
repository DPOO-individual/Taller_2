package consola;

import java.io.IOException;

import logica.*;
/*import logica.Utiles;
import logica.Restaurante;*/

public class Aplicacion 
	{private Restaurante restaurante = new Restaurante();
	
	public void ejecutarOpcion()
		{System.out.println("Bienvenido al restaurante :) \n");
		boolean centinela = true;
		while (centinela)
			{try
				{mostrarMenu();
				int input = Integer.parseInt(Utiles.userInput("\nPor favor digite una opcion"));
				if (input == 1)
					{restaurante.mostrarMenuBase();
					restaurante.mostrarMenuCombos();
					restaurante.mostrarIngredientes();}
				else if (input == 2)
					{String nombreCliente = Utiles.userInput("\nPor favor ingrese su nombre");
					String direccion = Utiles.userInput("\nPor favor ingrese su direccion");
					restaurante.iniciarPedido(nombreCliente, direccion);}
				else if (input == 3)
					restaurante.agregarItemAPedido();
				else if (input == 4)
					restaurante.cerrarYGuardarPedido();
				else if (input == 5)
					{String idPedido = Utiles.userInput("\nPor favor ingrese el ID de su pedido");
					try 
						{restaurante.getPedidoPorId(idPedido);} 
					catch (IOException e) 
						{e.printStackTrace();}}
				else if (input == 6)
					{System.out.println("\nHasta luego :D");
					centinela = false;}
				else
					{System.out.println("Por favor seleccione una opción válida.");}}
			catch (NumberFormatException e)
				{System.out.println("Debe seleccionar uno de los números de las opciones.");}}}
	
	public void mostrarMenu()
		{System.out.println("\nOpciones de usuario\n");
		System.out.println("1. Mostrar el menu");
		System.out.println("2. Iniciar un nuevo pedido y/o remplazar el pedido en curso");
		System.out.println("3. Añadir un item a la orden");
		System.out.println("4. Cerrar y guardar el pedido");
		System.out.println("5. Revisar un pedido cerrado segun su ID");
		System.out.println("6. Salir del programa");}
	
	public static void main(String[] args)
		{Aplicacion consola = new Aplicacion();
		consola.ejecutarOpcion();}
	}
