package tr.org.lkd.lyk2015.camp.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractUser extends AbstractBaseModel {

	// @NotEmpty
	private String name;
	// @NotEmpty
	private String surname;
	// @NotEmpty
	private Integer birthDate;
	// @NotEmpty
	@Column(unique = true)
	private Long tckn;
	// @NotEmpty
	@Column(unique = true)
	private String email;
	// @NotEmpty
	private String password;
	// @NotEmpty
	@Column(unique = true)
	private String phone;

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return this.surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Integer getBirthDate() {
		return this.birthDate;
	}

	public void setBirthDate(Integer birthDate) {
		this.birthDate = birthDate;
	}

	public Long getTckn() {
		return this.tckn;
	}

	public void setTckn(Long tckn) {
		this.tckn = tckn;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

}
