package asia.marketit.marketitorder.adapter.in.web.request;

import asia.marketit.marketitorder.adapter.out.persistence.OrderStatus;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class OrderRequest {

    private Long orderIdx;

    private Long customerIdx;

    private OrderStatus orderStatus;

    private List<OrderItemRequest> orderItems;

    private Long totalPrice;
}
