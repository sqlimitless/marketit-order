package asia.marketit.marketitorder.application.port.in;

import asia.marketit.marketitorder.domain.OrderDto;

public interface OrderCompleteUsecase {

    OrderDto complete(long orderId);
}
