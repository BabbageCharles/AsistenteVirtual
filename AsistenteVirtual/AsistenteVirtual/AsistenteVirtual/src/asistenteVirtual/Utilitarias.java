package asistenteVirtual;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utilitarias {

	public static int buscarEntero(String s) {
		String regexp = "([0-9]+)";
		Pattern pattern = Pattern.compile(regexp);
		Matcher matcher = pattern.matcher(s);
		matcher.find();
		int i = Integer.parseInt(matcher.group());

		return i;
	}
	
}
