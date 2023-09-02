package asia.marketit.marketitorder.application.port.out;

import asia.marketit.marketitorder.adapter.out.persistence.OrderEntity;

import java.util.List;

public interface OrderFindPort {
    OrderEntity findByIdx(long orderIdx);

    List<OrderEntity> findByCustomerIdx(long customerIdx);
}
