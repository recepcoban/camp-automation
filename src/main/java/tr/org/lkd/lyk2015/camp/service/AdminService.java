package tr.org.lkd.lyk2015.camp.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import tr.org.lkd.lyk2015.camp.dal.AdminDao;
import tr.org.lkd.lyk2015.camp.model.Admin;

@Service
@Transactional
public class AdminService extends GenericService<Admin> {

	@Autowired
	private AdminDao adminDao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Long create(Admin admin) {
		admin.setPassword(this.passwordEncoder.encode(admin.getPassword()));
		return super.create(admin);
	}

}
