package tr.org.lkd.lyk2015.camp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tr.org.lkd.lyk2015.camp.dto.ApplicationFormDto;
import tr.org.lkd.lyk2015.camp.model.Application;
import tr.org.lkd.lyk2015.camp.service.ApplicationService;
import tr.org.lkd.lyk2015.camp.service.CourseService;
import tr.org.lkd.lyk2015.camp.validation.ApplicationFormValidator;

@Controller
@RequestMapping("")
public class ApplicationController {

	@Autowired
	private CourseService courseService;

	@Autowired
	private ApplicationFormValidator applicationFormValidator;

	@Autowired
	private ApplicationService applicationService;

	@InitBinder
	protected void InitBinder(final WebDataBinder binder) {
		binder.addValidators(this.applicationFormValidator);
	}

	@RequestMapping(value = "/basvuru", method = RequestMethod.GET)
	public String formGet(@ModelAttribute("form") ApplicationFormDto applicationFormDto, Model model) {

		model.addAttribute("courses", this.courseService.getAll());
		return "applicationForm";
	}

	@RequestMapping(value = "/basvuru", method = RequestMethod.POST)
	public String formPost(@ModelAttribute("form") @Valid ApplicationFormDto applicationFormDto,
			BindingResult bindingResult, Model model) {

		if (bindingResult.hasErrors()) {
			model.addAttribute("courses", this.courseService.getAll());
			return "applicationForm";
		} else {
			this.applicationService.create(applicationFormDto);
			return "applicationSuccess";
		}

	}

	@RequestMapping(value = "/apps/validate/{uuid}", method = RequestMethod.GET)
	public String onayGet(@PathVariable("uuid") String uuid, Model model) {

		if (this.applicationService.validate(uuid)) {
			model.addAttribute("message", "Başvurunuz başarıyla onaylanmıştır.");
			return "applicationValidate";
		} else {
			model.addAttribute("message", "Böyle bir form bulunmamaktadır. Lütfen yeniden başvurun!");
			return "applicationValidate";
		}
	}

	@RequestMapping(value = "/apps", method = RequestMethod.GET)
	public String list(Model model) {
		List<Application> apps = this.applicationService.getAll();

		model.addAttribute("applicationList", apps);
		return "applications";
	}

	@RequestMapping(value = "/apps/selected/{id}", method = RequestMethod.GET)
	public String selectedApps(@ModelAttribute Application application, Model model, @PathVariable("id") Long id) {

		return "applications";
	}

}
