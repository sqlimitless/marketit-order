package asia.marketit.marketitorder.order.complete;

import asia.marketit.marketitorder.adapter.out.persistence.OrderEntity;
import asia.marketit.marketitorder.adapter.out.persistence.OrderStatus;
import asia.marketit.marketitorder.application.port.OrderService;
import asia.marketit.marketitorder.application.port.out.OrderCompletePort;
import asia.marketit.marketitorder.domain.OrderDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class 주문완료_서비스 {

    @Mock
    OrderCompletePort orderCompletePort;

    @InjectMocks
    OrderService orderService;

    @Test
    void 주문완료_서비스_테스트 () {
        // Given
        OrderEntity orderEntity = OrderEntity.builder()
                .orderIdx(1L)
                .orderStatus(OrderStatus.COMPLETE)
                .build();
        when(orderCompletePort.orderStateComplete(1L)).thenReturn(orderEntity);

        // When
        OrderDto complete = orderService.complete(1L);

        // then
        assertEquals(complete.getOrderStatus(), OrderStatus.COMPLETE);
    }}
