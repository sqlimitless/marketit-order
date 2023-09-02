package asia.marketit.marketitorder.application.port.out;

import asia.marketit.marketitorder.adapter.out.persistence.OrderEntity;
import org.springframework.stereotype.Service;

@Service
public interface OrderReceptionPort {

    OrderEntity save(OrderEntity order);
}
