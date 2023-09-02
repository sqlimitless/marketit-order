package asia.marketit.marketitorder.adapter.in.web.response;

import asia.marketit.marketitorder.adapter.out.persistence.OrderStatus;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class OrderResponse {

    private Long orderIdx;

    private Long customerIdx;

    private OrderStatus orderStatus;

    private List<OrderItemResponse> orderItems;

    private Long totalPrice;

}
