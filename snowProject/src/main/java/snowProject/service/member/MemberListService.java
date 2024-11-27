package snowProject.service.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import snowProject.domain.MemberDTO;
import snowProject.domain.StartEndPageDTO;
import snowProject.mapper.MemberMapper;
import snowProject.service.StartEndPageService;

@Service
public class MemberListService {
	@Autowired
	MemberMapper memberMapper;
	@Autowired
	StartEndPageService startEndPageService;
	
	public void execute(String searchWord, Integer page, Model model) {
		Integer limit = 3;
		StartEndPageDTO sepDTO = startEndPageService.execute(page, limit, searchWord);
		List<MemberDTO> list = memberMapper.memberSelectList(sepDTO);
		Integer count = memberMapper.memberCount();
		startEndPageService.execute(page, limit, count, searchWord, list, model);
	}

}
