package wf;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

@Singleton
@Startup
@Transactional(TxType.NOT_SUPPORTED)
public class Start {
	
	@EJB
	ContainerManagedTransactionBean bean;

	@PersistenceContext
	EntityManager em;

	@PostConstruct
	public void start() {
		bean.insert();
		list(); //1 OK
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
		list(); // 2 : Why not 3?


	}


	private void list() {
		System.out.println(em.createQuery("Select count(*) from Entry").getSingleResult());
	}
	

}
