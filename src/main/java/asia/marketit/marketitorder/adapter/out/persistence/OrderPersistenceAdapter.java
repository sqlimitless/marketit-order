package asia.marketit.marketitorder.adapter.out.persistence;

import asia.marketit.marketitorder.application.port.out.OrderCompletePort;
import asia.marketit.marketitorder.application.port.out.OrderFindPort;
import asia.marketit.marketitorder.application.port.out.OrderReceptionPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OrderPersistenceAdapter implements OrderReceptionPort, OrderCompletePort, OrderFindPort {

    private final OrderRepository orderRepository;

    @Override
    public OrderEntity save(OrderEntity orderEntity) {
        return orderRepository.save(orderEntity);
    }

    @Override
    public OrderEntity orderStateComplete(long orderIdx) {
        OrderEntity orderEntity = orderRepository.findById(orderIdx).orElseThrow(() -> new IllegalArgumentException("orderIdx가 없음."));
        orderEntity.updateOrderStatus(OrderStatus.COMPLETE);
        return orderEntity;
    }

    @Override
    public OrderEntity findByIdx(long orderIdx) {
        return orderRepository.findById(orderIdx).orElseThrow(() -> new IllegalArgumentException("orderIdx가 없음."));
    }

    @Override
    public List<OrderEntity> findByCustomerIdx(long customerIdx) {
        return orderRepository.findByCustomerIdx(customerIdx);
    }
}
