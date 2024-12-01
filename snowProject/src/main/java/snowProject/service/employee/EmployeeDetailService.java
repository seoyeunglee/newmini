package snowProject.service.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import snowProject.domain.EmployeeDTO;
import snowProject.mapper.EmployeeMapper;

@Service
public class EmployeeDetailService {
	@Autowired
	EmployeeMapper employeeMapper;
	
	public void execute(String empNum, Model model) {
		EmployeeDTO num = employeeMapper.employeeOneSelect(empNum);
		model.addAttribute("empCommand", num);
	}

}
