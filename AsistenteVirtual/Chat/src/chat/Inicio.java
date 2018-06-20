package chat;



import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Transaction;



public class Inicio {

//	public static boolean loguearUser(ClienteChat cliente) {
//
//	    Transaction tx = cliente.session.beginTransaction();
//
//		CriteriaQuery<Usuario> criteriaQuery = cliente.criteriaBuilder.createQuery(Usuario.class);
//		Root<Usuario> root = criteriaQuery.from(Usuario.class);
//		System.out.println(cliente.session.createQuery(criteriaQuery).getResultList().toString());
//		// de la tabla que le pase, busca si existe el nombre de usuario que
//		// ingresaron
//		criteriaQuery.select(root).where(cliente.criteriaBuilder.like(root.get("usuario"), cliente.getUser().getUsuario()),cliente.criteriaBuilder.like(root.get("password"), cliente.getUser().getPassword()));
//		//
//		
//
//		   if(!cliente.session.createQuery(criteriaQuery).getResultList().isEmpty()){
//			   try {
//				tx.commit();
//				System.out.println("El usuario " + cliente.getUser().getUsuario() + " ha iniciado sesión." + System.lineSeparator());
//			   } catch (HibernateException e) {
//	 				if (tx != null)
//	 					tx.rollback();//si hay error se descarta todo
//	 				e.printStackTrace();
//	 				
//	 				cliente.session.close();
//	 				cliente.factory.close();
//	 				System.out.println("El usuario " + cliente.getUser().getUsuario()
//	 				      + " fallo al iniciar sesión." + System.lineSeparator());
//	                return false;			
//			     }
//		   } else {
//			   System.out.println("El usuario " + cliente.getUser().getUsuario()
//	                + " ha realizado un intento fallido de inicio de sesión." + System.lineSeparator());
//	           return false;
//	         }
//		   
//			cliente.session.close();
//			cliente.factory.close();
//			return true;
//		}
	
//	public static boolean registrarUser(ClienteChat cliente) {
//
//		CriteriaQuery<Usuario> criteriaQuery = cliente.criteriaBuilder.createQuery(Usuario.class);
//		Root<Usuario> root = criteriaQuery.from(Usuario.class);// le
//																		// paso
//																		// la
//																		// entidad
//																		// que
//																		// necesito
////		System.out.println(session.createQuery(criteriaQuery).getResultList().toString());
//		// de la tabla que le pase, busca si existe el nombre de usuario que
//		// ingresaron
//		criteriaQuery.select(root).where(cliente.criteriaBuilder.equal(root.get("usuario"), cliente.getUser().getUsuario()));
////		 System.out.println("holaaaaaaASAA "+cliente.session.createQuery(criteriaQuery).getResultList().toString());
//
//		// si la consulta genera tabla vacia, es porque no existe el usuario y
//		// puede crearse
//		if (cliente.session.createQuery(criteriaQuery).getResultList().isEmpty()) {
//			Transaction tx = cliente.session.beginTransaction();
//			try {
//				cliente.session.saveOrUpdate(cliente.getUser());
//				tx.commit();
//
//			} catch (HibernateException e) {
//				if (tx != null)
//					tx.rollback();// si hay error se descarta todo
//				e.printStackTrace();
//
//				cliente.session.close();
//				cliente.factory.close();
//				System.out.println("Eror al intentar registrar el usuario " + cliente.getUser().getUsuario() + System.lineSeparator());
//				return false;
//			}
//		} else {// si la consulta devuelve un campo, es porque el usuario ya
//				// existe
//			cliente.session.close();
//			cliente.factory.close();
//			System.out.println("El usuario " + cliente.getUser().getUsuario() + " ya se encuentra en uso." + System.lineSeparator());
//			return false;
//		}
//
//		cliente.session.close();
//		cliente.factory.close();
//		System.out.println("El usuario " + cliente.getUser().getUsuario() + " se ha registrado." + System.lineSeparator());
//		return true;
//
//	}
	
}
