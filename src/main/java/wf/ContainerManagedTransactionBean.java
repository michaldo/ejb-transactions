package wf;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

@Stateless
@Transactional(TxType.REQUIRES_NEW)
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
