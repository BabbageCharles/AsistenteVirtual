package asistenteVirtual.respuestas;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import asistenteVirtual.AsistenteEscucha;
import asistenteVirtual.Utilitarias;
import baseDatos.ConsultasDB;
import baseDatos.Deuda;


public class CalcularDeudas implements AsistenteEscucha {

	private AsistenteEscucha siguiente;
	public static String USUARIO;
	private DecimalFormat df = new DecimalFormat("#.#####");

	@Override
	public void establecerSiguiente(AsistenteEscucha siguiente) {
		this.siguiente = siguiente;

	}

	@Override
	public String escuchar(String pedido, String user) {
		String error = new String("No entiendo el mensaje");
		List<String> deudas = Arrays.asList("deuda", "deudas", "simplificar", "debo", "me debe", "gastamos", "pago",
				"pagó", "pague", "pagué");
		String b = deudas.stream().filter(x -> pedido.toLowerCase().contains(x)).findFirst().orElse(error);
		USUARIO = user;
		if (!b.equals(error))
			return estadoDeudas(pedido);
		return siguiente.escuchar(pedido, USUARIO);
	}

	private String estadoDeudas(String pedido) {

		float monto;
		String nombre[];
		nombre = Utilitarias.buscarNombre(pedido);
		monto = Utilitarias.buscarNumero(pedido);
		
		
		if(monto != (float)-1) {
			if(nombre.length == 0)
				return "Lo siento, no puedo realizar ese pedido.No has ingresado bien el nombre";
			if(pedido.contains("me debe"))
				return meDebe(nombre[0],monto);
			if(pedido.contains("le debo"))
				return leDebo(nombre[0], monto);
			if(pedido.contains("pague yo"))
				return deudaGrupalCaso2(nombre, monto);
			if(pedido.contains("gastamos"))
				return deudaGrupalCaso1(nombre, monto);
		} else {
			if(pedido.contains("cuanto"))
				if(nombre.length == 0)
					return "Lo siento, no puedo realizar ese pedido.No has ingresado bien el nombre";
				else			
					return cuantoMeDebe(nombre[0]);	
			if(pedido.contains("estado de deuda"))
				return estadoDeudas();
			if(pedido.contains("simplificar"))
				if(nombre.length == 0)
					return "Lo siento, no puedo realizar ese pedido.No has ingresado bien el nombre";
				else
					return simplificar(nombre);
		}

		return "Lo siento, no puedo realizar ese pedido";
	}

	private String meDebe(String nombre, float monto) {
		if(ConsultasDB.actualizarDeudas(nombre,monto,USUARIO)){
			return "@"+USUARIO+" anotado.";
		}		
		return "Lo siento, se produjo un error";		
	}
	
	private String leDebo(String nombre, float monto) {
		if(ConsultasDB.actualizarDeudas(USUARIO,monto,nombre)){
			return "@"+USUARIO+" anotado.";
		}		
		return "Lo siento, se produjo un error";		
	}

	@SuppressWarnings("unused")
	private String simplificar(String[] nombres) {
		List<Deuda> deudor0= ConsultasDB.levantarDeudas(nombres[0],USUARIO); 
		List<Deuda> deuda0= ConsultasDB.levantarDeudas(USUARIO,nombres[0]); 
		
		List<Deuda> deudor1= ConsultasDB.levantarDeudas(nombres[1],USUARIO);
		List<Deuda> deuda1= ConsultasDB.levantarDeudas(USUARIO,nombres[1]);
		
		if(deudor0.get(0).getDinero() ==0 && deudor1.get(0).getDinero() ==0) {
			return "@" + USUARIO + " no se pueden simplificar las deudas";			
		}else {
			if(deuda0.get(0).getDinero() >0 && deudor1.get(0).getDinero() >0) {
				float yodebo = deuda0.get(0).getDinero();
				float medeben = deudor1.get(0).getDinero();
				float resta = yodebo - medeben;
				if(resta > 0) {
					ConsultasDB.actualizarDeudas(nombres[1],0,USUARIO);
					ConsultasDB.actualizarDeudas(USUARIO,resta,nombres[0]);
				}else {
					ConsultasDB.actualizarDeudas(USUARIO,0,nombres[0]);
					ConsultasDB.actualizarDeudas(nombres[1],Math.abs(resta),USUARIO);					
				}
				
			}else {
				return "@" + USUARIO + " no se pueden simplificar las deudas";	
			}
		}
		
		return "@"+USUARIO+" bueno.";
			

	}
	
