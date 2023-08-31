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
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "mki_order")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_idx")
    private long orderIdx;

    @Column(name = "customer_idx")
    private long customerIdx;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "orderEntity", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<OrderItemEntity> orderItemEntities = new ArrayList<>();

    @Column(name = "total_price")
    private long totalPrice;

    @CreatedDate
    @Column(updatable = false, name = "reg_date")
    private LocalDateTime regDate;

    @LastModifiedDate
    @Column(name = "mod_date")
    private LocalDateTime modDate;

    @Builder
    public OrderEntity(long orderIdx, long customerIdx) {
        this.orderIdx = orderIdx;
        this.customerIdx = customerIdx;
    }

    public void setOrderItems(List<OrderItemEntity> orderItemEntities) {
        if (orderItemEntities == null) {
            return;
        }
        this.totalPrice += orderItemEntities.stream()
                .peek(orderItemEntity -> {
                    orderItemEntity.setOrderEntity(this);
                    this.orderItemEntities.add(orderItemEntity);
                })
                .mapToInt(item -> item.getPrice() * item.getQuantity())
                .sum();
    }

    public void updateOrderStatus(OrderStatus orderStatus) {
        if(orderStatus == null){
            this.orderStatus = OrderStatus.WAIT;
            return;
        }
        if (orderStatus.equals(OrderStatus.COMPLETE) && !this.orderStatus.equals(OrderStatus.WAIT)) {
            throw new IllegalArgumentException("상태값이 잘못 되었슴.");
        }
        this.orderStatus = orderStatus;
    }
}
