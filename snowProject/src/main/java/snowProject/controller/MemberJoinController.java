package snowProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import snowProject.command.LoginCommand;
import snowProject.command.UserCommand;
import snowProject.service.memberJoin.MemberJoinService;

@Controller
@RequestMapping("register")
public class MemberJoinController {
	@Autowired
	MemberJoinService memberJoinService;

	
	@ModelAttribute
	public UserCommand userCommand() {
		return new UserCommand();
	}
	
	@GetMapping("userLogin")
	public String loginpage(Model model) {
		model.addAttribute("loginCommand", new LoginCommand());
		return "thymeleaf/memberJoin/userLogin";
	}
	
	@RequestMapping("userAgree")
	public String userAgree() {
		return "thymeleaf/memberJoin/agree";
	}
	
	
	@GetMapping("userWrite")
	public String userWrite() {
		return "thymeleaf/memberJoin/userForm";
	}
	@PostMapping("userWrite")
	public String userWrite1(@Validated UserCommand userCommand, BindingResult result) {
		if(result.hasErrors()) {
			return "thymeleaf/memberJoin/userForm";
		}
		memberJoinService.execute(userCommand);
		return "thymeleaf/memberJoin/welcome";
	}
	
	
}
