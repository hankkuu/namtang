package kosta.namtang.talkbook.model.domain.account;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Setter
@Getter
@AllArgsConstructor
public class UserAddress {
    private String userPost;

    private String userAddress;

    private String userAddressDetail;
}
