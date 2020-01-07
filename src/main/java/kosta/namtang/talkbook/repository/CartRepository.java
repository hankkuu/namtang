package kosta.namtang.talkbook.repository;

import kosta.namtang.talkbook.model.domain.Cart;
import org.springframework.data.repository.CrudRepository;

public interface CartRepository extends CrudRepository<Cart,Long> {
}
