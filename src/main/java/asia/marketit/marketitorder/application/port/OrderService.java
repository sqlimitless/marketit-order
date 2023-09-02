package asia.marketit.marketitorder.application.port;

import asia.marketit.marketitorder.adapter.out.persistence.OrderEntity;
import asia.marketit.marketitorder.adapter.out.persistence.OrderMapper;
import asia.marketit.marketitorder.application.port.in.OrderCompleteUsecase;
import asia.marketit.marketitorder.application.port.in.OrderReceptionUseCase;
import asia.marketit.marketitorder.application.port.out.OrderCompletePort;
import asia.marketit.marketitorder.application.port.out.OrderReceptionPort;
import asia.marketit.marketitorder.domain.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService implements OrderReceptionUseCase, OrderCompleteUsecase {

    private final OrderReceptionPort orderReceptionPort;
    private final OrderCompletePort orderCompletePort;

    @Override
    @Transactional
    public OrderDto orderRecprion(OrderDto order) {
        OrderEntity entity = OrderMapper.toEntity(order);
        orderReceptionPort.save(entity);
        return OrderMapper.toOrder(entity);
    }

    @Override
    @Transactional
    public OrderDto complete(long orderId) {
        OrderEntity orderEntity = orderCompletePort.orderStateComplete(orderId);
        return OrderMapper.toOrder(orderEntity);
    }
}
