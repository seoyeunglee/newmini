package snowProject.service.member;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import snowProject.command.MemberCommand;
import snowProject.domain.MemberDTO;
import snowProject.mapper.MemberMapper;

@Service
public class MemeberWriteService {
	@Autowired
	MemberMapper memberMapper;
	@Autowired
	PasswordEncoder passwordEncoder;

	
	public void execute(MemberCommand memberCommand) {
		MemberDTO dto = new MemberDTO();
		
		BeanUtils.copyProperties(memberCommand, dto);
		
		if(memberCommand.getMemberPhone2() != null) {
			dto.setMemberPhone2(memberCommand.getMemberPhone2().trim());
		}
		
		String encodePw = passwordEncoder.encode(memberCommand.getMemberPw());
		dto.setMemberPw(encodePw);
		
		dto.setMemberPost(memberCommand.getMemberPost());
		memberMapper.memberInsert(dto);
		
	}

}
