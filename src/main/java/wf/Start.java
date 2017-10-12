package wf;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
@Startup
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class Start {

	@EJB
	ContainerManagedTransactionBean bean;

	@PersistenceContext
	EntityManager em;

	@PostConstruct
	public void start() {
		bean.insert();
		list(); // 1 OK
		try {
			bean.insertAndThrowChecked();
		} catch (Exception e) {
			System.out.println("Start Checked exception catched");
		}
		list(); // 2 OK

		try {
			bean.insertAndThrowUnchecked();
		} catch (Exception e) {
			System.out.println("Start unchecked exception catched");
		}
		list(); // it now prints 3 bc none of Exceptions is marked for rollback

	}

	private void list() {
		System.out.println(em.createQuery("Select count(*) from Entry").getSingleResult());
	}

}
