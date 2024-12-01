package snowProject.service.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import snowProject.command.EmpCommand;
import snowProject.domain.EmployeeDTO;
import snowProject.mapper.EmployeeMapper;

@Service
public class EmployeeUpdateService {
	@Autowired
	EmployeeMapper employeeMapper;
	
	public void execute(EmpCommand empCommand) {
		EmployeeDTO dto = new EmployeeDTO();
		dto.setEmpAddr(empCommand.getEmpAddr());
		dto.setEmpAddrDetail(empCommand.getEmpAddrDetail());
		dto.setEmpEmail(empCommand.getEmpEmail());
		dto.setEmpHireDate(empCommand.getEmpHireDate());
		dto.setEmpJumin(empCommand.getEmpJumin());
		dto.setEmpName(empCommand.getEmpName());
		dto.setEmpPhone(empCommand.getEmpPhone());
		dto.setEmpPost(empCommand.getEmpPost());
		dto.setEmpNum(empCommand.getEmpNum());
		employeeMapper.employeeUpdate(dto);
	}

}
