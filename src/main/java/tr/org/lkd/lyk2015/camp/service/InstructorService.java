package tr.org.lkd.lyk2015.camp.service;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tr.org.lkd.lyk2015.camp.dal.CourseDao;
import tr.org.lkd.lyk2015.camp.dal.InstructorDao;
import tr.org.lkd.lyk2015.camp.model.Course;
import tr.org.lkd.lyk2015.camp.model.Instructor;

@Transactional
@Service
public class InstructorService implements Serializable {

	private static final long serialVersionUID = 1L;

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired // pooldan cekme
	protected InstructorDao instructorDao;

	@Autowired // pooldan cekme
	protected CourseDao courseDao;

	public Long create(final Instructor instructor) {

		if (instructor == null) {
			throw new RuntimeException("Model cannot be null");
		}

		return this.instructorDao.create(instructor);
	}

	public Instructor getById(final Long id) {

		if (id == null) {
			throw new RuntimeException("Id cannot be null");
		}

		return this.instructorDao.getById(id);
	}

	public Instructor update(final Instructor instructor) {

		if (instructor == null) {
			throw new RuntimeException("Model cannot be null");
		}

		return this.instructorDao.update(instructor);
	}

	public List<Instructor> getAll() {

		return this.instructorDao.getAll();
	}

	public void create(Instructor instructor, List<Long> ids) {

		List<Course> courses = this.courseDao.getByIds(ids);

		Set<Course> courseSet = new HashSet<>();
		courseSet.addAll(courses);
		instructor.setCourses(courseSet);

		// instructor.getCourses().addAll(courses);

		this.instructorDao.create(instructor);
	}

	// long id, inst in = getbyid(id)
	// hiber.initialize(inst.getcour)

	public Instructor getInstructorWithCourses(Long id) {

		Instructor instructor = this.instructorDao.getByIdWithCourses(id);
		// Hibernate.initialize(instructor.getCourses());

		return instructor;
	}

}
