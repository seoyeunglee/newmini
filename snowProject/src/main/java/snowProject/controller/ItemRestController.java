package snowProject.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import snowProject.command.CartCommand;
import snowProject.service.item.CartInsertService;
import snowProject.service.item.CartQtyDownService;
import snowProject.service.item.GoodsCartDelsService;
import snowProject.service.item.GoodsWishService;

@RestController
@RequestMapping("item")
public class ItemRestController {
	@Autowired
	GoodsWishService goodsWishService;
	@Autowired
	CartInsertService cartInsertService;
	@Autowired
	CartQtyDownService cartQtyDownService;
	@Autowired
	GoodsCartDelsService goodsCartDelsService;
	
	@RequestMapping("wishItem")
	public void wishAdd(@RequestBody Map<String, Object> map, HttpSession session) {
		goodsWishService.execute(map.get("goodsNum").toString(), session);
	}
	@RequestMapping("cartAdd")
	public String cartAdd(@RequestBody CartCommand cartCommand, HttpSession session) {
		System.out.println(cartCommand.getGoodsNum());
		return cartInsertService.execute(cartCommand, session);
	}
	@GetMapping("cartQtyDown")
	public void cartQtyDown(String goodsNum, HttpSession session) {
		cartQtyDownService.execute(goodsNum, session);
	}
	@PostMapping("cartDels")
	public String cartDels(@RequestBody String goodsNums[], HttpSession session) {
		return goodsCartDelsService.execute(goodsNums, session);
	}
	
	
}
