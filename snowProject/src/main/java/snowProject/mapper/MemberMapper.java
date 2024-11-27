package snowProject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import snowProject.domain.MemberDTO;
import snowProject.domain.StartEndPageDTO;

@Mapper
public interface MemberMapper {
	public void memberInsert(MemberDTO dto);
	public List<MemberDTO> memberSelectList(StartEndPageDTO sepDTO);
	public Integer memberCount();
	
}
