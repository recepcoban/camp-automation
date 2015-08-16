package tr.org.lkd.lyk2015.camp.model;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractUser extends AbstractBaseModel {

	private String name;
	private String surname;
	private Integer birthDate;
	private Long tckn;
	private String email;
	private String password;
	private String phone;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Integer getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Integer birthDate) {
		this.birthDate = birthDate;
	}

	public Long getTckn() {
		return tckn;
	}

	public void setTckn(Long tckn) {
		this.tckn = tckn;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
