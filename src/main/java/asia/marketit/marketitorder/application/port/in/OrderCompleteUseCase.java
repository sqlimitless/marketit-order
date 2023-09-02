package asia.marketit.marketitorder.application.port.in;

import asia.marketit.marketitorder.domain.OrderDto;

public interface OrderCompleteUseCase {

    OrderDto complete(long orderId);
}
