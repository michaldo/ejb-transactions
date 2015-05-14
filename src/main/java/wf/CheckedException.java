package wf;

import javax.ejb.ApplicationException;

@ApplicationException(rollback=false)
public class CheckedException extends Exception {

}
