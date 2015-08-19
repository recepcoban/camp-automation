package tr.org.lkd.lyk2015.camp.validation;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import tr.org.lkd.lyk2015.camp.dto.ApplicationFormDto;
import tr.org.lkd.lyk2015.camp.model.Application.WorkStatus;
import tr.org.lkd.lyk2015.camp.model.Student;
import tr.org.lkd.lyk2015.camp.service.ApplicationService;
import tr.org.lkd.lyk2015.camp.service.BlackListValidationService;
import tr.org.lkd.lyk2015.camp.service.EmailService;
import tr.org.lkd.lyk2015.camp.service.ExamValidationService;
import tr.org.lkd.lyk2015.camp.service.TcknValidationService;

@Component
public class ApplicationFormValidator implements Validator {

	@Autowired
	private TcknValidationService tcknValidatorService;

	@Autowired
	private BlackListValidationService blackListValidationService;

	@Autowired
	private ExamValidationService examValidationService;

	@Autowired
	private EmailService emailService;

	@Autowired
	private ApplicationService applicationService;

	@Override
	public boolean supports(Class<?> arg0) {
		return arg0.equals(ApplicationFormDto.class);
	}

	@Override
	public void validate(Object target, Errors error) {

		ApplicationFormDto application = (ApplicationFormDto) target;

		// workıng status
		if (application.getApplication().getWorkStatus() == WorkStatus.NOT_WORKING
				&& application.getApplication().isOfficer()) {
			error.rejectValue("workStatus", "error.notWorkingOfficer",
					"Hem calismayip hem memur nasil olabiliyorsun lanet olasi pislik");
		}

		// course selection size
		application.getPreferredCourseIds().removeAll(Collections.singleton(null));
		if (application.getPreferredCourseIds().size() == 0) {
			error.rejectValue("preferredCourseIds", "error.preferredCourseNoSelection", "En az bir kurs secmelisin");
		}

		// same course selection
		int listSize = application.getPreferredCourseIds().size();
		Set<Long> set = new HashSet<>(application.getPreferredCourseIds());
		int setSize = set.size();
		if (listSize != setSize) {
			error.rejectValue("preferredCourseIds", "error.preferredCourseSame",
					"Ayni kurstan birden fazla kere secemezsin");
		}

		// valıdate tckn from web servıce
		Student student = application.getStudent();
		boolean tcknValid = this.tcknValidatorService.validate(student.getName(), student.getSurname(),
				student.getBirthDate(), student.getTckn());
		if (!tcknValid) {
			error.rejectValue("student.tckn", "error.tcknInvalid", "TC Kimlik No Hatali");
		}

		// valıdate email
		boolean emailValid = this.blackListValidationService.validate(student.getName(), student.getName(),
				student.getEmail());
		if (!emailValid) {

			error.rejectValue("student.email", "error.emailInvalid", "Kara listedesin lanet olasi pislik");
		}

		// valıdate exam
		boolean examValid = this.examValidationService.validate(student.getTckn(), student.getEmail());
		if (!examValid) {
			error.rejectValue("student.email", "error.emailInvalid", "Sinavi gecemedin ki ne ayaksin sen");
		}

		// email confirmation
		boolean sendMail = this.emailService.sendEmailConfirmation(student.getEmail(), "E-mail Confirmation",
				"buraya tikla");
		if (!sendMail) {
			error.rejectValue("student.email", "error.checkConfirmation", "Email Gönderilemedi!");
		}

	}

}
