package snowProject.domain;

import java.sql.Timestamp;
import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Alias("goodsIpgo")
@AllArgsConstructor
@NoArgsConstructor
public class GoodsIpgoDTO {
	String goodsIpgoNum;
	String goodsNum;
	Integer ipgoQty;
	Date ipgoDate;
	Timestamp madeDate;
	Integer ipgoPrice;
	String empNum;
}
