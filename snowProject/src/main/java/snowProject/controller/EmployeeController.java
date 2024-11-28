package snowProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import snowProject.command.EmpCommand;
import snowProject.service.AutoNumService;
import snowProject.service.employee.EmployeeInsertService;
import snowProject.service.employee.EmployeeListService;

@Controller
@RequestMapping("employee")
public class EmployeeController {
	@Autowired
	AutoNumService autoNumService;
	@Autowired
	EmployeeInsertService employeeInsertService;
	@Autowired
	EmployeeListService employeeListService;
	
	
	@RequestMapping(value="employeeList", method=RequestMethod.GET)
	public String empList(
			@RequestParam(value="page", required=false, defaultValue="1") int page
			,@RequestParam(value="searchWord", required=false) String searchWord
			,Model model) {
		employeeListService.execute(searchWord, page, model);
		return "thymeleaf/employee/employeeList";
	}
	
	@GetMapping("empRegist")
	public String form(Model model) {
		String autoNum = autoNumService.execute("emp_", "emp_num", 5, "employees");
		EmpCommand empCommand = new EmpCommand();
		empCommand.setEmpNum(autoNum);
		model.addAttribute("empCommand", empCommand);
		return "thymeleaf/employee/employeeForm";
	}
	
	@RequestMapping(value="empRegist", method=RequestMethod.POST)
	public String form(@Validated EmpCommand empCommand, BindingResult result, Model model) {
		if(result.hasErrors()) {
			return "thymeleaf/employee/employeeForm";
		}
		if(!empCommand.isEmpPwEqualsEmpPwCon()) {
			System.out.println("비밀번호 확인이 다릅니다.");
			return "thymeleaf/employee/employeeForm";
		}
		employeeInsertService.execute(empCommand);
		return "redirect:employeeList";
	}
	
	

}
