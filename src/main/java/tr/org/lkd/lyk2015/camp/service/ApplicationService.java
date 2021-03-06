package tr.org.lkd.lyk2015.camp.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tr.org.lkd.lyk2015.camp.dal.ApplicationDao;
import tr.org.lkd.lyk2015.camp.dal.CourseDao;
import tr.org.lkd.lyk2015.camp.dal.StudentDao;
import tr.org.lkd.lyk2015.camp.dto.ApplicationFormDto;
import tr.org.lkd.lyk2015.camp.model.Application;
import tr.org.lkd.lyk2015.camp.model.Course;
import tr.org.lkd.lyk2015.camp.model.Student;

@Service
@Transactional
public class ApplicationService extends GenericService<Application> {

	private static final String URL_BASE = "http://localhost:8080/CampAutomation/apps/validate/";

	@Autowired
	private CourseDao courseDao;

	@Autowired
	private StudentDao studentDao;

	@Autowired
	private ApplicationDao applicationDao;

	@Autowired
	private EmailService emailService;

	public void create(ApplicationFormDto applicationFormDto) {

		Application application = applicationFormDto.getApplication();
		Student student = applicationFormDto.getStudent();
		List<Long> courseIds = applicationFormDto.getPreferredCourseIds();

		String uuid = UUID.randomUUID().toString();
		String url = "<a href=\"" + URL_BASE + uuid + "\">" + URL_BASE + uuid + "</a>";

		this.emailService.sendEmailConfirmation(student.getName(), student.getSurname(), student.getEmail(),
				"Linux Yaz Kampi - Basvuru Onayi", url);

		application.setValidationId(uuid);

		List<Course> courses = this.courseDao.getByIds(courseIds);
		application.getPreferredCourses().clear();
		application.getPreferredCourses().addAll(courses);

		Student studentFromDb = this.studentDao.getUserByTckn(student.getTckn());

		if (studentFromDb == null) {
			this.studentDao.create(student);
			studentFromDb = student;
		}

		application.setOwner(studentFromDb);

		this.applicationDao.create(application);

	}

	public boolean validate(String validationId) {
		Application application = this.applicationDao.getByValidationId(validationId);
		if (application == null) {
			return false;
		} else {
			application.setValidated(true);
			this.applicationDao.update(application);
			return true;
		}
	}

	@Override
	public List<Application> getAll() {

		return this.applicationDao.getAll();
	}

}
