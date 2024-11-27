package snowProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("register")
public class MemberJoinController {
	
	@GetMapping("userLogin")
	public String loginpage() {
		return "thymeleaf/memberJoin/userLogin";
	}
	
	
}
