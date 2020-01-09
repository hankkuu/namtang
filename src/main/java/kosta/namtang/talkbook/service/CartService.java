package kosta.namtang.talkbook.service;

import com.google.common.collect.Lists;
import kosta.namtang.talkbook.model.domain.Cart;
import kosta.namtang.talkbook.model.dto.cart.CartDelRequestDto;
import kosta.namtang.talkbook.model.dto.cart.CartResponseDto;
import kosta.namtang.talkbook.repository.BookRepository;
import kosta.namtang.talkbook.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CartService {
    private final CartRepository cartRepository;

    private final BookRepository bookRepository;

    //유저 repo 추가해야됨

    public List<Cart> selectByUserIdx(Long userIdx) {
        return Lists.newArrayList(cartRepository.findByCartIdUserIdx(userIdx));
    }

    //삭제
    public CartDelRequestDto delete(Long userIdx, Long bookIdx) {
//        int result = cartRepository.deleteByCartIdUserIdxAndCartIdBookIdx(userIdx, bookIdx);
//        if (result != 1) {
//            // error
//        }
        return null;
    }
}
