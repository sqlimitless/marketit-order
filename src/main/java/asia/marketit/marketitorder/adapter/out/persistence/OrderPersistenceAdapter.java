package asia.marketit.marketitorder.adapter.out.persistence;

import asia.marketit.marketitorder.application.port.out.OrderCompletePort;
import asia.marketit.marketitorder.application.port.out.OrderReceptionPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderPersistenceAdapter implements OrderReceptionPort, OrderCompletePort {

    private final OrderRepository orderRepository;

    @Override
    public OrderEntity save(OrderEntity orderEntity) {
        return orderRepository.save(orderEntity);
    }

    @Override
    public OrderEntity orderStateComplete(long orderId) {
        OrderEntity orderEntity = orderRepository.findById(orderId).orElseThrow(() -> new IllegalArgumentException("orderId가 없음."));
        orderEntity.updateOrderStatus(OrderStatus.COMPLETE);
        return orderEntity;
    }
}
