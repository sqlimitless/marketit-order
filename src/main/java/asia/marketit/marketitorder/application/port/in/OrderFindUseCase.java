package asia.marketit.marketitorder.application.port.in;

import asia.marketit.marketitorder.domain.OrderDto;

import java.util.List;

public interface OrderFindUseCase {

    OrderDto findOrder(long orderIdx);

    List<OrderDto> findOrderByCustomer(long customerIdx);
}
