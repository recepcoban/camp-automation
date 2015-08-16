package tr.org.lkd.lyk2015.camp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/test")
public class TestController {

	@RequestMapping("")
	public String home(Model model){
		model.addAttribute("message", "Welcome message!");
		return "test";
	}
}
