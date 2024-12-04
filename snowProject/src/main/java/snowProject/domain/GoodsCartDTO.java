package snowProject.domain;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data
@Alias("cartGoods")
public class GoodsCartDTO {
	GoodsDTO goodsDTO;     
	CartDTO cartDTO;       
}