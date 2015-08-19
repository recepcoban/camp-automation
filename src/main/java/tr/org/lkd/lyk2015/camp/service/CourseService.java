package tr.org.lkd.lyk2015.camp.service;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tr.org.lkd.lyk2015.camp.dal.CourseDao;
import tr.org.lkd.lyk2015.camp.model.Course;

@Transactional
@Service
public class CourseService implements Serializable {

	private static final long serialVersionUID = 1L;

	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired // pooldan cekme
	protected CourseDao courseDao;

	public Long create(final Course course) {

		if (course == null) {
			throw new RuntimeException("Model cannot be null");
		}

		return courseDao.create(course);
	}

	public Course getById(final Long id) {

		if (id == null) {
			throw new RuntimeException("Id cannot be null");
		}

		return courseDao.getById(id);
	}

	public Course update(final Course course) {

		if (course == null) {
			throw new RuntimeException("Model cannot be null");
		}

		return courseDao.update(course);
	}

	public List<Course> getAll() {

		return courseDao.getAll();
	}

	public Object getAllActive() {
		// TODO Auto-generated method stub
		return null;
	}

}
