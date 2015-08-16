package tr.org.lkd.lyk2015.camp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tr.org.lkd.lyk2015.camp.model.Instructor;
import tr.org.lkd.lyk2015.camp.service.InstructorService;

@Controller
@RequestMapping("/instructors")
public class InstructorController {

	@Autowired
	private InstructorService instructorService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String list(Model model) {
		List<Instructor> instructors = instructorService.getAll();

		model.addAttribute("instructorList", instructors);
		return "instructor/instructors";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String createInstructorForm(@ModelAttribute(value="instructors") Instructor instructor) {
		return "instructor/createInstructor";
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public String create(@ModelAttribute @Valid Instructor instructor, Model model, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return "instructor/createInstructor";
		}		
		instructorService.create(instructor);
		List<Instructor> instructors = instructorService.getAll();
		model.addAttribute("instructorList", instructors);
		return "instructor/instructors";
	}
	
}
