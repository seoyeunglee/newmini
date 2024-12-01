package snowProject.service.goods;

import java.io.File;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import snowProject.domain.GoodsDTO;
import snowProject.mapper.GoodsMapper;

@Service
public class GoodsDeleteService {
	@Autowired
	GoodsMapper goodsMapper;
	
	public void execute(String goodsNum) {
		GoodsDTO dto = goodsMapper.selectOne(goodsNum);
		int i = goodsMapper.goodsDelete(goodsNum);
		
		if(i > 0) {
			
			URL resource = getClass().getClassLoader().getResource("static/upload");
			String fileDir = resource.getFile();
			
			File file =  new File(fileDir+"/"+dto.getGoodsMainStoreImage());
			
			if(file.exists()) file.delete();
			if(dto.getGoodsDetailImage() != null) {
				for(String fileName : dto.getGoodsDetailStoreImage().split("/")) {
					
					file =  new File(fileDir+"/"+fileName);
					
					if(file.exists()) file.delete();
				}
			}
		}
	}
}