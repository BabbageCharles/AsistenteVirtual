package asistenteVirtual.respuestas;

import javax.crypto.Cipher;

import java.nio.ByteBuffer;

import java.security.GeneralSecurityException;

import java.security.KeyFactory;

import java.security.KeyPair;

import java.security.KeyPairGenerator;

import java.security.NoSuchAlgorithmException;

import java.security.PrivateKey;

import java.security.PublicKey;

import java.security.SecureRandom;

import java.security.spec.InvalidKeySpecException;

import java.security.spec.X509EncodedKeySpec;

public class PGP {

	/**
	 * 
	 * The message is created like so:
	 * 
	 * - GENERAR UNA KEYPAIR RANDOM
	 * 
	 * -ENCRIPTAR EL MENSAJE CON CLAVE PRIVADA DESDE LA KEYPAIR GENERADA
	 * 
	 * - ENCRIPTAR LA CLAVE PUBLICA CON LA CLAVE GENERADA
	 *
	 * @param message2 MENSAJE A ENCRIPTAR
	 * 
	 * 
	 * @param key LLAVE PARA ENCRIPTAR
	 * 
	 * 
	 * @return MENSAJE ENCRIPTADO
	 * 
	 * 
	 */

	public static String encrypt(String message2, PublicKey key) throws GeneralSecurityException {

		byte[] message = message2.getBytes();
		KeyPair pair = generateKeyPair();

		PrivateKey privateKey = pair.getPrivate();
//		System.out.println(privateKey.getEncoded().length);
		byte[] aux = privateKey.getEncoded();
//		System.out.println(aux.length);

		byte[] aux1 = new byte[100];
		byte[] aux2 = new byte[100];
		byte[] aux3 = new byte[100];
		byte[] aux4 = new byte[100];
		byte[] aux5 = new byte[100];
		byte[] aux6 = new byte[100];
		byte[] aux7 = new byte[100];

		for (int i = 0; i < aux.length; i++) {
			if (i < 100) {
				aux1[i] = aux[i];

			} else if (i < 200) {
				aux2[i - 100] = aux[i];

			} else if (i < 300) {
				aux3[i - 200] = aux[i];

			} else if (i < 400) {
				aux4[i - 300] = aux[i];

			} else if (i < 500) {
				aux5[i - 400] = aux[i];

			} else if (i < 600) {
				aux6[i - 500] = aux[i];

			} else if (i < aux.length) {
				aux7[i - 600] = aux[i];

			}
		}

		Cipher cipher = Cipher.getInstance("RSA");

		cipher.init(Cipher.ENCRYPT_MODE, privateKey);

		byte[] encryptedMessage = cipher.doFinal(message);

//		System.out.println("mensaje encriptado" + encryptedMessage.toString());

		cipher.init(Cipher.ENCRYPT_MODE, key);

		// REALIZO ESTO PORQUE LA LLAVE ES MUY GRANDE

		byte[] encryptedPublicKey1 = cipher.doFinal(aux1);
		byte[] encryptedPublicKey7 = cipher.doFinal(aux7);
		byte[] encryptedPublicKey2 = cipher.doFinal(aux2);
		byte[] encryptedPublicKey3 = cipher.doFinal(aux3);
		byte[] encryptedPublicKey4 = cipher.doFinal(aux4);
		byte[] encryptedPublicKey5 = cipher.doFinal(aux5);
		byte[] encryptedPublicKey6 = cipher.doFinal(aux6);

		String auxencrypt = encryptedPublicKey1.toString() + encryptedPublicKey2.toString()
				+ encryptedPublicKey3.toString() + encryptedPublicKey4.toString() + encryptedPublicKey5.toString()
				+ encryptedPublicKey6.toString() + encryptedPublicKey7.toString();
		// System.out.println("llave encriptada"+auxencrypt);
		// byte [] encryptedPublicKey = auxencrypt.getBytes();
		// System.out.println(encryptedPublicKey.toString());
		// byte[] encryptedPublicKey = cipher.doFinal(pair.getPublic().getEncoded());
		// byte[] encryptedPublicKey;
		// String ab="12345678";
		// encryptedPublicKey= ab.getBytes();

		// String buffer = ""+encryptedPublicKey.length +
		// ByteBuffer buffer = ByteBuffer.allocate((encryptedPublicKey.length +
		// encryptedMessage.length) + 4);

		// System.out.println(encryptedPublicKey.length);
		// System.out.println(encryptedMessage.length);

		// buffer.putInt(encryptedPublicKey.length);

		// buffer.put(encryptedPublicKey);

		// buffer.put(encryptedMessage);

		String bufferstring = "" + auxencrypt.length() + auxencrypt + encryptedMessage;

		// return buffer.array().toString();
//		System.out.println(bufferstring);
		return bufferstring;

	}

