package tr.org.lkd.lyk2015.camp.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Size;

import tr.org.lkd.lyk2015.camp.model.Application;
import tr.org.lkd.lyk2015.camp.model.Student;

public class ApplicationFormDto {

	@Valid
	private Application application = new Application();

	@Valid
	private Student student = new Student();

	@Size(min = 1, max = 3)
	private List<Long> preferredCourseIds;

	public ApplicationFormDto() {
		this.preferredCourseIds = new ArrayList<Long>();
		this.preferredCourseIds.add(null);
		this.preferredCourseIds.add(null);
		this.preferredCourseIds.add(null);
	}

	public Application getApplication() {
		return this.application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	public Student getStudent() {
		return this.student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public List<Long> getPreferredCourseIds() {
		return this.preferredCourseIds;
	}

	public void setPreferredCourseIds(List<Long> preferredCourseIds) {
		this.preferredCourseIds = preferredCourseIds;
	}

}
