package tr.org.lkd.lyk2015.camp.service;

import org.springframework.stereotype.Service;

@Service
public interface ExamValidationService {
	public abstract boolean validate(Long tckn, String email);
}