	/**
	 * 
	 * LEER LA CLAVE ENCRIPTADA
	 * 
	 * DESENCRIPTAR LA CLAVE PUBLICA CON LA CLAVE PRIVADA
	 * 
	 * LEER EL MENSAJE ENCRIPTADO
	 * 
	 * USAR LA CLAVE PUBLICA DESENCRIPTADA PARA DESENCRIPTAR EL MENSAJE ENCRIPTADO
	 * 
	 * @param message2
	 *            MENSAJE ENCRIPTADO
	 * 
	 * 
	 * @param key
	 *            LLAVE PRIVADA
	 * 
	 * 
	 * @return MENSAJE DESENCRIPTADO
	 * 
	 */

	public static String decrypt(String message2, PrivateKey key) throws GeneralSecurityException {

		byte[] message = message2.getBytes();

//		System.out.println(message.length);
//		System.out.println(message.toString());

		ByteBuffer buffer = ByteBuffer.allocate(message.length);
//		System.out.println("capacidad" + buffer.capacity());
		buffer = ByteBuffer.wrap(message);
//		System.out.println("DESPUES DE WRAP" + buffer);
//		System.out.println("tamañoa  esperar" + buffer.getInt(0));
		// int keyLength = 89;
		int keyLenght = buffer.getInt();

//		System.out.println(keyLenght);
		byte[] encyptedPublicKey = new byte[keyLenght];

		// SE ROMPE ACA

		buffer.get(encyptedPublicKey);
//		System.out.println(buffer);
		// encyptedPublicKey= message.clone();

		Cipher cipher = Cipher.getInstance("RSA");

		cipher.init(Cipher.DECRYPT_MODE, key);

		byte[] encodedPublicKey = cipher.doFinal(encyptedPublicKey);

		PublicKey publicKey = getPublicKey(encodedPublicKey);

		cipher.init(Cipher.DECRYPT_MODE, publicKey);

		byte[] encryptedMessage = new byte[buffer.remaining()];
		// byte[] encryptedMessage =
		// buffer.get(encryptedMessage);

		return cipher.doFinal(encryptedMessage).toString();

	}

	protected static PublicKey getPublicKey(byte[] encodedKey)
			throws NoSuchAlgorithmException, InvalidKeySpecException {

		KeyFactory factory = KeyFactory.getInstance("RSA");
		KeyPair pair = generateKeyPair();

		X509EncodedKeySpec encodedKeySpec = new X509EncodedKeySpec(encodedKey);

		return factory.generatePublic(new X509EncodedKeySpec(pair.getPublic().getEncoded()));

	}

	protected static KeyPair generateKeyPair() throws NoSuchAlgorithmException {

		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");

		keyPairGenerator.initialize(1024, SecureRandom.getInstance("SHA1PRNG"));

		return keyPairGenerator.generateKeyPair();

	}

	public static String porPartes(byte[] bytes) {
		char temp[] = new char[bytes.length * 2], val;

		for (int i = 0; i < bytes.length; i++) {
			val = (char) (((bytes[i] & 0xf0) >> 4) & 0x0f);
			temp[i * 2] = (char) (val > 9 ? val + 'A' - 10 : val + '0');

			val = (char) (bytes[i] & 0x0f);
			temp[i * 2 + 1] = (char) (val > 9 ? val + 'A' - 10 : val + '0');
		}
		return new String(temp);
	}

}
