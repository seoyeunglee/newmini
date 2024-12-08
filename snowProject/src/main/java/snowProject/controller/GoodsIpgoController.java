package snowProject.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import snowProject.command.GoodsIpgoCommand;
import snowProject.domain.AuthInfoDTO;
import snowProject.domain.GoodsIpgoGoodsNameDTO;
import snowProject.service.AutoNumService;
import snowProject.service.goodsIpgo.GoodsIpgoDeleteService;
import snowProject.service.goodsIpgo.GoodsIpgoDetailService;
import snowProject.service.goodsIpgo.GoodsIpgoListService;
import snowProject.service.goodsIpgo.GoodsIpgoService;
import snowProject.service.goodsIpgo.GoodsIpgoUpdateService;
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
	@Autowired
	GoodsIpgoDetailService goodsIpgoDetailService;
	@Autowired
	GoodsIpgoUpdateService goodsIpgoUpdateService;
	@Autowired
	GoodsIpgoDeleteService goodsIpgoDeleteService;
	
	
	@GetMapping("goodsIpgoList")
	public String goodsIpgoList(Model model, HttpSession session) {
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
	public String goodsIpgo(Model model, HttpSession session) {
		String autoNum = autoNumService.execute("ipgo_", "IPGO_NUM", 6, "goods_ipgo");
		GoodsIpgoCommand goodsIpgoCommand = new GoodsIpgoCommand();
		goodsIpgoCommand.setGoodsIpgoNum(autoNum);
		model.addAttribute("goodsIpgoCommand", goodsIpgoCommand);
		return "thymeleaf/goodsIpgo/goodsIpgo";
	}
	
	@RequestMapping(value="ipgoRegist", method=RequestMethod.POST)
	@ResponseBody
	public String ipgoRegist(GoodsIpgoCommand goodsIpgoCommand, HttpSession session) {
		System.out.println("session.getAttribute(auth) : " + ((AuthInfoDTO)session.getAttribute("auth")).getUserId());
		goodsIpgoService.execute(goodsIpgoCommand, session);
		return "200";
	}
	
	@GetMapping("goodsIpgoDetail")
	public String detailView(@ModelAttribute(value = "ipgoNum") String ipgoNum
			, @ModelAttribute(value = "goodsNum") String goodsNum) {
		return "thymeleaf/goodsIpgo/goodsIpgoDetail";
	}
	
	@PostMapping("goodsIpgoDetail")
	@ResponseBody
	public GoodsIpgoGoodsNameDTO detail(String ipgoNum, String goodsNum, Model model) {
		GoodsIpgoGoodsNameDTO dto = goodsIpgoDetailService.execute(ipgoNum, goodsNum, model);
		return dto;
	}
	
	@RequestMapping(value="goodsIpgoUpdate", method=RequestMethod.GET)
	public String goodsIpgoUpdate(String ipgoNum, String goodsNum, Model model) {
		goodsIpgoDetailService.execute(ipgoNum, goodsNum, model);
		return "thymeleaf/goodsIpgo/goodsIpgoUpdate";
	}
	
	@PostMapping("goodsIpgoModify")
	public String goodsIpgoModify(GoodsIpgoCommand goodsIpgoCommand) {
		goodsIpgoUpdateService.execute(goodsIpgoCommand);
		return "redirect:goodsIpgoDetail?ipgoNum="+goodsIpgoCommand.getGoodsIpgoNum()
											+"&goodsNum="+goodsIpgoCommand.getGoodsNum();
	}
	
	@RequestMapping("goodsIpgoDelete")
	public String goodsIpgoDelete(String num) {
		goodsIpgoDeleteService.execute(num);
		return "redirect:goodsIpgoList";
	}

	

}
