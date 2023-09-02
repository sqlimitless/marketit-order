package asia.marketit.marketitorder.application.port.out;

import asia.marketit.marketitorder.adapter.out.persistence.OrderEntity;

public interface OrderCompletePort {
    OrderEntity orderStateComplete(long orderId);
}
