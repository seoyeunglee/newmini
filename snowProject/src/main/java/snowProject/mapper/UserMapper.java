package snowProject.mapper;

import org.apache.ibatis.annotations.Mapper;

import snowProject.domain.MemberDTO;

@Mapper
public interface UserMapper {
	public Integer userInsert(MemberDTO dto);

}
