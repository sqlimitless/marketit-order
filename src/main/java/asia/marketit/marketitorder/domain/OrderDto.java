package asia.marketit.marketitorder.domain;

import asia.marketit.marketitorder.adapter.out.persistence.OrderStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class OrderDto {

    private long orderIdx;

    private long customerIdx;

    private OrderStatus orderStatus;

    private List<OrderItemDto> orderItemDtos = new ArrayList<>();

    private long totalPrice;

    @Builder
    public OrderDto(long orderIdx, long customerIdx, OrderStatus orderStatus, List<OrderItemDto> orderItemDtos, long totalPrice) {
        this.orderIdx = orderIdx;
        this.customerIdx = customerIdx;
        this.orderStatus = orderStatus;
        this.orderItemDtos = orderItemDtos;
        this.totalPrice = totalPrice;
    }
}
