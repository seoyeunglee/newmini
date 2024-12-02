package snowProject.service.goodsIpgo;

import java.sql.Timestamp;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import snowProject.command.GoodsIpgoCommand;
import snowProject.domain.AuthInfoDTO;
import snowProject.domain.GoodsIpgoDTO;
import snowProject.mapper.EmployeeMapper;
import snowProject.mapper.GoodsIpgoMapper;

@Service
public class GoodsIpgoService {
	@Autowired
	EmployeeMapper employeeMapper;
	@Autowired
	GoodsIpgoMapper goodsIpgoMapper;
	
	public void execute(GoodsIpgoCommand goodsIpgoCommand, HttpSession session) {
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		String empNum = employeeMapper.getEmpNum(auth.getUserId());
		GoodsIpgoDTO dto = new GoodsIpgoDTO();
		
		BeanUtils.copyProperties(goodsIpgoCommand, dto);
		dto.setEmpNum(empNum);
		dto.setMadeDate(Timestamp.valueOf(goodsIpgoCommand.getMadeDate()));
		
		goodsIpgoMapper.goodsIpgoInsert(dto);
	}

}
