package asistenteTest;

import static org.junit.Assert.assertEquals;

import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import asistenteVirtual.AsistenteEscucha;

class EscucharTests {
	String salida;
	AsistenteEscucha jenkins;
	public final static String USUARIO = "delucas";
	public final static Date FECHA_HORA = new GregorianCalendar(2018, 3, 1, 15, 15, 0).getTime();

	@Before
	public void setUp() {
		salida = new String();
		jenkins = new AsistenteEscucha();
	}

	@Test // MSJES_SIN SENTIDO
	public void sinsentido() {
		String[] mensajes = { "Este mensaje no tiene sentido @jenkins" };
		for (String mensaje : mensajes) {
			Assert.assertEquals("Disculpa... no entiendo el pedido, @delucas �podr�as repetirlo?",
					jenkins.escuchar(mensaje));
		}
	}

	@Test // CON SALUDO
	public void saludo_1() {
		String[] mensajes = { "�Hola, @jenkins!", "@jenkins hola!", "buen d�a @jenkins", "@jenkins, buenas tardes",
				"hey @jenkins" };
		for (String mensaje : mensajes) {
			Assert.assertEquals("�Hola, @delucas!", jenkins.escuchar(mensaje));
		}
	}

	@Test
	public void saludo_2() { // despedida
		salida = jenkins.escuchar("chau");
		assertEquals("Espero haberte sido de ayuda, adios!", salida);
	}

	@Test
	public void agradecimiento() {
		String[] mensajes = { "�Muchas gracias, @jenkins!", "@jenkins gracias", "gracias @jenkins" };
		for (String mensaje : mensajes) {
			assertEquals("No es nada, @delucas", jenkins.escuchar(mensaje));
		}
	}

	
	// CON FECHA
	
	@Test
	public void hora() {
		String[] mensajes = {
				"�qu� hora es, @jenkins?",
				"@jenkins, la hora por favor",
				"me dec�s la hora @jenkins?"
		};
		for (String mensaje : mensajes) {
			Assert.assertEquals(
					"@delucas son las 3:15 PM",
					jenkins.escuchar(mensaje)
			);
		}
	}
	
	@Test
	public void fecha() {
		String[] mensajes = {
				"�qu� d�a es, @jenkins?",
				"@jenkins, la fecha por favor",
				"me dec�s la fecha @jenkins?"
		};
		for (String mensaje : mensajes) {
			Assert.assertEquals(
					"@delucas hoy es 1 de abril de 2018",
					jenkins.escuchar(mensaje)
			);
		}
	}
	
	@Test
	public void diaDeLaSemana() {
		String[] mensajes = {
				"�qu� d�a de la semana es hoy, @jenkins?"
		};
		for (String mensaje : mensajes) {
			Assert.assertEquals(
					"@delucas hoy es domingo",
					jenkins.escuchar(mensaje)
			);
		}
	}
	
	@Test
	public void diaDentroDe() {

		Assert.assertEquals("@delucas ser� el martes 3 de abril de 2018",
				jenkins.escuchar("@jenkins qu� d�a ser� dentro de 2 dias?"));

		Assert.assertEquals("@delucas ser� el viernes 1 de junio de 2018",
				jenkins.escuchar("@jenkins qu� d�a ser� dentro de 2 meses?"));

		Assert.assertEquals("@delucas ser� el mi�rcoles 1 de abril de 2020",
				jenkins.escuchar("@jenkins qu� d�a ser� dentro de 2 a�os?"));
	}

	@Test
	public void diaHace() {

		Assert.assertEquals("@delucas fue el s�bado 31 de marzo de 2018",
				jenkins.escuchar("@jenkins que dia fue ayer?"));

		Assert.assertEquals("@delucas fue el jueves 29 de marzo de 2018",
				jenkins.escuchar("@jenkins que dia fue hace 3 dias?"));

		Assert.assertEquals("@delucas fue el jueves 1 de febrero de 2018",
				jenkins.escuchar("@jenkins que dia fue hace 2 meses?"));

		Assert.assertEquals("@delucas fue el viernes 1 de abril de 2016",
				jenkins.escuchar("@jenkins que dia fue hace 2 a�os?"));
	}

	@Test
	public void tiempoDesde() {

		Assert.assertEquals("@delucas entre el 1 de abril de 2017 y el 1 de abril de 2018 pasaron 365 d�as",
				jenkins.escuchar("@jenkins cuantos dias pasaron desde el 1 de abril de 2017?"));

		Assert.assertEquals("@delucas entre el 1 de marzo de 2018 y el 1 de abril de 2018 pasaron 31 d�as",
				jenkins.escuchar("@jenkins cuantos dias pasaron desde el 1 de marzo de 2018?"));

	}

