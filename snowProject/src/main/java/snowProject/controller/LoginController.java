package snowProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import snowProject.command.LoginCommand;
import snowProject.service.IdCheckService;
import snowProject.service.login.UserLoginService;

@Controller
@RequestMapping("login")
public class LoginController {
	@Autowired
	IdCheckService idCheckService;
	@Autowired
	UserLoginService userLoginService;
	
	@PostMapping("userIdCheck")
	public @ResponseBody Integer userIdCheck(String userId) {
		return idCheckService.execute(userId);
	}
	
	@PostMapping("login")
	public String login(@Validated LoginCommand loginCommand
			,BindingResult result
			,HttpSession session
			,HttpServletResponse response) {
		userLoginService.execute(loginCommand, result, session, response);
		if(result.hasErrors()) {
			return "thymeleaf/index";
		}
		return "redirect:/";
	}

}
