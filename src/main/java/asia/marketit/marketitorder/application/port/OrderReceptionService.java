package asia.marketit.marketitorder.application.port;

import asia.marketit.marketitorder.application.port.in.OrderReceptionUseCase;
import asia.marketit.marketitorder.application.port.out.OrderReceptionPort;
import asia.marketit.marketitorder.domain.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderReceptionService implements OrderReceptionUseCase {

    private final OrderReceptionPort orderReceptionPort;

    @Override
    @Transactional
    public OrderDto orderRecprion(OrderDto order) {
        return orderReceptionPort.save(order);
    }
}
