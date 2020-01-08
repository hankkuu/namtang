package kosta.namtang.talkbook;

import kosta.namtang.talkbook.model.domain.Book;
import kosta.namtang.talkbook.model.domain.User;
import kosta.namtang.talkbook.model.domain.bill.BillKey;
import kosta.namtang.talkbook.model.domain.bill.PurchaseOrder;
import kosta.namtang.talkbook.model.domain.bill.PurchasePayment;
import kosta.namtang.talkbook.service.BookService;
import kosta.namtang.talkbook.service.bill.PurchaseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class BillingTests {

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private BookService bookService;

    @Test
    void purchaseTest() throws Exception {
        // 구매 책 목록
        List<Book> bookList = bookService.selectAll();
        List<Book> list = new ArrayList<>();
        for(int i = 0 ; i < 10 ; i ++) {
            Book b = bookList.get(i);
            list.add(b);
        }

        PurchaseOrder order = new PurchaseOrder();
        order.setDeliveryAddress("경기도 판교시");
        PurchasePayment payment = new PurchasePayment();
        payment.setDeliveryComment("잘배송해주세요");
        payment.setReceiverName("강한규");
        payment.setPaymentCode(1);
        payment.setReceiverPhone("111111111");
        payment.setShippingPrice(new BigDecimal("10000"));
        payment.setTotalPrice(new BigDecimal("100000"));
        User user = new User();
        user.setUserIdx(1L);

        BillKey key = purchaseService.insertPurchase(list, order, payment, user);
        assertNotNull(key);

    }

}
