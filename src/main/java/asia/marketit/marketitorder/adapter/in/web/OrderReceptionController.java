package asia.marketit.marketitorder.adapter.in.web;

import asia.marketit.marketitorder.adapter.in.web.request.OrderRequest;
import asia.marketit.marketitorder.application.port.in.OrderReceptionUseCase;
import asia.marketit.marketitorder.domain.OrderDto;
import asia.marketit.marketitorder.domain.OrderItemDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderReceptionController {

    private final OrderReceptionUseCase orderReceptionUseCase;

    @PostMapping("/orders")
    public ResponseEntity<OrderDto> orderRecprion(@RequestBody OrderRequest orderRequest) {
        if (orderRequest.getCustomerIdx() == null) {
            throw new IllegalArgumentException("CustomerIdx가 없음");
        }
        if (orderRequest.getOrderItems() == null) {
            throw new IllegalArgumentException("OrderItems가 없음");
        }
        OrderDto orderReq = OrderDto.builder()
                .orderItemDtos(orderRequest.getOrderItems().stream().map(orderItemReq -> OrderItemDto.builder()
                        .itemName(orderItemReq.getItemName())
                        .price(orderItemReq.getPrice())
                        .quantity(orderItemReq.getQuantity())
                        .build()
                ).toList())
                .customerIdx(orderRequest.getCustomerIdx())
                .build();
        OrderDto order = orderReceptionUseCase.orderRecprion(orderReq);
        return ResponseEntity.ok(order);
    }

}
