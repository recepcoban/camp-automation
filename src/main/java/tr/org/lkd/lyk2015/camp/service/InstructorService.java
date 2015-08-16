package tr.org.lkd.lyk2015.camp.service;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tr.org.lkd.lyk2015.camp.dal.InstructorDao;
import tr.org.lkd.lyk2015.camp.model.Instructor;

@Transactional
@Service
public class InstructorService implements Serializable {

	private static final long serialVersionUID = 1L;

	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired // pooldan cekme
	protected InstructorDao instructorDao;

	public Long create(final Instructor instructor) {

		if (instructor == null) {
			throw new RuntimeException("Model cannot be null");
		}

		return instructorDao.create(instructor);
	}

	public Instructor getById(final Long id) {

		if (id == null) {
			throw new RuntimeException("Id cannot be null");
		}

		return instructorDao.getById(id);
	}

	public Instructor update(final Instructor instructor) {

		if (instructor == null) {
			throw new RuntimeException("Model cannot be null");
		}

		return instructorDao.update(instructor);
	}

	public List<Instructor> getAll() {

		return instructorDao.getAll();
	}

}
