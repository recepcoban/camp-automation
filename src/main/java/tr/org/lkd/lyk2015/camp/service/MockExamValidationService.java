package tr.org.lkd.lyk2015.camp.service;

import org.springframework.stereotype.Service;

@Service
public class MockExamValidationService implements ExamValidationService {

	@Override
	public boolean validate(Long tckn, String email) {
		if (email.equals("hello@mail.com")) {
			return false;
		}
		return true;
	}

}
