package tr.org.lkd.lyk2015.camp.service;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tr.org.lkd.lyk2015.camp.dal.AdminDao;
import tr.org.lkd.lyk2015.camp.model.Admin;

@Transactional
@Service
public class AdminService implements Serializable {

	private static final long serialVersionUID = 1L;

	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired // pooldan cekme
	protected AdminDao adminDao;

	public Long create(final Admin admin) {

		if (admin == null) {
			throw new RuntimeException("Model cannot be null");
		}

		return adminDao.create(admin);
	}

	public Admin getById(final Long id) {

		if (id == null) {
			throw new RuntimeException("Id cannot be null");
		}

		return adminDao.getById(id);
	}

	public Admin update(final Admin admin) {

		if (admin == null) {
			throw new RuntimeException("Model cannot be null");
		}

		return adminDao.update(admin);
	}

	public List<Admin> getAll() {

		return adminDao.getAll();
	}
	
}
