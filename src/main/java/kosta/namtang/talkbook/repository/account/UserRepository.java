package kosta.namtang.talkbook.repository.account;

import kosta.namtang.talkbook.model.domain.account.Users;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Users, Long> {
}
