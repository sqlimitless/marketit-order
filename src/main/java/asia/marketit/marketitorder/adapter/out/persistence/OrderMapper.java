package asia.marketit.marketitorder.adapter.out.persistence;

import asia.marketit.marketitorder.domain.OrderDto;
import asia.marketit.marketitorder.domain.OrderItemDto;

public class OrderMapper {
    public static OrderEntity toEntity(OrderDto order) {
        OrderEntity orderEntity = OrderEntity.builder()
                .orderIdx(order.getOrderIdx())
                .customerIdx(order.getCustomerIdx())
                .build();
        orderEntity.updateOrderStatus(order.getOrderStatus());
        orderEntity.setOrderItems(order.getOrderItemDtos().stream().map(OrderMapper::toOrderItemEntity).toList());
        return orderEntity;
    }

    public static OrderDto toOrder(OrderEntity orderEntity){
        return OrderDto.builder()
                .orderIdx(orderEntity.getOrderIdx())
                .orderStatus(orderEntity.getOrderStatus())
                .customerIdx(orderEntity.getCustomerIdx())
                .totalPrice(orderEntity.getTotalPrice())
                .orderItemDtos(orderEntity.getOrderItemEntities().stream().map(OrderMapper::toOrderItem).toList())
                .build();
    }

    public static OrderItemEntity toOrderItemEntity(OrderItemDto orderItemsDto) {
        return OrderItemEntity.builder()
                .itemName(orderItemsDto.getItemName())
                .quantity(orderItemsDto.getQuantity())
                .price(orderItemsDto.getPrice())
                .build();
    }
    
    public static OrderItemDto toOrderItem(OrderItemEntity orderItemEntity) {
        return OrderItemDto.builder()
                .orderItemIdx(orderItemEntity.getOrderItemIdx())
                .orderIdx(orderItemEntity.getOrderEntity().getOrderIdx())
                .itemName(orderItemEntity.getItemName())
                .quantity(orderItemEntity.getQuantity())
                .price(orderItemEntity.getPrice())
                .build();
    }
}
