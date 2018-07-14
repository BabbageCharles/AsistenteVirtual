package asistenteTest;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import org.junit.Assert;
import org.junit.Test;
import asistenteVirtual.respuestas.PGP;

public class TestPGP {	

	@Test
	public void test() throws Exception {
		
		 KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
	      KeyPair keyPair = keyPairGenerator.generateKeyPair();
	      PublicKey publicKey = keyPair.getPublic();
	      PrivateKey privateKey = keyPair.getPrivate();
	      
		Assert.assertEquals("Esto es un test 123456789", PGP.desencriptar((PGP.encriptar("Esto es un test 123456789",publicKey)),privateKey));
	}

	
}
