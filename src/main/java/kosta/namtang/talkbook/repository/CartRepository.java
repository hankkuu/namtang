package kosta.namtang.talkbook.repository;

import kosta.namtang.talkbook.model.domain.Cart;
import kosta.namtang.talkbook.model.domain.CartId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CartRepository extends CrudRepository<Cart, CartId> {

    //find => userIdx기준으로 찾기
    List<Cart> findByCartIdUserIdx(Long userIdx);

    //update
    Cart findByCartIdUserIdxAndCartIdBookIdx(Long userIdx, Long bookIdx);


    //delete => useridx BookIdx 로 삭제
    void deleteByCartIdUserIdxAndCartIdBookIdx(Long userIdx, Long bookIdx);
//    int deleteByCartIdUserIdxAndCartIdBookIdx(Long userIdx, Long bookIdx);
}
