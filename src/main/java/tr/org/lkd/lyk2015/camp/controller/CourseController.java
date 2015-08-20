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

import tr.org.lkd.lyk2015.camp.model.Course;
import tr.org.lkd.lyk2015.camp.service.CourseService;

@Controller
@RequestMapping("/courses")
public class CourseController {

	@Autowired
	private CourseService courseService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String list(Model model) {
		List<Course> courses = this.courseService.getAll();

		model.addAttribute("courseList", courses);
		return "course/courses";
	}

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String createCourse(@ModelAttribute(value = "courses") Course course) {
		return "course/createCourse";
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	public String create(@ModelAttribute @Valid Course course, Model model, BindingResult bindingResult) {

		if (bindingResult.hasErrors()) {
			return "course/createCourse";
		}
		this.courseService.create(course);
		List<Course> courses = this.courseService.getAll();
		model.addAttribute("courseList", courses);
		return "course/courses";
	}

	// @{/courses/__*{id}__}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
	public String updateForm(@ModelAttribute Course course, @PathVariable("id") Long id, Model model,
			@RequestParam(value = "message", required = false) String message) {
		Course courseNew = this.courseService.getById(id);
		model.addAttribute("course", courseNew);
		model.addAttribute("message", message); // fix repost problem

		return "course/updateCourse";
	}

	@RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
	public String update(@ModelAttribute Course course, Model model) {

		this.courseService.update(course);
		model.addAttribute("message", "Success!");

		return "redirect:/courses/update/" + String.valueOf(course.getId()); // fix
																				// repost
																				// problem
	}

}
