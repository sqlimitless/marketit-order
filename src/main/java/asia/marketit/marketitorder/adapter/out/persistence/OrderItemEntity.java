package asia.marketit.marketitorder.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "mki_order_item")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class OrderItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_idx")
    private long orderItemIdx;

    @ManyToOne
    @JoinColumn(name = "order_idx", nullable=false)
    private OrderEntity orderEntity;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "price")
    private int price;

    @CreatedDate
    @Column(updatable = false, name = "reg_date")
    private LocalDateTime regDate;

    @LastModifiedDate
    @Column(name = "mod_date")
    private LocalDateTime modDate;

    @Builder
    public OrderItemEntity(long orderItemIdx, OrderEntity orderEntity, String itemName, int quantity, int price, LocalDateTime regDate, LocalDateTime modDate) {
        if (price < 0) {
            throw new IllegalArgumentException("가격은 음수가 될수 없음.");
        }
        this.orderItemIdx = orderItemIdx;
        this.orderEntity = orderEntity;
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
        this.regDate = regDate;
        this.modDate = modDate;
    }

    void setOrderEntity(OrderEntity orderEntity) {
        this.orderEntity = orderEntity;
    }
}
