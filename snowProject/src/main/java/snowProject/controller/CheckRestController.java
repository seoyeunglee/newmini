package snowProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import snowProject.service.EmailCheckService;

@RestController
public class CheckRestController {
	@Autowired
	EmailCheckService emailCheckService;

	
	@PostMapping("/checkRest/userEmailCheck")
	public Integer emailCheck(String userEmail) {
		return emailCheckService.execute(userEmail);
	}
	@GetMapping("userConfirm")
	public String userConfirm(String chk) {
		Integer i = emailCheckService.update(chk);
		if(i == null) return "이메일이 존재하지 않습니다.";
		else {
			if(i == 1) {
				return "인증되었습니다.";
			}else {
				return "이미 인증되었습니다.";
			}
		}
	}
	

	

}
