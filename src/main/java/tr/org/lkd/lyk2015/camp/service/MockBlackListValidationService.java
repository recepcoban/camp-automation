package tr.org.lkd.lyk2015.camp.service;

import org.springframework.stereotype.Service;

@Service
public class MockBlackListValidationService implements BlackListValidationService {
	@Override
	public boolean validate(String name, String surname, String email) {

		if (email.equals("deneme@hotmail.com")) {
			return false;
		}
		return true;

	}
}
