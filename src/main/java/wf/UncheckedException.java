package wf;

import javax.ejb.ApplicationException;

@ApplicationException(rollback=false)
public class UncheckedException extends RuntimeException {

}