	@Test
	public void tiempoHasta() {
		Assert.assertEquals("@delucas faltan 9 dias",
				jenkins.escuchar("@jenkins cuantos dias faltan para el 10 de abril?"));
		Assert.assertEquals("@delucas faltan 365 dias",
				jenkins.escuchar("@jenkins cuantos dias faltan para el 1 de abril de 2019?"));
	}

	@Test // CALCULAR
	public void calculos() {
		assertEquals("@delucas 3", jenkins.escuchar("@jenkins cu�nto es 1 + 2"));
		
		assertEquals("@delucas 3.5", jenkins.escuchar("@jenkins cu�nto es 1.5 + 2"));

		assertEquals("@delucas 1", jenkins.escuchar("@jenkins cu�nto es 5 - 2 * 2"));

		assertEquals("@delucas 10", jenkins.escuchar("@jenkins cu�nto es el 10% de 100"));		

		assertEquals("@delucas 42", jenkins.escuchar("@jenkins cu�nto es el 17 + 5 ^ 2"));
	
	}

	@Test
	public void calculosCompuestos() {
		assertEquals("@delucas -6", jenkins.escuchar("@jenkins cu�nto es (4-8)*2 + 4 / ( 1 + 1)"));
		
	}
	
	// Leyes de la robotica
	@Test
	public void leyesRobotica() {
		
		String[] mensajes = {
				"�@jenkins cuales son las leyes de la rob�tica?",
				"me decias las leyes de la rob�tica @jenkins?",
				"@jenkins,las leyes de la robotica por favor"
		};
		
		for (String mensaje : mensajes) {
			Assert.assertEquals("1-Un robot no har� da�o a un ser humano, ni permitir� con su inacci�n que sufra da�o.\n" + 
					"2-Un robot debe cumplir las �rdenes dadas por los seres humanos, a excepci�n de aquellas que entrasen en conflicto con la primera ley.\n" + 
					"3-Un robot debe proteger su propia existencia en la medida en que esta protecci�n no entre en conflicto con la primera o con la segunda ley.", 
					jenkins.escuchar(mensaje));
			
		}
		
	}
	
	
	// Unidad de medicion
	@Test
	public void unidadesDeMedicion() {
		Assert.assertEquals(
				"@delucas 1 kilo equivale a 1000 gramos",
				jenkins.escuchar("@jenkins cu�ntos gramos son 1 kilo")
			);
		
		Assert.assertEquals(
				"@delucas 1000 gramos equivalen a 1 kilo",
				jenkins.escuchar("@jenkins cu�ntos kilos son 1000 gramos")
			);
		
		Assert.assertEquals(
				"@delucas 1000 gramos equivalen a 35.27 onzas",
				jenkins.escuchar("@jenkins cu�ntas onzas son 1000 gramos")
			);	
		
		Assert.assertEquals(
				"@delucas 1000 metros equivalen a 100000 centimetros",
				jenkins.escuchar("@jenkins cu�ntos centimetros son 1000 metros")
			);	
		
		Assert.assertEquals(
				"@delucas 1000 centimetros equivalen a 10 metros",
				jenkins.escuchar("@jenkins cu�ntos metros son 1000 centimetros")
			);	
		
		Assert.assertEquals(
				"@delucas 1000 centimetros equivalen a 393.7 pulgadas",
				jenkins.escuchar("@jenkins cu�ntas pulgadas son 1000 centimetros")
			);	
		
		
		Assert.assertEquals(
				"@delucas 1000 metros c�bicos equivalen a 1000000000 centimetros c�bicos",
				jenkins.escuchar("@jenkins cu�ntos centimetros c�bicos son 1000 metros c�bicos")
			);	
		
		Assert.assertEquals(
				"@delucas 1000 centimetros c�bicos equivalen a 0.001 metros c�bicos",
				jenkins.escuchar("@jenkins cu�ntos metros c�bicos son 1000 centimetros c�bicos")
			);	
		
		Assert.assertEquals(
				"@delucas 1000 pies c�bicos equivalen a 28.3168 metros c�bicos",
				jenkins.escuchar("@jenkins cu�ntos metros c�bicos son 1000 pies c�bicos")
			);	
		
		Assert.assertEquals(
				"@delucas 1000 segundos equivalen a 16.6667 minutos",
				jenkins.escuchar("@jenkins cu�ntos minutos son 1000 segundos")
			);	
		
		Assert.assertEquals(
				"@delucas 1333 horas equivalen a 79980 minutos",
				jenkins.escuchar("@jenkins cu�ntos minutos son 1333 horas")
			);	
	}


}
