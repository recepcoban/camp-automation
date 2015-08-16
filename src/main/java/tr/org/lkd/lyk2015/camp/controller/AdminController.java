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
import org.springframework.web.bind.annotation.RequestParam;

import tr.org.lkd.lyk2015.camp.model.Admin;
import tr.org.lkd.lyk2015.camp.service.AdminService;

@Controller
@RequestMapping("/admins")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String list(Model model) {
		List<Admin> admins = adminService.getAll();

		model.addAttribute("adminList", admins);
		return "admin/admins";
	}
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String createAdminForm(@ModelAttribute(value="admins") Admin admin) {
		return "admin/createAdmin";
	}
	
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String postAdminCreate(@ModelAttribute @Valid Admin admin,
                                  @RequestParam("passwordAgain") String passwordAgain,
                                  BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "admin/createAdmin";
        }

        if (!passwordAgain.equals(admin.getPassword())) {
            //TODO error
        }
        adminService.create(admin);

        return "redirect:/admins";

    }

	@RequestMapping(value = "", method = RequestMethod.POST)
	public String create(@ModelAttribute @Valid Admin admin, Model model, BindingResult bindingResult) {
		
		if (bindingResult.hasErrors()) {
			return "admin/createAdmin";
		}		
		adminService.create(admin);
		List<Admin> admins = adminService.getAll();
		model.addAttribute("adminList", admins);
		return "admin/admins";
	}
	

}
