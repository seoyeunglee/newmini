package snowProject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import snowProject.domain.GoodsDTO;
import snowProject.domain.GoodsIpgoDTO;

@Mapper
public interface GoodsIpgoMapper {
	public int goodsIpgoInsert(GoodsIpgoDTO dto);
	public List<GoodsIpgoDTO> goodsIpgoSelectList();
	public List<GoodsDTO> itemSelectList(@Param("startRow") int startRow
			, @Param("endRow") int endRow
			, @Param("searchWord") String searchWord);
	public int itemListCount(String searchWord);

}
