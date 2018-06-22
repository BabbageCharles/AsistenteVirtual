package asistenteVirtual.respuestas;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.KeySpec;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;


public class PGP {
   private static Cipher rsa;
   
   
   public static String desencriptar(byte [] texto,PrivateKey privateKey) throws Exception {
	   

	   rsa.init(Cipher.DECRYPT_MODE, privateKey);
     byte[] bytesDesencriptados = rsa.doFinal(texto);
     String textoDesencriptado = new String(bytesDesencriptados);
     return textoDesencriptado;
   }
   
   public static byte [] encriptar(String texto, PublicKey publicKey) throws Exception {
	   
	
     rsa = Cipher.getInstance("RSA/ECB/PKCS1Padding");
     rsa.init(Cipher.ENCRYPT_MODE, publicKey);
   byte[] encriptado = rsa.doFinal(texto.getBytes());
   
   return encriptado;  
	   
   }

   private static PublicKey cargarPublicKey(String fileName) throws Exception {
      FileInputStream fis = new FileInputStream(fileName);
      int numBtyes = fis.available();
      byte[] bytes = new byte[numBtyes];
      fis.read(bytes);
      fis.close();

      KeyFactory keyFactory = KeyFactory.getInstance("RSA");
      KeySpec keySpec = new X509EncodedKeySpec(bytes);
      PublicKey keyFromBytes = keyFactory.generatePublic(keySpec);
      return keyFromBytes;
   }

   private static PrivateKey cargarPrivateKey(String fileName) throws Exception {
      FileInputStream fis = new FileInputStream(fileName);
      int numBtyes = fis.available();
      byte[] bytes = new byte[numBtyes];
      fis.read(bytes);
      fis.close();

      KeyFactory keyFactory = KeyFactory.getInstance("RSA");
      KeySpec keySpec = new PKCS8EncodedKeySpec(bytes);
      PrivateKey keyFromBytes = keyFactory.generatePrivate(keySpec);
      return keyFromBytes;
   }

   private static void guardarKey(Key key, String fileName) throws Exception {
      byte[] publicKeyBytes = key.getEncoded();
      FileOutputStream fos = new FileOutputStream(fileName);
      fos.write(publicKeyBytes);
      fos.close();
   }
}