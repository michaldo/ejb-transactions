package wf;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Entry {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;

}
