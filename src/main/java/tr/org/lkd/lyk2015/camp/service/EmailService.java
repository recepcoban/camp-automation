package tr.org.lkd.lyk2015.camp.service;

public interface EmailService {

	// public abstract yazmasak da default olarak oyledir
	public abstract void sendEmailConfirmation(String name, String surname, String email, String subject,
			String content);

}