	private String cuantoMeDebe(String nombre) {
		List<Deuda> d= ConsultasDB.levantarDeudas(nombre,USUARIO);
		List<Deuda> d1= ConsultasDB.levantarDeudas(USUARIO,nombre);
		float n1 = 0;
		if(!d1.isEmpty())
			 n1= d1.get(0).getDinero();
		if(d.isEmpty()) {			
			if(n1 == 0)
				return "@"+nombre+" no te debe nada";
			else
				return "@"+nombre+" no te debe nada. Vos le debes $"+n1;
		}			
		else {
			float n= d.get(0).getDinero();
			if(n==0) {
				if(n1 == 0)
					return "@"+nombre+" no te debe nada";
				else
					return "@"+nombre+" no te debe nada. Vos le debes $"+n1;
			}
			else {
				if(n%1 ==0)
					return "@"+USUARIO+" @"+nombre+" te debe $"+(int)n;
				else
					return "@"+USUARIO+" @"+nombre+" te debe $"+n;
			}
				
		}		
		
	}
	
	private String estadoDeudas() { 
		List<Deuda> misdeudas= ConsultasDB.MisDeudas(USUARIO);
		List<Deuda> misdeudores = ConsultasDB.MisDeudores(USUARIO);
		String resultado= "@"+USUARIO;
		
		for(Deuda d: misdeudas) {
			if(misdeudas.size()==1) {
				if(d.getDinero() !=0)
					resultado+=" le debés $"+df.format(d.getDinero())+" a @"+d.getAcreedor()+".";
			}				
			else {
				if(d.getDinero() != 0)
					resultado+=" le debés $"+df.format(d.getDinero())+" a @"+d.getAcreedor()+",";
			}
				
		}
		
		if(misdeudas.size()>1) {
			resultado= resultado.substring(0, resultado.length()-1);
			resultado+=". ";
		}
		
		for(Deuda d: misdeudores) {
			if(misdeudores.size()==1) {
				if(d.getDinero() !=0 )
					resultado+="@"+d.getDeudor()+" te debe $"+df.format(d.getDinero());
			}			
			else {
				if(d.getDinero() !=0)
					resultado+="@"+d.getDeudor()+" te debe $"+df.format(d.getDinero())+",";
			}
				
		}
		
		if(misdeudores.size()>1) {
			resultado= resultado.substring(0, resultado.length()-1);
		}
		
		if(resultado.equals("@"+USUARIO))
			return "@"+USUARIO+" no tienes deudas";
		else
			return resultado;
			
	}
	
	public String deudaGrupalCaso1(String[] nombres, float monto) {
		float valor = monto/3;
		String prestamista=nombres[nombres.length-1];
		String deudor= (prestamista.equals(nombres[0])) ? nombres[1]:nombres[0];
		
		if(ConsultasDB.actualizarDeudas(USUARIO, valor, prestamista) &&
				ConsultasDB.actualizarDeudas(deudor, valor, prestamista)) {
			return "@"+USUARIO+" anotado.";
		}
		
		return "Lo siento, se produjo un error";
		
	}
	
	public String deudaGrupalCaso2(String[] nombres, float monto) {
		float valor = monto/3;
		String prestamista= USUARIO;
		
		if(ConsultasDB.actualizarDeudas(nombres[0], valor, prestamista) &&
				ConsultasDB.actualizarDeudas(nombres[1], valor, prestamista)) {
			return "@"+USUARIO+" anotado.";
		}
		
		return "Lo siento, se produjo un error";
	}

}
