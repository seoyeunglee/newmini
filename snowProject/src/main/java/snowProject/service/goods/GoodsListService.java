package snowProject.service.goods;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import snowProject.domain.GoodsDTO;
import snowProject.domain.StartEndPageDTO;
import snowProject.mapper.GoodsMapper;
import snowProject.service.StartEndPageService;

@Service
public class GoodsListService {
	@Autowired
	GoodsMapper goodsMapper;
	@Autowired
	StartEndPageService startEndPageService;
	String searchWord;
	public void execute(String searchWord , Model model, int page) {
		
		int limit = 3;
		StartEndPageDTO sepDTO = startEndPageService.execute(page,limit, searchWord);
		
		List<GoodsDTO> list = goodsMapper.allSelect(sepDTO);
		int count = goodsMapper.goodsCount(searchWord);
		
		startEndPageService.execute(page,limit, count, searchWord,list, model);
	}

}
