package tr.org.lkd.lyk2015.camp.model;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Admin extends AbstractUser {
	@Column(unique = true, nullable = false)
	private String lkdNo;

	public String getLkdNo() {
		return this.lkdNo;
	}

	public void setLkdNo(String lkdNo) {
		this.lkdNo = lkdNo;
	}

}
