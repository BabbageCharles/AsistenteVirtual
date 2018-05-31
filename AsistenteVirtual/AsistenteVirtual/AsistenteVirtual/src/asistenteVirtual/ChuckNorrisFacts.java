package asistenteVirtual;

public class ChuckNorrisFacts {

	public static int indice = 0;
	public static int auxiliar[];
	public static String facts[] = {
			"No existe la teoría de la evolución, tan sólo una lista de las especies que Chuck Norris permite vivir.",
			"Chuck Norris borró la papelera de reciclaje.",
			"Una vez una cobra mordió a Chuck Norris en una pierna. Después de cinco días de agónico dolor, la cobra murió.",
			"El grupo sanguíneo de Chuck Norris es 5W40",
			"Chuck Noris puede ahogarte con el cable de un teléfono inalámbrico.",
			"Cuando Chuck Norris entra en una habitación no enciende la luz, apaga la oscuridad.",
			"Chuck Norris inventó el negro. De hecho inventó todos los colores menos el rosa, que fue inventado por Tom Cruise.",
			"Los compiladores no dan warnings a Chuck Norris, Chuck se los da a ellos.",
			"A Chuck Norris no le corrigen el examen. Su examen es el modelo para corregir a los demás.",
			"Chuck Norris recorrió un bucle infinito. Dos veces.",
			"Chuck Norris no necesita wifi, se comunica por telepatía.",
			"Chuck Norris comprime los datos a fuerza de meterle patadas giratorias a los 1 y los 0.",
			"Linus Torvalds no quería liberar el kernel Linux. Chuck Norris habló con él.",
			"Los programas de Chuck Norris siempre compilan a la primera.", "Chuck Norris puede dividir entre cero.",
			"Chuck Norris no hace algoritmos eficientes, el miedo les hace ir más rápido.",
			"El teclado de Chuck Norris tiene dos teclas: 0 y 1", "Todos los punteros apuntan a Chuck Norris",
			"El teclado de Chuck Norris no tiene tecla Ctrl porque nadie controla a Chuck Norris",
			"Chuck Norris puede ganar al ta-te-ti en dos jugadas.",
			"Chuck Norris arrojó una granada y mató a 50 personas. Y luego la granada explotó.",
			"Una vez se le imputaron 3 intentos de homicidio a Chuck Norris, pero los cargos fueron retirados porque Chuck Norris no “intenta” asesinar.",
			"Chuck Norris hace llorar a las cebollas.", "Chuck Norris es la razón por la cual Wally se esconde.",
			"Chuck Norris jugó a la ruleta rusa con un revolver completamente cargado. Y ganó.",
			"La muerte una vez tuvo una experiencia cercana a Chuck Norris.",
			"Chuck Norris duerme con una almohada bajo su arma.",
			"Las lágrimas de Chuck Norris curan el cáncer. Es una pena que nunca haya llorado.",
			"Chuck Norris tiene un diario. Se llama el Libro Guinness de Records.",
			"Los fantasmas se sientan alrededor de las fogatas a contar historias de Chuck Norris.",
			"Chuck Norris puede tomar sopa con palitos chinos.",
			"Chuck Norris no acepta las bases y condiciones, las bases y condiciones lo aceptan a él.",
			"Chuck Norris ganó un falta envido con tres anchos, sin ser mano.",
			"Chuck Norris sigue comprando el dólar a 1 peso. Ningún banco ni casa de cambio se animó a avisarle que aumentó.",
			"La cheta de Nordelta le ceba mates a Chuck Norris.",
			"Chuck Norris sabe por qué Manuelita se fue de Pehuajó a París.",
			"Chuck Norris manda mensajes de Whatsapp desde un teléfono de línea.",
			"Chuck Norris sabe qué tiene Sancor bebé 3.", "Chuck Norris puede tocar a la vieja de Pappo.",
			"Los osos duermen con un Chuck Norris de peluche.",
			"Chuck Norris puede abrir las galletitas tirando de la bandita roja, sin problemas.",
			"Un zombie muerde a Chuck Norris y se hace humano.",
			"Chuck Norris puede limpiar el riachuelo con un balde y un cepillo de dientes.",
			"Chuck Norris puede hacer wheelies con un monociclo.", "Chuck Norris puede parsear HTML con regex.",
			"El 4 de octubre de 2011, se filtraron fotografías de Chuck Norris en su cuenta de iCloud. Al día siguiente, Steve Jobs fue encontrado muerto luego de recibir una patada giratoria en la mandíbula.",
			"Chuck Norris puede programar con objetos. En Assembler.", "Chuck Norris sigue descargando de Megaupload.",
			"No necesitás antivirus en tu computadora, basta con poner a Chuck Norris de fondo de pantalla.",
			"Chuck Norris usa Internet Explorer. Y le anda rápido.",
			"Chuck Norris no hizo una planilla de métricas ni un solo test en toda la cursada, y sin embargo se sacó 10 en Programación Avanzada.", };

	public static String darFact() {

		auxiliar = new int[51];
		int i = 0;

		if (indice == 51) {
			for (int j = 0; j < 51; j++) {
				auxiliar[i] = 0;
			}
			indice = 0;
		}

		if (indice < 20) {

			i = (int) Math.floor(Math.random() * (facts.length));
			while (auxiliar[i] == 1) // SI LA FRASE i DEL RANDOM YA FUE MOSTRADA
										// HACEMOS RANDOM HASTA OBTENER UNA
										// FRASE i QUE NO
				i = (int) Math.floor(Math.random() * (facts.length));

			auxiliar[i] = 1;
			indice++;
			return facts[i];
		} else {
			while (auxiliar[i] == 1) {
				i++;
			}
			auxiliar[i] = 1;
			indice++;
			return facts[i];
		}
	}

}
