package snowProject.service.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import snowProject.command.EmpCommand;
import snowProject.domain.EmployeeDTO;
import snowProject.mapper.EmployeeMapper;

@Service
public class EmployeeInsertService {
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	EmployeeMapper employeeMapper;
	
	public void execute(EmpCommand empCommand) {
		EmployeeDTO dto = new EmployeeDTO();
		dto.setEmpAddr(empCommand.getEmpAddr());
		dto.setEmpAddrDetail(empCommand.getEmpAddrDetail());
		dto.setEmpEmail(empCommand.getEmpEmail());
		dto.setEmpId(empCommand.getEmpId());
		dto.setEmpName(empCommand.getEmpName());
		dto.setEmpNum(empCommand.getEmpNum());
		dto.setEmpPhone(empCommand.getEmpPhone());
		dto.setEmpPost(empCommand.getEmpPost());
		dto.setEmpPw(passwordEncoder.encode(empCommand.getEmpPw()));
		dto.setEmpJumin(empCommand.getEmpJumin());
		employeeMapper.employeeInsert(dto);
		
	}

}
