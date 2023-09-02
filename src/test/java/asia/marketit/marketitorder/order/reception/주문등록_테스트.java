package asia.marketit.marketitorder.order.reception;

import asia.marketit.marketitorder.adapter.out.persistence.*;
import asia.marketit.marketitorder.domain.OrderDto;
import asia.marketit.marketitorder.domain.OrderItemDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class 주문등록_테스트 {

    @Mock
    OrderRepository orderRepository;

    @InjectMocks
    private OrderPersistenceAdapter orderPersistenceAdapter;

    @Test
    void 주문등록_테스트() {
        // Given
        OrderItemDto orderItemDto = OrderItemDto.builder()
                .itemName("볼펜")
                .quantity(1)
                .price(1000)
                .build();
        OrderItemDto orderItemDto2 = OrderItemDto.builder()
                .itemName("연습장")
                .quantity(2)
                .price(3000)
                .build();
        List<OrderItemDto> orderItemDtos = new ArrayList<>();
        orderItemDtos.add(orderItemDto);
        orderItemDtos.add(orderItemDto2);
        OrderDto order = OrderDto.builder()
                .orderStatus(OrderStatus.WAIT)
                .customerIdx(1L)
                .orderItemDtos(orderItemDtos)
                .build();

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
                .customerIdx(1L)
                .build();
        orderEntity.addOrderItems(orderItemEntities);

        when(orderRepository.save(any(asia.marketit.marketitorder.adapter.out.persistence.OrderEntity.class))).thenReturn(orderEntity);

        // When
        OrderEntity orderRtn = orderPersistenceAdapter.save(orderEntity);
        // Then
        assertEquals(orderEntity.getTotalPrice(), orderRtn.getTotalPrice());
        assertEquals(orderEntity.getTotalPrice(), 7000);
        verify(orderRepository).save(any(asia.marketit.marketitorder.adapter.out.persistence.OrderEntity.class));
    }
}
