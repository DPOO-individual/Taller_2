package logica;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Utiles
	{public static String userInput(String string)
		{try
			{System.out.print(string + ": ");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String userInput = br.readLine();
			return userInput;}
		catch (IOException e)
			{e.printStackTrace();}
		return "No se pudo leer lo ingresado";}
	}
