package asia.marketit.marketitorder.adapter.out.persistence;

import asia.marketit.marketitorder.application.port.out.OrderReceptionPort;
import asia.marketit.marketitorder.domain.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderPersistenceAdapter implements OrderReceptionPort {

    private final OrderRepository orderRepository;

    @Override
    public OrderDto save(OrderDto order) {
        OrderEntity entity = OrderMapper.toEntity(order);
        OrderEntity orderEntity = orderRepository.save(entity);
//        orderEntity.setOrderItems(order.getOrderItems().stream().map(OrderMapper::toOrderItemEntity).toList());
        return OrderMapper.toOrder(orderEntity);
    }
}
