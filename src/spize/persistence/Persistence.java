package spize.persistence;

import java.util.Map;
import java.util.HashMap;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

/**
* This class was taken from persistence-with-jpa-examples
* 
* @author  Harald Habiger
* @version 1.0
*/

public class Persistence {

	private static EntityManager em = null;
	private static EntityManagerFactory emf = null;
	private static String persistenceUnitName = "carDb";

	private static boolean debugMode = true;

	public static EntityManager connect(String persistenceUnit) {
		if (emf == null) {
			emf = javax.persistence.Persistence.createEntityManagerFactory(persistenceUnit);

			em = emf.createEntityManager();
		}

		return em;
	}

	public static EntityManager connect(String persistenceUnit, String user, String password) {
		if (emf == null) {

			if (debugMode)
				System.out.println("Connecting as user " + user);

			Map<String, String> props = new HashMap<String, String>();

			props.put("javax.persistence.jdbc.user", user);
			props.put("javax.persistence.jdbc.password", password);

			emf = javax.persistence.Persistence.createEntityManagerFactory(persistenceUnit, props);

			em = emf.createEntityManager();
		}

		return em;
	}

	public static EntityManager getEntityManager() {
		return em;
	}

	public static EntityManagerFactory getEntityManagerFactory() {
		return emf;
	}

	public static EntityTransaction getTransaction() {
		return em.getTransaction();
	}

	public static void close() {
		if (em != null) {
			em.close();
			em = null;
		}
		if (emf != null) {
			emf.close();
			emf = null;
		}
	}

	public static void resetTable(String schema, String table) {
		em.createNativeQuery("DELETE FROM " + schema + "." + table).executeUpdate();
	}

	public static void resetSequence(String schema, String sequence) {
		em.createNativeQuery("ALTER SEQUENCE " + schema + "." + sequence + " RESTART").executeUpdate();
	}
	
	public static String getPersistenceUnitName() {
		return persistenceUnitName;
	}

}
