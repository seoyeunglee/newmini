package snowProject.service.goodsIpgo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import snowProject.mapper.GoodsIpgoMapper;

@Service
public class GoodsIpgoDeleteService {
	@Autowired
	GoodsIpgoMapper goodsIpgoMapper;
	
	public void execute(String ipgoNumgoodsNum) {
		System.out.println("ipgoNumgoodsNum : " + ipgoNumgoodsNum);
		goodsIpgoMapper.ipgoGoodsNumDelete(ipgoNumgoodsNum);
		
	}
}
