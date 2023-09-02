package asia.marketit.marketitorder.adapter.in.web;

import asia.marketit.marketitorder.application.port.in.OrderCompleteUseCase;
import asia.marketit.marketitorder.domain.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderCompleteController {

    private final OrderCompleteUseCase orderCompleteUsecase;

    @PatchMapping("/orders/{orderId}/complete")
    public ResponseEntity<OrderDto> orderComplete(@PathVariable("orderId") Long orderId) {
        if (orderId == null) {
            throw new IllegalArgumentException("orderId가 없음.");
        }
        return ResponseEntity.ok(orderCompleteUsecase.complete(orderId));
    }
}
