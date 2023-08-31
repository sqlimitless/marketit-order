package asia.marketit.marketitorder.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class OrderItemDto {

    private long orderItemIdx;
    private long orderIdx;
    private String itemName;

    private int quantity;

    private int price;

    @Builder
    public OrderItemDto(long orderItemIdx, long orderIdx, String itemName, int quantity, int price) {
        this.orderItemIdx = orderItemIdx;
        this.orderIdx = orderIdx;
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
    }
}
