package asistenteTest;


import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import baseDatos.ConsultasDB;
import baseDatos.Meme;


public class MemeTests {
	
	List<Meme> memes = ConsultasDB.levantarMeme();
	String[] lista = {"lol"};
	private ArrayList<String> nombres = new ArrayList<>();
	
	@Before
	public void setUp() {
		for(int i=0;i< memes.size();i++)
			nombres.add(memes.get(i).getNombre());
	}
	
	@Test
	public void simedevuelvelosmemespedidos() {		
		for(String m : lista) {
			Assert.assertTrue(nombres.contains(m));
		}
	}
	
	@Test
	public void quenocontengaelmemepedido() {		
		Assert.assertFalse(nombres.contains("meme invalido"));	
	}

}
