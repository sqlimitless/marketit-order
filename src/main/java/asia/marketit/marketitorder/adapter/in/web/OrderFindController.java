package asia.marketit.marketitorder.adapter.in.web;

import asia.marketit.marketitorder.application.port.in.OrderFindUseCase;
import asia.marketit.marketitorder.domain.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderFindController {

    private final OrderFindUseCase orderFindUseCase;

    @GetMapping("/customer/{customerIdx}/orders/{orderIdx}")
    public ResponseEntity<OrderDto> findOrder(@PathVariable("customerIdx") Long customerIdx, @PathVariable("orderIdx") Long orderIdx) {
        if (orderIdx == null) {
            throw new IllegalArgumentException("orderIdx가 잘못됨.");
        }
        if (customerIdx == null) {
            throw new IllegalArgumentException("customerIdx가 잘못됨.");
        }

        OrderDto order = orderFindUseCase.findOrder(orderIdx);
        if (order.getCustomerIdx() != customerIdx) {
            throw new IllegalArgumentException("권한 없음.");
        }
        return ResponseEntity.ok(order);
    }

    @GetMapping("/customer/{customerIdx}/orders")
    public ResponseEntity<List<OrderDto>> findOrder(@PathVariable("customerIdx") Long customerIdx) {
        if (customerIdx == null) {
            throw new IllegalArgumentException("customerIdx가가 잘못됨.");
        }
        List<OrderDto> order = orderFindUseCase.findOrderByCustomer(customerIdx);
        return ResponseEntity.ok(order);
    }
}
