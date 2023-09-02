package asia.marketit.marketitorder.order.reception;

import asia.marketit.marketitorder.adapter.in.web.request.OrderItemRequest;
import asia.marketit.marketitorder.adapter.in.web.request.OrderRequest;
import asia.marketit.marketitorder.adapter.out.persistence.OrderStatus;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class 주문등록_컨트롤러 {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void 컨틀로러_테스트() throws Exception {
        // Given
        OrderItemRequest orderItemReq = OrderItemRequest.builder()
                .itemName("볼펜")
                .quantity(1)
                .price(1000)
                .build();
        OrderItemRequest orderItemReq2 = OrderItemRequest.builder()
                .itemName("연습장")
                .quantity(2)
                .price(3000)
                .build();
        List<OrderItemRequest> orderItemReqs = new ArrayList<>();
        orderItemReqs.add(orderItemReq);
        orderItemReqs.add(orderItemReq2);
        OrderRequest order = OrderRequest.builder()
                .orderStatus(OrderStatus.WAIT)
                .customerIdx(1L)
                .orderItems(orderItemReqs)
                .build();
        String orderJson = objectMapper.writeValueAsString(order);
        // When
        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(orderJson)
                )
                .andExpect(status().isOk())
                .andDo(print())
        ;
    }
}
