# 마켓잇 과제

Java 17  
Spring boot 3.1.3  
H2

### H2 (Embedded)
- JDBC URL: jdbc:h2:~/test
- user: sa
- pass:

### Test Summary
| 총 | 실패 | 무시 | 시간     |
|---|----|----|--------|
| 7 | 0  | 0  | 1.219s |


> 헥사고날 아케텍처로 작업했습니다.    
> Order - OrderItem 1:N 연관관계 설정하였습니다.
> 
> orderIdx: 주문번호  
> customerIdx: 사용자 고유번호   
> orderItems: 상품리스트   
> orderItems.itemName: 상품이름   
> orderItems.price: 상품가격  
> orderItems.quantity: 상품갯수


### 주문 접수처리
    POST /orders
    헤더 Content-Type application/json
    body 예)
    {
        "customerIdx": 1,
        "orderItems": [{
                "itemName": "연필",
                "price": 1000,
                "quantity": 5
            },{
                "itemName": "필통",
                "price": 3000,
                "quantity": 1
            }
        ]
    }
    리턴 예)
    {
        "orderIdx": 1,
        "customerIdx": 1,
        "orderStatus": "WAIT",
        "orderItemDtos": [
            {
                "orderItemIdx": 1,
                "orderIdx": 1,
                "itemName": "연필",
                "quantity": 5,
                "price": 1000
            },
            {
                "orderItemIdx": 2,
                "orderIdx": 1,
                "itemName": "필통",
                "quantity": 1,
                "price": 3000
            }
        ],
        "totalPrice": 8000
    }


### 주문 완료처리
    PATCH /orders/{orderIdx}/complete

### 단일 주문조회
    GET /customer/{customerIdx}/orders/{orderIdx}

### 주문 목록조회
    GET /customer/{customerIdx}/orders
