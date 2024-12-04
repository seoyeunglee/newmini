package snowProject.mapper;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ItemMapper {
	public Integer wishCountSelectOne(Map<String, String> map);
}
