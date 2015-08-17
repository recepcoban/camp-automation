package tr.org.lkd.lyk2015.camp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import tr.org.lkd.lyk2015.camp.model.Instructor;
import tr.org.lkd.lyk2015.camp.service.CourseService;
import tr.org.lkd.lyk2015.camp.service.InstructorService;

@Controller
@RequestMapping("/instructors")
public class InstructorController {

	@Autowired
	private InstructorService instructorService;
	
	@Autowired
	private CourseService courseService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String list(Model model) {
		List<Instructor> instructors = instructorService.getAll();

		model.addAttribute("instructorList", instructors);
		return "instructor/instructors";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String createInstructorForm(@ModelAttribute(value="instructors") Instructor instructor, Model model) {
		
		model.addAttribute("courses", courseService.getAll());
		
		return "instructor/createInstructor";
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public String create(@ModelAttribute @Valid Instructor instructor, @RequestParam("courseIds") List<Long> ids, Model model, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return "instructor/createInstructor";
		}		
		instructorService.create(instructor);
		List<Instructor> instructors = instructorService.getAll();
		model.addAttribute("instructorList", instructors);
		return "instructor/instructors";
	}
	
	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String updateForm(@ModelAttribute Instructor instructor, Model model, @PathVariable("id") Long id, @RequestParam(value="message", required=false) String message) {
		Instructor instructorNew = instructorService.getById(id);
		model.addAttribute("instructor", instructorNew);
		model.addAttribute("message", message); // fix repost problem
		
		return "instructor/updateInstructor";
	}
	
	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	public String update(@ModelAttribute Instructor instructor, Model model) {
		
		instructorService.update(instructor);
		model.addAttribute("message", "Success!");

		return "redirect:/instructors/update/"+String.valueOf(instructor.getId()); // fix repost problem
	}
	
}
