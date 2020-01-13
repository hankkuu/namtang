package kosta.namtang.talkbook.repository.account;

import kosta.namtang.talkbook.model.domain.Admin;
import kosta.namtang.talkbook.model.domain.account.Account;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {
    //Account findBy(String userId);
}
