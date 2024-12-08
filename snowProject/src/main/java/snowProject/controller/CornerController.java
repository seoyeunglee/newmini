package snowProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import snowProject.service.item.GoodsDetailViewService;

@Controller
@RequestMapping("item")
public class CornerController {
	@Autowired
	GoodsDetailViewService goodsDetailViewService;

	
	@GetMapping("detailView/{goodsNum}")
	public String goodsInfo(
			@PathVariable("goodsNum") String goodsNum, Model model, HttpSession session) {
		goodsDetailViewService.execute(goodsNum, model, session);
		return "thymeleaf/item/detailView";
	}
	
	

}
