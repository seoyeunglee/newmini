package snowProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import snowProject.command.MemberCommand;
import snowProject.service.AutoNumService;
import snowProject.service.member.MemberDeleteService;
import snowProject.service.member.MemberDetailService;
import snowProject.service.member.MemberListService;
import snowProject.service.member.MemberUpdateService;
import snowProject.service.member.MemberWriteService;

@Controller
@RequestMapping("member")
public class MemberController {
	
	@Autowired
	AutoNumService autoNumService;
	@Autowired
	MemberWriteService memberWriteService;
	@Autowired
	MemberListService memberListService;
	@Autowired
	MemberDetailService memberDetailService;
	@Autowired
	MemberUpdateService memberUpdateService;
	@Autowired
	MemberDeleteService memberDeleteService;
	
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
	
	@GetMapping("memberDetail/{memberNum}")
	public String memberDetail(@PathVariable("memberNum")String memberNum, Model model) {
		memberDetailService.execute(model, memberNum);
		return "thymeleaf/member/memberInfo";
	}
	
	@GetMapping("memberUpdate")
	public String memberUpdate(String memberNum, Model model) {
		memberDetailService.execute(model, memberNum);
		return "thymeleaf/member/memberModify";
	}
	
	@PostMapping("memberUpdate")
	public String memberUpdate(@Validated MemberCommand memberCommand, BindingResult result) {
		if(result.hasErrors()) {
			return "thymeleaf/member/memberModify";
		}
		memberUpdateService.execute(memberCommand);
		return "redirect:memberDetail/"+memberCommand.getMemberNum();
	}
	
	@GetMapping("memberDelete/{memberNum}")
	public String memberDelete(@PathVariable("memberNum") String memberNum) {
		memberDeleteService.execute(memberNum);
		return "redirect:../memberList";
	}
	
	

}
