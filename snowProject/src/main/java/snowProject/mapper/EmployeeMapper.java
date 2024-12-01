package snowProject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import snowProject.domain.EmployeeDTO;
import snowProject.domain.StartEndPageDTO;

@Mapper
public interface EmployeeMapper {
	public Integer employeeInsert(EmployeeDTO dto);
	public List<EmployeeDTO> employeeAllSelect(StartEndPageDTO sepDTO);
	public int employeeCount(String searchWord);
	public EmployeeDTO employeeOneSelect(String empNum);
	public Integer employeeUpdate(EmployeeDTO dto);
	public Integer employeeDelete(String empNum);
	public String getEmpNum(String empId);
}
