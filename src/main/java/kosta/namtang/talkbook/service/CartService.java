package kosta.namtang.talkbook.service;

import com.google.common.collect.Lists;
import kosta.namtang.talkbook.model.domain.Cart;
//import kosta.namtang.talkbook.model.domain.User;
import kosta.namtang.talkbook.model.domain.CartId;
import kosta.namtang.talkbook.model.domain.account.Account;
import kosta.namtang.talkbook.model.dto.cart.CartSetRequest;
import kosta.namtang.talkbook.repository.BookRepository;
import kosta.namtang.talkbook.repository.CartRepository;
import kosta.namtang.talkbook.util.DateTimeHelper;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class CartService {
    private final CartRepository cartRepository;

    private final BookRepository bookRepository;

    //유저 repo 추가해야됨


    //출력
    public List<Cart> selectByUserIdx(Long userIdx) {
        List<Cart> list = cartRepository.findByCartIdUserIdx(userIdx);

        return list;
    }

    //추가
    public void insert(CartSetRequest cart,long userIdx) {
        Cart c = new Cart();
        c.setCartId(new CartId(userIdx, cart.getBookIdx()));
        c.setCreateDate(LocalDateTime.now());
        c.setQty(cart.getQty());
        c.setBookPrice(cart.getBookPrice());
        c.setBookTitle(cart.getBookTitle());
        c.setBookImg(cart.getBookImg());
        cartRepository.save(c);

    }

    public Cart update(CartSetRequest cart, Account account) throws Exception {
        Cart c = cartRepository.findByCartIdUserIdxAndCartIdBookIdx(account.getAccountIdx(), cart.getBookIdx());
        if (c != null) {
            c.setQty(cart.getQty());
            cartRepository.save(c);

            return c;
        } else {
            //throw new Exception("실패");
        }

        return null;
    }


    //삭제
    public void delete(Long userIdx, Long bookIdx) {
        Cart c = cartRepository.findByCartIdUserIdxAndCartIdBookIdx(userIdx, bookIdx);
        if (c != null) {
            cartRepository.delete(c);
        }
        //cartRepository.deleteByCartIdUserIdxAndCartIdBookIdx(userIdx, bookIdx);

    }
}
