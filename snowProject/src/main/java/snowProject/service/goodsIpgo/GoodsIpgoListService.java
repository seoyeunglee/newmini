package snowProject.service.goodsIpgo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import snowProject.domain.GoodsIpgoDTO;
import snowProject.mapper.GoodsIpgoMapper;

@Service
public class GoodsIpgoListService {
	@Autowired
	GoodsIpgoMapper goodsIpgoMapper;
	
	public void execute(Model model) {
		List<GoodsIpgoDTO> list = goodsIpgoMapper.goodsIpgoSelectList();
		model.addAttribute("list", list);
	}


}
