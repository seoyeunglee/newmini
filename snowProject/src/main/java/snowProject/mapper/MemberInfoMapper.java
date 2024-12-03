package snowProject.mapper;

import org.apache.ibatis.annotations.Mapper;

import snowProject.domain.MemberDTO;

@Mapper
public interface MemberInfoMapper {
	public MemberDTO memberSelectOne(String memberId);
	public Integer memberUpdate(MemberDTO dto);

}
