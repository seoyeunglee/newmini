package snowProject.service.goodsIpgo;

import java.sql.Timestamp;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import snowProject.command.GoodsIpgoCommand;
import snowProject.domain.GoodsIpgoDTO;
import snowProject.mapper.GoodsIpgoMapper;

@Service
public class GoodsIpgoUpdateService {
	@Autowired
	GoodsIpgoMapper goodsIpgoMapper;
	
	public void execute(GoodsIpgoCommand goodsIpgoCommand) {
		GoodsIpgoDTO dto = new GoodsIpgoDTO();
		BeanUtils.copyProperties(goodsIpgoCommand, dto);
		dto.setMadeDate(Timestamp.valueOf(goodsIpgoCommand.getMadeDate()));
		goodsIpgoMapper.goodsIpgoUpdate(dto);
	}

}
