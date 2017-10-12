package wf;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
public class ContainerManagedTransactionBean {

	@PersistenceContext
	EntityManager em;

	public void insert() {
		em.persist(new Entry());
	}

	public void insertAndThrowChecked() throws CheckedException {
		em.persist(new Entry());
		throw new CheckedException();
	}

	public void insertAndThrowUnchecked() throws UncheckedException {
		em.persist(new Entry());
		throw new UncheckedException();
	}

}
