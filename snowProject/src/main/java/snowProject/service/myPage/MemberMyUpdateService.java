package snowProject.service.myPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import snowProject.command.MemberCommand;
import snowProject.domain.AuthInfoDTO;
import snowProject.domain.MemberDTO;
import snowProject.mapper.MemberInfoMapper;

@Service
public class MemberMyUpdateService {
	@Autowired
	MemberInfoMapper memberInfoMapper;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public void execute(MemberCommand memberCommand, HttpSession session) {
		MemberDTO dto = new MemberDTO();
		dto.setGender(memberCommand.getGender());
		dto.setMemberAddr(memberCommand.getMemberAddr());
		dto.setMemberAddrDetail(memberCommand.getMemberAddrDetail());
		dto.setMemberBirth(memberCommand.getMemberBirth());
		dto.setMemberEmail(memberCommand.getMemberEmail());
		dto.setMemberId(memberCommand.getMemberId());
		dto.setMemberName(memberCommand.getMemberName());
		dto.setMemberNum(memberCommand.getMemberNum());
		dto.setMemberPhone1(memberCommand.getMemberPhone1());
		dto.setMemberPhone2(memberCommand.getMemberPhone2());
		dto.setMemberPost(memberCommand.getMemberPost());
		
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		String currentPw = auth.getUserPw();
		
		System.out.println(memberCommand.getMemberPw());
		System.out.println(currentPw);
		
		if(memberCommand.getMemberPw().equals(currentPw)) {
		memberInfoMapper.memberUpdate(dto);
		}
	}

}
