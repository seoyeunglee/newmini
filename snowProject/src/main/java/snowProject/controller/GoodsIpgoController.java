package snowProject.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import snowProject.command.GoodsIpgoCommand;
import snowProject.service.AutoNumService;
import snowProject.service.goodsIpgo.GoodsIpgoListService;
import snowProject.service.goodsIpgo.GoodsIpgoService;
import snowProject.service.goodsIpgo.GoodsItemListService;

@Controller
@RequestMapping("goods")
public class GoodsIpgoController {
	@Autowired
	AutoNumService autoNumService;
	@Autowired
	GoodsIpgoService goodsIpgoService;
	@Autowired
	GoodsItemListService goodsItemListService;
	@Autowired
	GoodsIpgoListService goodsIpgoListService;
	
	@GetMapping("goodsIpgoList")
	public String goodsIpgoList(Model model) {
		goodsIpgoListService.execute(model);
		return "thymeleaf/goodsIpgo/goodsIpgoList";
	}
	
	@GetMapping(value="goodsItem")
	public String goodsItem() {
		return "thymeleaf/goodsIpgo/goodsItem";
	}
	
	@PostMapping("goodsItem")
	public @ResponseBody Map<String, Object> goodsItem1(
			@RequestParam(value="page", required=false, defaultValue="1") int page
			, @RequestParam(value="searchWord", required=false) String searchWord){
		Map<String, Object> map = goodsItemListService.execute(page, searchWord);
		return map;
	}
	
	@RequestMapping(value="goodsIpgo", method=RequestMethod.GET)
	public String goodsIpgo(Model model) {
		String autoNum = autoNumService.execute("ipgo_", "IPGO_NUM", 6, "goods_ipgo");
		GoodsIpgoCommand goodsIpgoCommand = new GoodsIpgoCommand();
		goodsIpgoCommand.setGoodsIpgoNum(autoNum);
		model.addAttribute("goodsIpgoCommand", goodsIpgoCommand);
		return "thymeleaf/goodsIpgo/goodsIpgo";
	}
	@RequestMapping("ipgoRegist")
	@ResponseBody
	public String ipgoRegist(GoodsIpgoCommand goodsIpgoCommand, HttpSession session) {
		goodsIpgoService.execute(goodsIpgoCommand, session);
		return "200";
	}
	
	
	
	
	

}
