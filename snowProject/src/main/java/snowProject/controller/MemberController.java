package snowProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import snowProject.command.MemberCommand;
import snowProject.service.AutoNumService;
import snowProject.service.member.MemberListService;
import snowProject.service.member.MemeberWriteService;

@Controller
@RequestMapping("member")
public class MemberController {
	
	@Autowired
	AutoNumService autoNumService;
	@Autowired
	MemeberWriteService memberWriteService;
	@Autowired
	MemberListService memberListService;
	
	@GetMapping("welcome")
	public String welcome() {
		return "thymeleaf/memberJoin/welcome";
	}
	
	@GetMapping("memberList")
	public String list(
			@RequestParam(value="page", required=false, defaultValue="1") Integer page
			, @RequestParam(value="searchWord", required = false) String searchWord
			, Model model) {
		memberListService.execute(searchWord, page, model);
		return "thymeleaf/member/memberList";
	}
	
	
	@GetMapping("memberWrite")
	public String write(Model model) {
		//String autoNum = autoNumService.execute("mem_", "member_num", 5, "members");
		MemberCommand memberCommand = new MemberCommand();
		//memberCommand.setMemberNum(autoNum);
		model.addAttribute("memberCommand", memberCommand);
		return "thymeleaf/member/memberForm";
	}
	
	@PostMapping("memberRegist")
	public String write(@Validated MemberCommand memberCommand, BindingResult result) {
		if(result.hasErrors()) {
			return "thymeleaf/member/memberForm";
		}
		if(!memberCommand.isMemberPwEqualMemberPwCon()) {
			result.rejectValue("memberPwCon", "memberCommand.memberPwCon", "비밀번호가일치하지않습니다.");
			return "thymeleaf/member/memberForm";
		}
		memberWriteService.execute(memberCommand);
		return "redirect:welcome";
	}
	

}
