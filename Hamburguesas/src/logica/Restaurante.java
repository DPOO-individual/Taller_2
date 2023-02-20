package logica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class Restaurante {
	
	private File combostxt = new File("./data/combos.txt");
	private File ingredientestxt = new File("./data/ingredientes.txt");
	private File menutxt = new File("./data/menu.txt");
	private HashMap<String, Combo> mapaCombos=new HashMap<String, Combo>();
	private HashMap<String, Ingrediente> mapaIngredientes=new HashMap<String, Ingrediente>();
	private HashMap<String, ProductoMenu> mapaMenuBase=new HashMap<String, ProductoMenu>();
	private Pedido pedidoEnCurso = null;
	private static ArrayList<Pedido> pedidos = new ArrayList<Pedido>();
	
	public Restaurante() 
		{cargarInformacionRestaurante(ingredientestxt, menutxt, combostxt);}
	
	public HashMap<String, Ingrediente> getMapaIngredientes()
		{return mapaIngredientes;}
	public HashMap<String, ProductoMenu> getMapaMenu()
		{return mapaMenuBase;}
	public HashMap<String, Combo> getMapaCombos()
		{return mapaCombos;}
	
	public void cargarInformacionRestaurante(File txtIngredientes, File txtMenu, File txtCombos) 
		{try 
			{cargarIngredientes(ingredientestxt);}
		catch (IOException e1) 
			{e1.printStackTrace();}
		try 
			{cargarMenu(menutxt);} 
		catch (IOException e) 
			{e.printStackTrace();}
		try 
			{cargarCombos(combostxt);} 
		catch (IOException e) 
			{e.printStackTrace();}}
	
	private void cargarIngredientes(File txt) throws IOException
		{BufferedReader br = new BufferedReader(new FileReader(txt));
		String linea = br.readLine();
		while (linea != null)
			{String[] partes = linea.split(";");
			String nombre = partes[0];
			int precioIngrediente = Integer.parseInt(partes[1]);
			Ingrediente ingrediente = new Ingrediente(nombre, precioIngrediente);
			mapaIngredientes.put(nombre, ingrediente);
			linea = br.readLine();}
		br.close();}

	private void cargarMenu(File txt) throws IOException
		{BufferedReader br = new BufferedReader(new FileReader(txt));
		String linea = br.readLine();
		while (linea != null)
			{String[] partes = linea.split(";");
			String nombre = partes[0];
			int precioProducto = Integer.parseInt(partes[1]);
			ProductoMenu producto = new ProductoMenu(nombre, precioProducto);
			mapaMenuBase.put(nombre, producto);
			linea = br.readLine();}
		br.close();}
		
	private void cargarCombos(File txt) throws IOException
			{BufferedReader br = new BufferedReader(new FileReader(txt));
			String linea = br.readLine();
			while (linea != null)
				{String[] partes = linea.split(";");
				String nombre = partes[0];
				double descuento = Double.parseDouble(partes[1].replaceAll("%", ""));
				Combo combo = new Combo(nombre, descuento);
				for(int i=2; i<partes.length; i++)
					{String stringItem = partes[i];
					ProductoMenu item = mapaMenuBase.get(stringItem);
					combo.agregarItemACombo(item);}
					mapaCombos.put(nombre, combo);
					linea = br.readLine();}
			br.close();}
	
	public ArrayList<ProductoMenu> getMenuBase()
		{Collection<ProductoMenu> setMenuBase = mapaMenuBase.values();
		ArrayList<ProductoMenu> arrayListMenuBase = new ArrayList<>(setMenuBase);
		return arrayListMenuBase;}
	
	public ArrayList<Combo> getCombos()
		{Collection<Combo> setCombos = mapaCombos.values();
		ArrayList<Combo> arrayCombos = new ArrayList<>(setCombos);
		return arrayCombos;}
	
	public ArrayList<Ingrediente> getIngredientes()
		{Collection<Ingrediente> setIngredientes = mapaIngredientes.values();
		ArrayList<Ingrediente> arrayIngredientes = new ArrayList<>(setIngredientes);
		return arrayIngredientes;}
	
	public void mostrarMenuBase()
		{ArrayList<ProductoMenu> arrayMenu = getMenuBase();
		int i = 1;
		System.out.println("\n\nMenu de productos:\n");
		for(ProductoMenu productoActual : arrayMenu)
			{System.out.println(i + "- " + productoActual.getNombre() + ": " + productoActual.getPrecio() + "$");
			i += 1;}}
	
	public void mostrarMenuCombos()
		{ArrayList<Combo> arrayCombos = getCombos();
		int i = 1;
		System.out.println("\n\nMenu de combos:\n");
		for(Combo comboActual : arrayCombos)
			{System.out.println("\n" + i + "- " + comboActual.getNombre() + " - Con su descuento de " + comboActual.getDescuento() + "% cuesta: "+ comboActual.getPrecio() + "$");
			for(ProductoMenu productoActual : comboActual.getItemsCombo())
				{System.out.println("	- " + productoActual.getNombre() + "-" + productoActual.getPrecio() + "$");};
			i += 1;}}
	
	public void mostrarIngredientes()
		{ArrayList<Ingrediente> arrayIngredientes = getIngredientes();
		int i = 1;
		System.out.println("\n\nLista de ingredientes:\n");
		for(Ingrediente productoActual : arrayIngredientes)
			{System.out.println(i + "- " + productoActual.getNombre() + ": " + productoActual.getCostoAdicional() + "$ adicionales");
			i += 1;}}
	
	public void iniciarPedido(String cliente, String direccion)
		{Pedido nuevoPedido = new Pedido(cliente, direccion);
		pedidoEnCurso = nuevoPedido;
		System.out.println("Tu pedido se inicio correctamente y su ID es: " + pedidoEnCurso.getIdPedido());}
	
	public Pedido getPedidoEnCurso()
		{return pedidoEnCurso;}
	
	public void cerrarYGuardarPedido()
		{if(pedidoEnCurso == null)
			{System.out.println("\nNo es posible cerrar un pedido porque no se ha iniciado. Por favor inicie un pedido primero y luego pruebe de nuevo.");}
		else
			{System.out.println("\nEl ID de su pedido es: " + pedidoEnCurso.getIdPedido() + ".");
			pedidos.add(pedidoEnCurso);
			pedidoEnCurso.guardarFactura(pedidoEnCurso);
			pedidoEnCurso = null;
			System.out.println("Su pedido se guardo y cerro correctamente. Ya puede consultar su factura.");}}
	
	public void getPedidoPorId(String idPedido) throws IOException
		{int indexPedido = Integer.parseInt(idPedido);
		if (indexPedido-1 > pedidos.size() || pedidos.size() == 0 || indexPedido <= 0)
			{System.out.println("Ese ID no existe. Prueba de nuevo.");}
		else
			{Pedido pedido = pedidos.get(indexPedido-1);
			System.out.println("\nEl ID de su pedido es: " + pedido.getIdPedido());
			System.out.println("\nSu nombre es: " + pedido.getNombreCliente());
			System.out.println("\nSu direccion es: " + pedido.getDireccionCliente());
			System.out.println("\nSu pedido contiene: ");
			for(I_Producto producto : pedido.getItemsPedido())
				{System.out.println(producto.getNombre());}}
		}
	
	public void agregarItemAPedido()
		{if (pedidoEnCurso != null)
			{System.out.println("Ingrese 1) para agregar un producto, 2) para agregar un combo, o 3) para agregar un producto ajustado.");
			int input = Integer.parseInt(Utiles.userInput("\nPor favor digite una opcion"));
			if(input == 1)
				{this.mostrarMenuBase();
				int elegido = Integer.parseInt(Utiles.userInput("\nPor favor digite una opcion"));
				ArrayList<ProductoMenu> arrayMenu = this.getMenuBase();
				ProductoMenu producto = arrayMenu.get(elegido-1);
				pedidoEnCurso.agregarProducto(producto);
				System.out.println(producto.getNombre() + " añadido correctamente.");}
			else if(input == 2)
				{this.mostrarMenuCombos();
				int elegido = Integer.parseInt(Utiles.userInput("\nPor favor digite una opcion"));
				ArrayList<Combo> arrayCombos = this.getCombos();
				Combo combo = arrayCombos.get(elegido-1);
				pedidoEnCurso.agregarProducto(combo);
				System.out.println(combo.getNombre() + " añadido correctamente en forma de combo.");
				ArrayList<ProductoMenu> itemsCombo = combo.getItemsCombo();
				for(ProductoMenu producto : itemsCombo)
					{System.out.println(producto.getNombre() + " añadido correctamente como parte del combo.");}}
			else if(input == 3)
				{this.mostrarMenuBase();
				int elegido = Integer.parseInt(Utiles.userInput("\nPor favor elija un producto base para modificar"));
				ArrayList<ProductoMenu> arrayMenu = this.getMenuBase();
				ProductoMenu producto = arrayMenu.get(elegido-1);
				ProductoAjustado ajustado = new ProductoAjustado(producto);
				int decision = Integer.parseInt(Utiles.userInput("\nDigite 1 para agregar algo o 2 para eliminar algo de su producto"));
				if(decision == 1)
					{this.mostrarIngredientes();
					int agregar = Integer.parseInt(Utiles.userInput("\nPor favor elija un producto base para modificar"));
					ArrayList<Ingrediente> arrayIngredientes = this.getIngredientes();
					Ingrediente ingrediente = arrayIngredientes.get(agregar-1);
					ajustado.agregarIngrediente(ingrediente);
					pedidoEnCurso.agregarProducto(ajustado);
					System.out.println(ajustado.getNombre() + " añadido correctamente.");
					for(Ingrediente ing : ajustado.getAgregados())
						{System.out.println(ing.getNombre() + " añadido correctamente");}}
				else if(decision == 2)
					{this.mostrarIngredientes();
					int eliminar = Integer.parseInt(Utiles.userInput("\nPor favor elija un producto base para modificar"));
					ArrayList<Ingrediente> arrayIngredientes = this.getIngredientes();
					Ingrediente ingrediente = arrayIngredientes.get(eliminar-1);
					ajustado.eliminarIngrediente(ingrediente);
					pedidoEnCurso.agregarProducto(ajustado);
					System.out.println(ajustado.getNombre() + " agregado correctamente.");
					for(Ingrediente ing : ajustado.getEliminados())
						{System.out.println(ing.getNombre() + " eliminado correctamente");}}
				else
					{System.out.println("La opcion ingresada no es valida. Intente de nuevo");}}
			else
				{System.out.println("La opcion ingresada no es valida. Intente de nuevo");}}
		else if (pedidoEnCurso == null)
			{System.out.println("Primero debes iniciar un pedido para añadirle items");}
		else
			{System.out.println("Ocurrio un error");}}
}
