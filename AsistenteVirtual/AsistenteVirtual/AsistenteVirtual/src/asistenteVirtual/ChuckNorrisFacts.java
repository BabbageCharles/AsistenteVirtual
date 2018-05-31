package asistenteVirtual;

public class ChuckNorrisFacts {

	public static int indice = 0;
	public static int auxiliar[];
	public static String facts[] = {
			"No existe la teor�a de la evoluci�n, tan s�lo una lista de las especies que Chuck Norris permite vivir.",
			"Chuck Norris borr� la papelera de reciclaje.",
			"Una vez una cobra mordi� a Chuck Norris en una pierna. Despu�s de cinco d�as de ag�nico dolor, la cobra muri�.",
			"El grupo sangu�neo de Chuck Norris es 5W40",
			"Chuck Noris puede ahogarte con el cable de un tel�fono inal�mbrico.",
			"Cuando Chuck Norris entra en una habitaci�n no enciende la luz, apaga la oscuridad.",
			"Chuck Norris invent� el negro. De hecho invent� todos los colores menos el rosa, que fue inventado por Tom Cruise.",
			"Los compiladores no dan warnings a Chuck Norris, Chuck se los da a ellos.",
			"A Chuck Norris no le corrigen el examen. Su examen es el modelo para corregir a los dem�s.",
			"Chuck Norris recorri� un bucle infinito. Dos veces.",
			"Chuck Norris no necesita wifi, se comunica por telepat�a.",
			"Chuck Norris comprime los datos a fuerza de meterle patadas giratorias a los 1 y los 0.",
			"Linus Torvalds no quer�a liberar el kernel Linux. Chuck Norris habl� con �l.",
			"Los programas de Chuck Norris siempre compilan a la primera.", "Chuck Norris puede dividir entre cero.",
			"Chuck Norris no hace algoritmos eficientes, el miedo les hace ir m�s r�pido.",
			"El teclado de Chuck Norris tiene dos teclas: 0 y 1", "Todos los punteros apuntan a Chuck Norris",
			"El teclado de Chuck Norris no tiene tecla Ctrl porque nadie controla a Chuck Norris",
			"Chuck Norris puede ganar al ta-te-ti en dos jugadas.",
			"Chuck Norris arroj� una granada y mat� a 50 personas. Y luego la granada explot�.",
			"Una vez se le imputaron 3 intentos de homicidio a Chuck Norris, pero los cargos fueron retirados porque Chuck Norris no �intenta� asesinar.",
			"Chuck Norris hace llorar a las cebollas.", "Chuck Norris es la raz�n por la cual Wally se esconde.",
			"Chuck Norris jug� a la ruleta rusa con un revolver completamente cargado. Y gan�.",
			"La muerte una vez tuvo una experiencia cercana a Chuck Norris.",
			"Chuck Norris duerme con una almohada bajo su arma.",
			"Las l�grimas de Chuck Norris curan el c�ncer. Es una pena que nunca haya llorado.",
			"Chuck Norris tiene un diario. Se llama el Libro Guinness de Records.",
			"Los fantasmas se sientan alrededor de las fogatas a contar historias de Chuck Norris.",
			"Chuck Norris puede tomar sopa con palitos chinos.",
			"Chuck Norris no acepta las bases y condiciones, las bases y condiciones lo aceptan a �l.",
			"Chuck Norris gan� un falta envido con tres anchos, sin ser mano.",
			"Chuck Norris sigue comprando el d�lar a 1 peso. Ning�n banco ni casa de cambio se anim� a avisarle que aument�.",
			"La cheta de Nordelta le ceba mates a Chuck Norris.",
			"Chuck Norris sabe por qu� Manuelita se fue de Pehuaj� a Par�s.",
			"Chuck Norris manda mensajes de Whatsapp desde un tel�fono de l�nea.",
			"Chuck Norris sabe qu� tiene Sancor beb� 3.", "Chuck Norris puede tocar a la vieja de Pappo.",
			"Los osos duermen con un Chuck Norris de peluche.",
			"Chuck Norris puede abrir las galletitas tirando de la bandita roja, sin problemas.",
			"Un zombie muerde a Chuck Norris y se hace humano.",
			"Chuck Norris puede limpiar el riachuelo con un balde y un cepillo de dientes.",
			"Chuck Norris puede hacer wheelies con un monociclo.", "Chuck Norris puede parsear HTML con regex.",
			"El 4 de octubre de 2011, se filtraron fotograf�as de Chuck Norris en su cuenta de iCloud. Al d�a siguiente, Steve Jobs fue encontrado muerto luego de recibir una patada giratoria en la mand�bula.",
			"Chuck Norris puede programar con objetos. En Assembler.", "Chuck Norris sigue descargando de Megaupload.",
			"No necesit�s antivirus en tu computadora, basta con poner a Chuck Norris de fondo de pantalla.",
			"Chuck Norris usa Internet Explorer. Y le anda r�pido.",
			"Chuck Norris no hizo una planilla de m�tricas ni un solo test en toda la cursada, y sin embargo se sac� 10 en Programaci�n Avanzada.", };

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
