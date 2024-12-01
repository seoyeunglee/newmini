package snowProject.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import snowProject.domain.AuthInfoDTO;

@Mapper
public interface LoginMapper {
	public Integer idCheckSelectOne(@Param("userId") String userId);
	public AuthInfoDTO loginSelectOne(String userId);
	public Integer emailCheckSelectOne(@Param("userEmail") String userEmail);
}
