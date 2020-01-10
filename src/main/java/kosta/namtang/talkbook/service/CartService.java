package kosta.namtang.talkbook.service;

import com.google.common.collect.Lists;
import kosta.namtang.talkbook.model.domain.Cart;
import kosta.namtang.talkbook.model.dto.cart.CartDelRequestDto;
import kosta.namtang.talkbook.repository.BookRepository;
import kosta.namtang.talkbook.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        return Lists.newArrayList(cartRepository.findByCartIdUserIdx(userIdx));
    }

    //삭제
    public void delete(Long userIdx, Long bookIdx) {
        cartRepository.deleteByCartIdUserIdxAndCartIdBookIdx(userIdx, bookIdx);
//        if (list.isEmpty()) {
//            // error
//            System.out.println("삭제할 정보 없음");
//        }
//        return null;
    }
}
