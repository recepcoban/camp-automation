package tr.org.lkd.lyk2015.camp.service;

import org.springframework.stereotype.Service;

@Service
public interface BlackListValidationService {
	public abstract boolean validate(String name, String surname, String email);
}
