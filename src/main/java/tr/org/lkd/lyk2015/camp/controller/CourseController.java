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
import tr.org.lkd.lyk2015.camp.model.Course;
import tr.org.lkd.lyk2015.camp.service.CourseService;

@Controller
@RequestMapping("/courses")
public class CourseController {

	@Autowired
	private CourseService courseService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String list(Model model) {
		List<Course> courses = courseService.getAll();

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
		courseService.create(course);
		List<Course> courses = courseService.getAll();
		model.addAttribute("courseList", courses);
		return "course/courses";
	}

}
