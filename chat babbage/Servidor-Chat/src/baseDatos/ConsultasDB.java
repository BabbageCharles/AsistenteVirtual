
package baseDatos;

import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;

import paqueteEnvios.PaqueteSala;
import paqueteEnvios.PaqueteUsuario;
import servidor.Servidor;

public class ConsultasDB {

	public static Configuration config = new Configuration().configure("cfg.xml");
	public static SessionFactory factory = config.buildSessionFactory();;
	public static Session session = factory.openSession();
	public static CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

	public ConsultasDB(String configuracion) {
		config = new Configuration().configure(configuracion);
		factory = config.buildSessionFactory();
		session = factory.openSession();
		criteriaBuilder = session.getCriteriaBuilder();
	}

	public static boolean loguearUser(PaqueteUsuario cliente) {

		CriteriaQuery<Usuario> criteriaQuery = criteriaBuilder.createQuery(Usuario.class);
		Root<Usuario> root = criteriaQuery.from(Usuario.class);

		criteriaQuery.select(root).where(criteriaBuilder.like(root.get("usuario"), cliente.getUsername()),
				criteriaBuilder.like(root.get("password"), cliente.getPassword()));

		if (!session.createQuery(criteriaQuery).getResultList().isEmpty()) {
			Transaction tx = session.beginTransaction();
			try {
				tx.commit();

			} catch (HibernateException e) {
				if (tx != null)
					tx.rollback();
				e.printStackTrace();

				return false;
			}
		} else {

			return false;
		}

		return true;
	}

