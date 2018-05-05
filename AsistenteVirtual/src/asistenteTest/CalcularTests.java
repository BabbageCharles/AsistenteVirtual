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
		assertEquals("@delucas 3", jenkins.calcular("1 + 2"));

		assertEquals("@delucas 1", jenkins.calcular("5 - 2 * 2"));

		assertEquals("@delucas 10", jenkins.hacerPorcentaje("10% de 100"));

		assertEquals("@delucas 42", jenkins.calcular("17 + 5 ^ 2"));

		
	}

	@Test
	public void calculosCompuestos() {
		assertEquals("@delucas -6", jenkins.calcular("(4-8)*2 + 4 / ( 1 + 1)"));


	}

}