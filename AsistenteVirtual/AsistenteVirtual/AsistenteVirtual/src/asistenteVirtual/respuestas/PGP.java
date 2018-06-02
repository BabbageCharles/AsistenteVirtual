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

	private static X509EncodedKeySpec encodedKeySpec;

	/**
	 * The message is created like so: - Generates a random KeyPair - Encrypt the
	 * message with the private key from the generated key pair - Encrypt the
	 * generated public key with given public key
	 *
	 * @param message
	 *            The message to encrypt
	 * @param key
	 *            The key to encrypt with
	 * @return The encrypted message
	 * @throws GeneralSecurityException
	 */

	public static ByteBuffer encrypt(String message2, PublicKey key) throws GeneralSecurityException {

		byte[] message = message2.getBytes();
		KeyPair pair = generateKeyPair();

		PrivateKey privateKey = pair.getPrivate();
		System.out.println(privateKey.getEncoded().length);
		byte[] aux = privateKey.getEncoded();
		System.out.println(aux.length);

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

		cipher.init(Cipher.ENCRYPT_MODE, key);

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

		byte[] encryptedPublicKey = auxencrypt.getBytes();

		// byte[] encryptedPublicKey = cipher.doFinal(pair.getPublic().getEncoded());
		// byte[] encryptedPublicKey;
		// String ab="12345678";
		// encryptedPublicKey= ab.getBytes();

		ByteBuffer buffer = ByteBuffer.allocate((encryptedPublicKey.length + encryptedMessage.length) + 4);

		buffer.putInt(encryptedPublicKey.length);

		buffer.put(encryptedPublicKey);

		buffer.put(encryptedMessage);

		return buffer;

	}

	/**
	 * The message is decrypted like so: - Read the encrypted public key - Decrypt
	 * the public key with the private key - Read the encrypted message - Use the
	 * decrypted public key to decrypt the encrypted message
	 * 
	 * @param message
	 *            The encrypted message
	 * @param key
	 *            The private key
	 * @return The decrypted message
	 * @throws GeneralSecurityException
	 */

	public static String decrypt(ByteBuffer buffer, PrivateKey key) throws GeneralSecurityException {

		byte[] encyptedPublicKey = new byte[1];

		buffer.flip();
		buffer.get(encyptedPublicKey);

		Cipher cipher = Cipher.getInstance("RSA");

		cipher.init(Cipher.DECRYPT_MODE, key);

		byte[] encodedPublicKey = cipher.doFinal(encyptedPublicKey);

		PublicKey publicKey = getPublicKey(encodedPublicKey);

		cipher.init(Cipher.DECRYPT_MODE, publicKey);

		byte[] encryptedMessage = new byte[buffer.remaining()];

		buffer.get(encryptedMessage);

		return cipher.doFinal(encryptedMessage).toString();

	}

	protected static PublicKey getPublicKey(byte[] encodedKey)
			throws NoSuchAlgorithmException, InvalidKeySpecException {

		KeyFactory factory = KeyFactory.getInstance("RSA");
		KeyPair pair = generateKeyPair();

		setEncodedKeySpec(new X509EncodedKeySpec(encodedKey));

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

	public static X509EncodedKeySpec getEncodedKeySpec() {
		return encodedKeySpec;
	}

	public static void setEncodedKeySpec(X509EncodedKeySpec encodedKeySpec) {
		PGP.encodedKeySpec = encodedKeySpec;
	}

}