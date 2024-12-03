package snowProject.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import snowProject.domain.GoodsDTO;
import snowProject.domain.GoodsIpgoDTO;
import snowProject.domain.GoodsIpgoGoodsNameDTO;

@Mapper
public interface GoodsIpgoMapper {
	public int goodsIpgoInsert(GoodsIpgoDTO dto);
	public List<GoodsIpgoDTO> goodsIpgoSelectList();
	public List<GoodsDTO> itemSelectList(
			@Param("startRow") int startRow
			, @Param("endRow") int endRow
			, @Param("searchWord") String searchWord);
	public int itemListCount(String searchWord);
	public GoodsIpgoGoodsNameDTO ipgoGoodsSelectOne(
			@Param(value="ipgoNum")String ipgoNum
			, @Param(value="goodsNum") String goodsNum);
	public void goodsIpgoUpdate(GoodsIpgoDTO dto);
	public void ipgoGoodsNumDelete(String ipgoNumgoodsNum);
}
