package asia.marketit.marketitorder.adapter.in.web.request;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OrderItemRequest {

    private long orderItemIdx;
    private String itemName;
    private int quantity;
    private int price;
}
