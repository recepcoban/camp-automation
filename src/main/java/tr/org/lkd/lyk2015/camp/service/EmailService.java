package tr.org.lkd.lyk2015.camp.service;

import org.springframework.stereotype.Service;

@Service
public interface EmailService {

	public abstract boolean sendEmailConfirmation(String email, String subject, String content);

}
