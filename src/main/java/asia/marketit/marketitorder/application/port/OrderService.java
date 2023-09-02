package asia.marketit.marketitorder.application.port;

import asia.marketit.marketitorder.adapter.out.persistence.OrderEntity;
import asia.marketit.marketitorder.adapter.out.persistence.OrderMapper;
import asia.marketit.marketitorder.application.port.in.OrderCompleteUseCase;
import asia.marketit.marketitorder.application.port.in.OrderFindUseCase;
import asia.marketit.marketitorder.application.port.in.OrderReceptionUseCase;
import asia.marketit.marketitorder.application.port.out.OrderCompletePort;
import asia.marketit.marketitorder.application.port.out.OrderFindPort;
import asia.marketit.marketitorder.application.port.out.OrderReceptionPort;
import asia.marketit.marketitorder.domain.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService implements OrderReceptionUseCase, OrderCompleteUseCase, OrderFindUseCase {

    private final OrderReceptionPort orderReceptionPort;
    private final OrderCompletePort orderCompletePort;
    private final OrderFindPort orderFindPort;

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

    @Override
    @Transactional
    public OrderDto findOrder(long orderIdx) {
        OrderEntity orderEntity = orderFindPort.findByIdx(orderIdx);
        return OrderMapper.toOrder(orderEntity);
    }

    @Override
    @Transactional
    public List<OrderDto> findOrderByCustomer(long customerIdx) {
        List<OrderEntity> orderEntities = orderFindPort.findByCustomerIdx(customerIdx);
        return orderEntities.stream().map(OrderMapper::toOrder).toList();
    }
}
