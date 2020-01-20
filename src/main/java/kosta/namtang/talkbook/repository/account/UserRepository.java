package kosta.namtang.talkbook.repository.account;

import kosta.namtang.talkbook.model.domain.account.Users;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<Users, Long> {
    List<Users> findByUserEmail (String userEmail);
    Users findByUserEmailAndAccountIdx(String userEmail, long accountIdx);
}
