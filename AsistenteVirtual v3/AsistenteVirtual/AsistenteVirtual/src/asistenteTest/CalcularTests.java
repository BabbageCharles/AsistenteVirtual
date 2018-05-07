package asistenteTest;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import asistenteVirtual.AsistenteVirtual;

public class CalcularTests {

	public final static String USUARIO = "delucas";

	AsistenteVirtual jenkins;

	@Before
	public void setup() {
		jenkins = new AsistenteVirtual("jenkins");
	}

	
	@Test
	public void calculos() {
		assertEquals("@delucas la respuesta es 3", jenkins.calculo("1 + 2"));

		assertEquals("@delucas la respuesta es 1", jenkins.calculo("5 - 2 * 2"));
		

		assertEquals("@delucas la respuesta es 10.2408", jenkins.porcentaje("10.2% de 100.4"));

		assertEquals("@delucas la respuesta es 42", jenkins.calculo("17 + 5 ^ 2"));

		
	}

	@Test
	public void calculosCompuestos() {
		assertEquals("@delucas la respuesta es -6", jenkins.calculo("(4-8)*2 + 4 / ( 1 + 1)"));


	}

}