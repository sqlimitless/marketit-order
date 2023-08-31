package asia.marketit.marketitorder.application.port.out;

import asia.marketit.marketitorder.domain.OrderDto;
import org.springframework.stereotype.Service;

@Service
public interface OrderReceptionPort {

    OrderDto save(OrderDto order);
}
