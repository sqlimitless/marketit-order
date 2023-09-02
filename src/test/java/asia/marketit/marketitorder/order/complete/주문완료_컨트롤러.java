package asia.marketit.marketitorder.order.complete;

import asia.marketit.marketitorder.adapter.in.web.OrderCompleteController;
import asia.marketit.marketitorder.adapter.out.persistence.OrderStatus;
import asia.marketit.marketitorder.application.port.in.OrderCompleteUseCase;
import asia.marketit.marketitorder.domain.OrderDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(OrderCompleteController.class)
@MockBean(JpaMetamodelMappingContext.class)
public class 주문완료_컨트롤러 {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderCompleteUseCase orderCompleteUsecase;

    @Test
    public void 컨트롤러_테스트() throws Exception {
        OrderDto order = OrderDto.builder()
                .orderStatus(OrderStatus.COMPLETE)
                .customerIdx(1L)
                .build();
        when(orderCompleteUsecase.complete(any(Long.class))).thenReturn(order);

        mockMvc.perform(patch("/orders/1/complete"))
                .andExpect(status().isOk());

        mockMvc.perform(patch("/orders/null/complete"))
                .andExpect(status().is4xxClientError());
    }
}
