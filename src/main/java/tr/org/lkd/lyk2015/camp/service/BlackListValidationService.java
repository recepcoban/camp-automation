package tr.org.lkd.lyk2015.camp.service;

public interface BlackListValidationService {
	public abstract boolean validate(String name, String surname, String email);
}
