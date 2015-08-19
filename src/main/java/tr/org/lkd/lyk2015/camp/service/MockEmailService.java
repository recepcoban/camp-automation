package tr.org.lkd.lyk2015.camp.service;

import org.springframework.stereotype.Service;

@Service
public class MockEmailService implements EmailService {

	@Override
	public boolean sendEmailConfirmation(String email, String subject, String content) {
		if (email.equals("recep@mail.com")) {
			return false;
		}
		return true;
	}

}
