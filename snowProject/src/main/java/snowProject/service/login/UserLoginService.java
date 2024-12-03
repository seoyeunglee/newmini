package snowProject.service.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import snowProject.command.LoginCommand;
import snowProject.domain.AuthInfoDTO;
import snowProject.mapper.LoginMapper;

@Service
public class UserLoginService {
	@Autowired
	LoginMapper loginMapper;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public void execute(LoginCommand loginCommand, BindingResult result
			, HttpSession session, HttpServletResponse response) {
		//
		AuthInfoDTO auth = loginMapper.loginSelectOne(loginCommand.getUserId());
		if(auth != null) {
			if(passwordEncoder.matches(loginCommand.getUserPw(), auth.getUserPw())) {
				System.out.println("비밀번호가 일치합니다.");
				session.setAttribute("auth", auth);
				System.out.println(auth.getUserId());
				System.out.println(((AuthInfoDTO)session.getAttribute("auth")).getUserId());
			}else {
				System.out.println("아이디가존재합니다.");
				result.rejectValue("userPw", "loginCommand.userPw", "비밀번호가 틀렸습ㄴ디ㅏ.");
				System.out.println("비밀번호가 일치하지않는다.");
			}
		}else {
			result.rejectValue("userId", "loginCommand.userId", "아이디가 존재하지 않습니다.");
			System.out.println("아이디가 존재하지 않습니다.");
		}
	}
}
