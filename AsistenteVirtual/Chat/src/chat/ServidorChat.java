
package chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.swing.DefaultListModel;

import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class ServidorChat {

	ServerSocket servidor = null;
	Socket socket = null;
	MensajesChat mensajes;
	int puerto = 1234;
	int maximoConexiones = 10;

	// hibernate
	public static Configuration config = new Configuration().configure("cfg.xml");

	public static SessionFactory factory = config.buildSessionFactory();;
	public static Session session = factory.openSession();

	// utilizo criteria para las consultas
	public static CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
	//

	public static void main(String[] args) {
		ServidorChat sc = new ServidorChat();

	}

	public ServidorChat() {
		super();
		this.servidor = null;
		this.socket = null;
		this.puerto = 1234;
		this.maximoConexiones = 10; // Maximo de conexiones simultaneas
		this.mensajes = new MensajesChat();
		try {
			// Se crea el serverSocket
			servidor = new ServerSocket(puerto, maximoConexiones);

			// Bucle infinito para esperar conexiones
			while (true) {
				System.out.println("Servidor a la espera de conexiones.");
				socket = servidor.accept();
				System.out.println("Cliente con la IP " + socket.getInetAddress().getHostName() + " conectado.");

				ConexionCliente cc = new ConexionCliente(socket, mensajes);
				cc.start();

			}
		} catch (IOException ex) {
			System.out.println("Error: " + ex.getMessage());
		} finally {
			try {
				socket.close();
				servidor.close();
			} catch (IOException ex) {
				System.out.println("Error al cerrar el servidor: " + ex.getMessage());
			}
			session.close();
			factory.close();
		}
	}

	public static boolean loguearUser(ClienteChat cliente) {

		// Transaction tx = session.beginTransaction();
		CriteriaQuery<Usuario> criteriaQuery = criteriaBuilder.createQuery(Usuario.class);
		Root<Usuario> root = criteriaQuery.from(Usuario.class);
		System.out.println(session.createQuery(criteriaQuery).getResultList().toString());
		// de la tabla que le pase, busca si existe el nombre de usuario que
		// ingresaron
		criteriaQuery.select(root).where(criteriaBuilder.like(root.get("usuario"), cliente.getUser().getUsuario()),
				criteriaBuilder.like(root.get("password"), cliente.getUser().getPassword()));
		//

		Transaction tx = session.beginTransaction();

		if (!session.createQuery(criteriaQuery).getResultList().isEmpty()) {
			try {
				tx.commit();
				System.out.println("El usuario " + cliente.getUser().getUsuario() + " ha iniciado sesión."
						+ System.lineSeparator());
			} catch (HibernateException e) {
				if (tx != null)
					tx.rollback();// si hay error se descarta todo
				e.printStackTrace();

				session.close();
				factory.close();
				System.out.println("El usuario " + cliente.getUser().getUsuario() + " fallo al iniciar sesión."
						+ System.lineSeparator());
				return false;
			}
		} else {
			System.out.println("El usuario " + cliente.getUser().getUsuario()
					+ " ha realizado un intento fallido de inicio de sesión." + System.lineSeparator());
			return false;
		}

		// session.close();
		// factory.close();
		return true;
	}

	public static boolean registrarUser(ClienteChat cliente) {

		CriteriaQuery<Usuario> criteriaQuery = criteriaBuilder.createQuery(Usuario.class);
		Root<Usuario> root = criteriaQuery.from(Usuario.class);// le
																// paso
																// la
																// entidad
																// que
																// necesito
		// System.out.println(session.createQuery(criteriaQuery).getResultList().toString());
		// de la tabla que le pase, busca si existe el nombre de usuario que
		// ingresaron
		criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("usuario"), cliente.getUser().getUsuario()));
		// System.out.println("holaaaaaaASAA
		// "+cliente.session.createQuery(criteriaQuery).getResultList().toString());

		// si la consulta genera tabla vacia, es porque no existe el usuario y
		// puede crearse
		if (session.createQuery(criteriaQuery).getResultList().isEmpty()) {
			Transaction tx = session.beginTransaction();
			try {
				session.saveOrUpdate(cliente.getUser());
				tx.commit();

			} catch (HibernateException e) {
				if (tx != null)
					tx.rollback();// si hay error se descarta todo
				e.printStackTrace();

				session.close();
				factory.close();
				System.out.println("Eror al intentar registrar el usuario " + cliente.getUser().getUsuario()
						+ System.lineSeparator());
				return false;
			}
		} else {// si la consulta devuelve un campo, es porque el usuario ya
				// existe
			session.close();
			factory.close();
			System.out.println("El usuario " + cliente.getUser().getUsuario() + " ya se encuentra en uso."
					+ System.lineSeparator());
			return false;
		}

		// session.close();
		// factory.close();
		System.out.println(
				"El usuario " + cliente.getUser().getUsuario() + " se ha registrado." + System.lineSeparator());
		return true;

	}

	public static List<Sala> levantarSalas() {

		CriteriaQuery<Sala> criteriaQuery = criteriaBuilder.createQuery(Sala.class);
		Root<Sala> root = criteriaQuery.from(Sala.class);

		System.out.println(session.createQuery(criteriaQuery).getResultList());

		return session.createQuery(criteriaQuery).getResultList();
	}

	public static boolean agregarSala(String text) {
		CriteriaQuery<Sala> criteriaQuery = criteriaBuilder.createQuery(Sala.class);
		Root<Sala> root = criteriaQuery.from(Sala.class);

		Sala s = new Sala();
		s.setNombre(text);
		s.setTipo("Publica");// VER COMO ELEGIR TIPO DE SALA
		criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("nombre"), text));

		if (session.createQuery(criteriaQuery).getResultList().isEmpty()) {
			Transaction tx = session.beginTransaction();
			try {
				session.saveOrUpdate(s);
				tx.commit();

			} catch (HibernateException e) {
				if (tx != null)
					tx.rollback();// si hay error se descarta todo
				e.printStackTrace();

				session.close();
				factory.close();
				System.out.println("Eror al intentar crear la sala: " + text + System.lineSeparator());
				return false;
			}
		} else {// si la consulta devuelve un campo, es porque el usuario ya
				// existe
			// session.close();
			// factory.close();
			System.out.println("La sala " + text + " ya se encuentra en uso." + System.lineSeparator());
			return false;
		}

		System.out.println("La sala " + text + " ha sido creada." + System.lineSeparator());
		return true;
	}

	// ver
	public static List<Meme> levantarMeme(String nombre) {

		/*
		 * esta seria la consulta correcta select Imagen from Meme where Nombre
		 * = nombre
		 */

		CriteriaQuery<Meme> criteriaQuery = criteriaBuilder.createQuery(Meme.class);
		Root<Meme> root = criteriaQuery.from(Meme.class);
		criteriaQuery.select(root);
		// criteriaQuery.where(criteriaBuilder.equal(root.get("nombre"),
		// nombre));

		if (!session.createQuery(criteriaQuery).getResultList().isEmpty()) { // aca
																				// esta
																				// el
																				// error
																				// tira
																				// que
																				// no
																				// puede
																				// ejecutar
																				// la
																				// query
			return session.createQuery(criteriaQuery).getResultList();
		}

		return null;

	}

	public static boolean actualizarDeudas(String nombre, float monto, String user) {

		CriteriaQuery<Deuda> criteriaQuery = criteriaBuilder.createQuery(Deuda.class);
		Root<Deuda> root = criteriaQuery.from(Deuda.class);
		//criteriaQuery.select(root).where(criteriaBuilder.like(root.get("acreedor"), user),
			//	criteriaBuilder.like(root.get("deudor"), nombre));// ACREEDOR
		Criteria cr1 =
		session.createCriteria(Deuda.class).add(Restrictions.eq("acreedor", user)).add(Restrictions.eq("deudor",nombre));
		
		List<Deuda> deudas1 = cr1.list();
		
		if (deudas1.isEmpty()) {//session.createQuery(criteriaQuery).getResultList().isEmpty()) {

			Deuda deuda = new Deuda();
			deuda.setAcreedor(user);
			deuda.setDeudor(nombre);
			deuda.setDinero(monto);

			Deuda deuda2 = new Deuda();
			deuda2.setAcreedor(nombre);
			deuda2.setDeudor(user);
			deuda2.setDinero(0);
			
			Transaction tx = session.beginTransaction();
			try {
				session.saveOrUpdate(deuda);
				session.saveOrUpdate(deuda2);
				tx.commit();
				return true;

			} catch (HibernateException e) {
				if (tx != null)
					tx.rollback();// si hay error se descarta todo
				e.printStackTrace();

				session.close();
				factory.close();
				System.out.println("Eror al intentar registrar deuda " + System.lineSeparator());
				return false;
			}
		} else {
			Deuda deuda = new Deuda();
			Deuda deuda2 = new Deuda();
			float nuevomonto = monto + deudas1.get(0).getDinero();//session.createQuery(criteriaQuery).getResultList().get(0).getDinero();
			float aux,aux2;
			Criteria cr2 =
					session.createCriteria(Deuda.class).add(Restrictions.eq("acreedor", nombre)).add(Restrictions.eq("deudor",user));
			/*criteriaQuery.select(root).where(criteriaBuilder.like(root.get("acreedor"), nombre),
					criteriaBuilder.like(root.get("deudor"), user)); // DEUDOR*/
			List<Deuda> deudas2 = cr2.list();
			
			if (!deudas2.isEmpty()) {//!session.createQuery(criteriaQuery).getResultList().isEmpty()) { //crear otra session???
				aux = deudas2.get(0).getDinero();//session.createQuery(criteriaQuery).getResultList().get(0).getDinero();
				if(aux==0){
					deuda.setAcreedor(user);
					deuda.setDeudor(nombre);
					deuda.setDinero(nuevomonto);
					
					deuda2.setAcreedor(nombre);
					deuda2.setDeudor(user);
					deuda2.setDinero(0);
				} else {
					aux2=nuevomonto-aux;
					if(aux2>0){
						deuda.setAcreedor(user);
						deuda.setDeudor(nombre);
						deuda.setDinero(aux2);
						
						deuda2.setAcreedor(nombre);
						deuda2.setDeudor(user);
						deuda2.setDinero(0);
					} else {
						deuda.setAcreedor(user);
						deuda.setDeudor(nombre);
						deuda.setDinero(0);
						
						deuda2.setAcreedor(nombre);
						deuda2.setDeudor(user);
						deuda2.setDinero(Math.abs(aux2));
					}
				}
				
				Transaction tx = session.beginTransaction();
				try {
					session.clear();
					session.saveOrUpdate(deuda);
					session.saveOrUpdate(deuda2);
					tx.commit();
					return true;

				} catch (HibernateException e) {
					if (tx != null)
						tx.rollback();// si hay error se descarta todo
					e.printStackTrace();

					session.close();
					factory.close();
					System.out.println("Eror al intentar registrar deuda " + System.lineSeparator());
					return false;
				}
					
			}

		}

		// session.close();
		// factory.close();
		System.out.println(System.lineSeparator());
		return true;

//		return false;
	}
	
	public static List<Deuda> levantarDeudas(String nombre, String user){
		
		CriteriaQuery<Deuda> criteriaQuery = criteriaBuilder.createQuery(Deuda.class);
		Criteria cr1 =
				session.createCriteria(Deuda.class).add(Restrictions.eq("acreedor", user)).add(Restrictions.eq("deudor",nombre));
		
		return cr1.list();
	}
	
	public static List<Deuda> MisDeudas(String user){
		
		CriteriaQuery<Deuda> criteriaQuery = criteriaBuilder.createQuery(Deuda.class);
		Criteria cr1 =
				session.createCriteria(Deuda.class).add(Restrictions.eq("deudor", user));
		
		return cr1.list();
	}
	
	public static List<Deuda> MisDeudores(String user){
		
		CriteriaQuery<Deuda> criteriaQuery = criteriaBuilder.createQuery(Deuda.class);
		Criteria cr1 =
				session.createCriteria(Deuda.class).add(Restrictions.eq("acreedor",user));
		
		return cr1.list();
	}
	
	public static boolean SimplificarDeudas(String[] nombre,String user) {
		CriteriaQuery<Deuda> criteriaQuery = criteriaBuilder.createQuery(Deuda.class);
		Criteria crA =
				session.createCriteria(Deuda.class).add(Restrictions.eq("acreedor", user)).add(Restrictions.eq("deudor",nombre));
		List<Deuda> deudas = crA.list();
		
		Criteria crD =
				session.createCriteria(Deuda.class).add(Restrictions.eq("acreedor",nombre)).add(Restrictions.eq("deudor",user));
		List<Deuda> deudas1 = crD.list();
		
		Deuda deuda = new Deuda();
		Deuda deuda2 = new Deuda();
		
		
		/* TERMINAR DE VER
		if(nombre.length == 1) {
			deuda.setAcreedor(user);
			deuda.setDeudor(nombre[0]);
			deuda.setDinero(0);
			
			Transaction tx = session.beginTransaction();
			try {
				session.saveOrUpdate(deuda);				
				tx.commit();
				return true;

			} catch (HibernateException e) {
				if (tx != null)
					tx.rollback();// si hay error se descarta todo
				e.printStackTrace();
				session.close();
				factory.close();
				System.out.println("Eror al intentar registrar deuda " + System.lineSeparator());
				return false;
			}
		}else {
			
		}*/
		
		System.out.println(System.lineSeparator());
		return true;

	}
	

}