	public static boolean registrarUser(PaqueteUsuario cliente) {

		CriteriaQuery<Usuario> criteriaQuery = criteriaBuilder.createQuery(Usuario.class);
		Root<Usuario> root = criteriaQuery.from(Usuario.class);
		criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("usuario"), cliente.getUsername()));
		Usuario user = new Usuario();
		user.setUsuario(cliente.getUsername());
		user.setPassword(cliente.getPassword());

		if (session.createQuery(criteriaQuery).getResultList().isEmpty()) {
			Transaction tx = session.beginTransaction();
			try {
				session.saveOrUpdate(user);
				tx.commit();

			} catch (HibernateException e) {
				if (tx != null)
					tx.rollback();
				e.printStackTrace();
				return false;
			}
		} else {
			return false;
		}

		return true;

	}

	public static void levantarSalas() {

		CriteriaQuery<Sala> criteriaQuery = criteriaBuilder.createQuery(Sala.class);
		Root<Sala> root = criteriaQuery.from(Sala.class);
		List<Sala> salas = session.createQuery(criteriaQuery).getResultList();

		if (!salas.isEmpty()) {
			for (int i = 0; i < salas.size(); i++) {
				Servidor.getNombresSalasDisponibles().add(salas.get(i).getNombre());
				Servidor.getSalas().put(salas.get(i).getNombre(), new PaqueteSala(salas.get(i).getNombre(), "-"));
			}
		}

	}

	public static void levantarSalasPrivadas(String usuario) {

		CriteriaQuery<SalaPrivada> criteriaQuery = criteriaBuilder.createQuery(SalaPrivada.class);
		Root<SalaPrivada> root = criteriaQuery.from(SalaPrivada.class);
		criteriaQuery.select(root).where(criteriaBuilder.like(root.get("miembro"), usuario));
		List<SalaPrivada> salas = session.createQuery(criteriaQuery).getResultList();

		if (!salas.isEmpty()) {
			for (int i = 0; i < salas.size(); i++) {
				if (!Servidor.getSalasPrivadasNombresDisponibles().contains(salas.get(i).getNombre())) {
					Servidor.getSalasPrivadasNombresDisponibles().add(salas.get(i).getNombre()+" (Privada)");// VER
					Servidor.getSalas().put(salas.get(i).getNombre(), new PaqueteSala(salas.get(i).getNombre(), "-"));
				}
			}
		}

	}

	public static boolean agregarSala(String text) {
		CriteriaQuery<Sala> criteriaQuery = criteriaBuilder.createQuery(Sala.class);
		Root<Sala> root = criteriaQuery.from(Sala.class);

		Sala s = new Sala();
		s.setNombre(text);
		s.setTipo("Publica");
		criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("nombre"), text));

		if (session.createQuery(criteriaQuery).getResultList().isEmpty()) {
			Transaction tx = session.beginTransaction();
			try {
				session.saveOrUpdate(s);
				tx.commit();

			} catch (HibernateException e) {
				if (tx != null)
					tx.rollback();
				e.printStackTrace();

				session.close();
				factory.close();
				return false;
			}
		} else {

			return false;
		}
		return true;
	}

	public static List<Meme> levantarMeme() {

		CriteriaQuery<Meme> criteriaQuery = criteriaBuilder.createQuery(Meme.class);
		Root<Meme> root = criteriaQuery.from(Meme.class);
		criteriaQuery.select(root);

		if (!session.createQuery(criteriaQuery).getResultList().isEmpty()) {
			return session.createQuery(criteriaQuery).getResultList();
		}

		return null;

	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	public static boolean actualizarDeudas(String nombre, float monto, String user) {

		// CriteriaQuery<Deuda> criteriaQuery =
		// criteriaBuilder.createQuery(Deuda.class);
		// Root<Deuda> root = criteriaQuery.from(Deuda.class);
		// criteriaQuery.select(root).where(criteriaBuilder.like(root.get("acreedor"),
		// user),
		// criteriaBuilder.like(root.get("deudor"), nombre));// ACREEDOR
		Criteria cr1 = session.createCriteria(Deuda.class).add(Restrictions.eq("acreedor", user))
				.add(Restrictions.eq("deudor", nombre));

		List<Deuda> deudas1 = cr1.list();

		if (deudas1.isEmpty()) {// session.createQuery(criteriaQuery).getResultList().isEmpty())
								// {

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
					tx.rollback();
				e.printStackTrace();

				session.close();
				factory.close();
				return false;
			}
		} else {
			Deuda deuda = new Deuda();
			Deuda deuda2 = new Deuda();
			float nuevomonto = monto + deudas1.get(0).getDinero();
			float aux, aux2;
			Criteria cr2 = session.createCriteria(Deuda.class).add(Restrictions.eq("acreedor", nombre))
					.add(Restrictions.eq("deudor", user));

			List<Deuda> deudas2 = cr2.list();

			if (!deudas2.isEmpty()) {
				aux = deudas2.get(0).getDinero();
				if (aux == 0) {
					deuda.setAcreedor(user);
					deuda.setDeudor(nombre);
					deuda.setDinero(nuevomonto);

					deuda2.setAcreedor(nombre);
					deuda2.setDeudor(user);
					deuda2.setDinero(0);
				} else {
					aux2 = nuevomonto - aux;
					if (aux2 > 0) {
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
						tx.rollback();
					e.printStackTrace();

					session.close();
					factory.close();

					return false;
				}

			}

		}
		return true;

	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	public static List<Deuda> levantarDeudas(String nombre, String user) {

		// CriteriaQuery<Deuda> criteriaQuery =
		// criteriaBuilder.createQuery(Deuda.class);
		Criteria cr1 = session.createCriteria(Deuda.class).add(Restrictions.eq("acreedor", user))
				.add(Restrictions.eq("deudor", nombre));

		return cr1.list();
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	public static List<Deuda> MisDeudas(String user) {

		// CriteriaQuery<Deuda> criteriaQuery =
		// criteriaBuilder.createQuery(Deuda.class);
		Criteria cr1 = session.createCriteria(Deuda.class).add(Restrictions.eq("deudor", user));

		return cr1.list();
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	public static List<Deuda> MisDeudores(String user) {

		// CriteriaQuery<Deuda> criteriaQuery =
		// criteriaBuilder.createQuery(Deuda.class);
		Criteria cr1 = session.createCriteria(Deuda.class).add(Restrictions.eq("acreedor", user));

		return cr1.list();
	}

	public static boolean crearEvento(String evento, String usuario) {
		Evento event = new Evento();
		event.setEvento(evento);
		event.setUsuario(usuario);
		Transaction tx = session.beginTransaction();

		try {

			session.saveOrUpdate(event);
			tx.commit();

			return true;
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();

			return false;
		}

	}

	public static List<Evento> levantarEventos(String usuario) {

		CriteriaQuery<Evento> criteriaQuery = criteriaBuilder.createQuery(Evento.class);
		Root<Evento> root = criteriaQuery.from(Evento.class);
		criteriaQuery.select(root);
		criteriaQuery.where(criteriaBuilder.equal(root.get("usuario"), usuario));

		if (!session.createQuery(criteriaQuery).getResultList().isEmpty()) {
			return session.createQuery(criteriaQuery).getResultList();
		}

		return null;

	}

	public static boolean guardarLinkRss(String link, String usuario) {
		RssBD rss = new RssBD();
		rss.setLink(link);
		rss.setUsuario(usuario);
		CriteriaQuery<RssBD> criteriaQuery = criteriaBuilder.createQuery(RssBD.class);
		Root<RssBD> root = criteriaQuery.from(RssBD.class);
		criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("link"), link));
		if (session.createQuery(criteriaQuery).getResultList().isEmpty()) {
			Transaction tx = session.beginTransaction();
			try {
				session.saveOrUpdate(rss);
				tx.commit();
			} catch (HibernateException e) {
				if (tx != null)
					tx.rollback();
				e.printStackTrace();
				session.close();
				factory.close();
				return false;
			}
		}
		return true;
	}

	public static List<RssBD> levantarRss(String usuario) {

		CriteriaQuery<RssBD> criteriaQuery = criteriaBuilder.createQuery(RssBD.class);
		Root<RssBD> root = criteriaQuery.from(RssBD.class);
		criteriaQuery.select(root);
		criteriaQuery.where(criteriaBuilder.equal(root.get("usuario"), usuario));

		if (!session.createQuery(criteriaQuery).getResultList().isEmpty()) {
			return session.createQuery(criteriaQuery).getResultList();
		}

		return null;

	}
	
public static void limpiarDeudas() {
		
		Transaction tx = session.beginTransaction();
		try {

			//
			CriteriaQuery<Deuda> criteriaQuery = criteriaBuilder.createQuery(Deuda.class);
			Root<Deuda> root = criteriaQuery.from(Deuda.class);
			List<Deuda> deudas = session.createQuery(criteriaQuery).getResultList();
			
			if(!deudas.isEmpty()){
				for(Deuda d:deudas){
					session.remove(d);
				}
			}
			tx.commit();
			//
	
		} catch (Exception e) {
			tx.rollback();
		
		}
}

public static void limpiarEventos() {
	
	Transaction tx = session.beginTransaction();
	try {

		//
		CriteriaQuery<Evento> criteriaQuery = criteriaBuilder.createQuery(Evento.class);
		Root<Evento> root = criteriaQuery.from(Evento.class);
		List<Evento> eventos = session.createQuery(criteriaQuery).getResultList();
		
		if(!eventos.isEmpty()){
			for(Evento e:eventos){
				session.remove(e);
			}
		}
		tx.commit();
		//

	} catch (Exception ex) {
		tx.rollback();
	
	}
}

public static void limpiarRss() {
	
	Transaction tx = session.beginTransaction();
	try {

		//
		CriteriaQuery<RssBD> criteriaQuery = criteriaBuilder.createQuery(RssBD.class);
		Root<RssBD> root = criteriaQuery.from(RssBD.class);
		List<RssBD> rss = session.createQuery(criteriaQuery).getResultList();
		
		if(!rss.isEmpty()){
			for(RssBD r:rss){
				session.remove(r);
			}
		}
		tx.commit();
		//

	} catch (Exception ex) {
		tx.rollback();
	
	}
}

}