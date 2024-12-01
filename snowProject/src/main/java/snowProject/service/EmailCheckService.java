package snowProject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import snowProject.mapper.LoginMapper;
import snowProject.mapper.MemberMapper;

@Service
public class EmailCheckService {
	@Autowired
	LoginMapper loginMapper;
	@Autowired
	MemberMapper memberMapper;
	
	public Integer execute(String userEmail) {
		return loginMapper.emailCheckSelectOne(userEmail);
	}
	public Integer update(String userEmail) {
		Integer i = loginMapper.emailCheckSelectOne(userEmail);
		if(i != null) {
			i = memberMapper.memberEmailCheckUpdate(userEmail);
		}
		return i;
	}
	

}
