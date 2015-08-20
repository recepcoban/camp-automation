package tr.org.lkd.lyk2015.camp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import tr.org.lkd.lyk2015.camp.model.Student;

public class StudentService extends GenericService<Student> {

	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public Long create(Student student) {
		student.setPassword(this.passwordEncoder.encode("123"));
		return super.create(student);
	}
}