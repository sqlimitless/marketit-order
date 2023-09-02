package asia.marketit.marketitorder.order.complete;

import asia.marketit.marketitorder.adapter.out.persistence.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Import(OrderPersistenceAdapter.class)
public class 주문완료_테스트 {
    @Autowired
    private OrderPersistenceAdapter orderPersistenceAdapter;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    public void 주문완료_변경_테스트() {
        // Given
        OrderItemEntity orderItemEntity = OrderItemEntity.builder()
                .itemName("볼펜")
                .quantity(1)
                .price(1000)
                .build();
        OrderItemEntity orderItemEntity2 = OrderItemEntity.builder()
                .itemName("연습장")
                .quantity(2)
                .price(3000)
                .build();
        List<OrderItemEntity> orderItemEntities = new ArrayList<>();
        orderItemEntities.add(orderItemEntity);
        orderItemEntities.add(orderItemEntity2);
        OrderEntity orderEntity = OrderEntity.builder()
                .orderStatus(OrderStatus.WAIT)
                .customerIdx(1L)
                .build();
        orderEntity.addOrderItems(orderItemEntities);

        OrderEntity savedOrder = orderRepository.save(orderEntity);

        // When
        OrderEntity completedOrder = orderPersistenceAdapter.orderStateComplete(savedOrder.getOrderIdx());

        // Then
        assertEquals(completedOrder.getOrderStatus(), OrderStatus.COMPLETE);
    }

    @Test
    public void 없는주문번호오류테스트() {
        assertThrows(IllegalArgumentException.class, () -> orderPersistenceAdapter.orderStateComplete(-1L));
    }
}
