package snowProject.service.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import jakarta.servlet.http.HttpSession;
import snowProject.domain.AuthInfoDTO;
import snowProject.domain.GoodsStockDTO;
import snowProject.mapper.GoodsStockMapper;

@Service
public class GoodsDetailViewService {
	@Autowired
	GoodsStockMapper goodsStockMapper;
	
	public void execute(String goodsNum, Model model, HttpSession session) {
		GoodsStockDTO dto = goodsStockMapper.goodsStockSelectOne(goodsNum);
		goodsStockMapper.goodsVisitCountUpdate(goodsNum);
		model.addAttribute("dto", dto);
		
		AuthInfoDTO auth = (AuthInfoDTO)session.getAttribute("auth");
		if(auth != null) {
			System.out.println(auth);
		}
	}

}
