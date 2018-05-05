package  asistenteTest;


import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import asistenteVirtual.AsistenteVirtual;

public class DarFechaTests {

	public final static String USUARIO = "delucas";
	public final static Date FECHA_HORA = new GregorianCalendar(2018, 3, 1, 15, 15, 0).getTime();
	
	AsistenteVirtual jenkins;
	
	@Before
	public void setup() {
		jenkins = new AsistenteVirtual("jenkins");
	}
	
	@Test
	public void buscarNumero() {
		Assert.assertEquals(222,jenkins.buscarEntero("@jenkins ququ�a dia ser� dentro de 222 dias?"));
	}
	
	@Test
	public void diaDentroDe() {
		Assert.assertEquals(
				"@delucas ser� el martes 3 de abril de 2018",
				jenkins.darFecha("@jenkins qu� dia ser� dentro de 2 dias?")
			);
		
		Assert.assertEquals(
				"@delucas ser� el viernes 1 de junio de 2018",
				jenkins.darFecha("@jenkins qu� dia ser� dentro de 2 meses?")
			);
		
		Assert.assertEquals(
				"@delucas ser� el mi�rcoles 1 de abril de 2020",
				jenkins.darFecha("@jenkins qu� dia sera dentro de 2 a�os?")
			);
	}
	
	@Test
	public void diaHace() {
		Assert.assertEquals(
				"@delucas fue el s�bado 31 de marzo de 2018",
				jenkins.darFecha("@jenkins que dia fue ayer?")
			);
		
		Assert.assertEquals(
				"@delucas fue el jueves 29 de marzo de 2018",
				jenkins.darFecha("@jenkins que dia fue hace 3 dias?")
			);
		
		Assert.assertEquals(
				"@delucas fue el jueves 1 de febrero de 2018",
				jenkins.darFecha("@jenkins que dia fue hace 2 meses?")
			);
		
		Assert.assertEquals(
				"@delucas fue el viernes 1 de abril de 2016",
				jenkins.darFecha("@jenkins que dia fue hace 2 a�os?")
			);
	}
	
	@Test
	public void tiempoDesde() {
		Assert.assertEquals(
				"@delucas entre el 1 de abril de 2017 y el 1 de abril de 2018 pasaron 365 d�as",
				jenkins.darFecha("@jenkins cuantos dias pasaron desde el 1 de abril de 2017?")
			);
		
		Assert.assertEquals(
				"@delucas entre el 1 de marzo de 2018 y el 1 de abril de 2018 pasaron 31 d�as",
				jenkins.darFecha("@jenkins cuantos dias pasaron desde el 1 de marzo de 2018?")
			);
		
		
	}
	
	
	@Test
	public void tiempoHasta() {
		Assert.assertEquals(
				"@delucas faltan 9 dias",
				jenkins.darFecha("@jenkins cuantos dias faltan para el 10 de abril?")
			);		
		Assert.assertEquals(
				"@delucas faltan 365 dias",
				jenkins.darFecha("@jenkins cuantos dias faltan para el 1 de abril de 2019?")
			);	
		
		Assert.assertEquals(
				"@delucas faltan 1 dias",
				jenkins.darFecha("@jenkins cuantos dias faltan para el 2 de abril?")
			);	
	}
}