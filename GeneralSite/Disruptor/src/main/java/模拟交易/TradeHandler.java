package 模拟交易;

import java.util.UUID;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

/**
 *
 */
public class TradeHandler implements EventHandler<Trade>, WorkHandler<Trade> {

	public void onEvent(Trade event, long arg1, boolean arg2) throws Exception {
		this.onEvent(event);
	}
	public void onEvent(Trade event){
		//具体消费逻辑
		event.setId(UUID.randomUUID().toString());
		System.out.println(event.getId());
	}
	
	
}
